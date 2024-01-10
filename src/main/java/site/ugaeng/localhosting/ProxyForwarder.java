package site.ugaeng.localhosting;

import lombok.extern.slf4j.Slf4j;
import site.ugaeng.localhosting.http.local.LocalRequests;
import site.ugaeng.localhosting.http.local.client.LocalProcessRequestClient;
import site.ugaeng.localhosting.http.local.request.Request;
import site.ugaeng.localhosting.http.local.response.Response;

import java.io.*;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import static site.ugaeng.localhosting.http.HttpConstant.*;
import static site.ugaeng.localhosting.http.request.RequestLineBuilder.*;
import static site.ugaeng.localhosting.util.ClosableUtils.close;
import static site.ugaeng.localhosting.util.IOStreamUtils.*;
import static site.ugaeng.localhosting.util.StringUtils.hasText;

@Slf4j
public class ProxyForwarder implements Runnable {

    private final Socket connection;
    private final BufferedReader reader;
    private final BufferedWriter writer;

    public ProxyForwarder(Socket connection) throws IOException {
        this.connection = connection;
        this.reader = getBufferedReader(connection.getInputStream());
        this.writer = getBufferedWriter(connection.getOutputStream());
    }

    @Override
    public void run() {
        log.info("client addr : {}, port : {}", connection.getInetAddress(), connection.getPort());

        try {
            forward();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        close(connection, reader, writer);
    }

    private void forward() throws IOException {
        LocalProcessRequestClient client = LocalRequests.getLocalRequestClient();

        Request request = readLocalRequest();
        log.info("HTTP RequestLine : {}", request.getRequestLine());

        Response response = client.performRequest(request);

        writeResponse(response);
    }

    private Request readLocalRequest() throws IOException {
        final String requestLine = reader.readLine();
        final Map<String, Object> requestHeader = new HashMap<>();

        String line = null;
        while (hasText((line = reader.readLine()))) {
            String[] headerAndValue = line.split(SP);

            final var header = headerAndValue[0];
            final var value = headerAndValue[1];

            requestHeader.put(header, value);
        }

        final String requestEntity = "";

        return Request.builder()
                      .requestLine(buildLocalRequestLine(requestLine))
                      .headers(requestHeader)
                      .entity(requestEntity)
                      .build();
    }

    private void writeResponse(Response response) throws IOException {
        writer.write(response.generateHttpResponse());
        writer.flush();
    }
}
