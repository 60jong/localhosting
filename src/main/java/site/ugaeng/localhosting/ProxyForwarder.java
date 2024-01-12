package site.ugaeng.localhosting;

import lombok.extern.slf4j.Slf4j;
import site.ugaeng.localhosting.http.local.LocalRequests;
import site.ugaeng.localhosting.http.local.client.LocalProcessRequestClient;
import site.ugaeng.localhosting.http.local.request.Request;
import site.ugaeng.localhosting.http.local.response.Response;

import java.io.*;
import java.net.Socket;

import static site.ugaeng.localhosting.http.local.LocalRequests.*;
import static site.ugaeng.localhosting.util.ClosableUtils.close;

@Slf4j
public class ProxyForwarder implements Runnable {

    private final Socket connection;

    public ProxyForwarder(Socket connection) {
        this.connection = connection;
    }

    @Override
    public void run() {
        log.info("client addr : {}, port : {}", connection.getInetAddress(), connection.getPort());

        try {
            forward();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        close(connection);
    }

    private void forward() throws IOException {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
             BufferedWriter out = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream()))) {

            LocalProcessRequestClient client = getLocalRequestClient();
            Request request = readRequest(in);
            log.info("HTTP RequestLine : {}", request.getRequestLine());

            Response response = client.performRequest(request);

            writeResponse(out, response);
        }
    }

    private Request readRequest(BufferedReader in) throws IOException {
        return Request.readFromReader(in);
    }

    private void writeResponse(BufferedWriter out, Response response) throws IOException {
        out.write(response.generateHttpResponse());
        out.flush();
    }
}
