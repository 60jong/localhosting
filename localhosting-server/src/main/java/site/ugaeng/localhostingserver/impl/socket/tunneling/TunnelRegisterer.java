package site.ugaeng.localhostingserver.impl.socket.tunneling;

import lombok.extern.slf4j.Slf4j;
import site.ugaeng.localhostingserver.http.request.Request;
import site.ugaeng.localhostingserver.http.request.RequestLine;
import site.ugaeng.localhostingserver.http.request.reader.RequestReader;
import site.ugaeng.localhostingserver.impl.socket.io.SocketDataLineReader;
import site.ugaeng.localhostingserver.impl.socket.io.SocketDataLineWriter;
import site.ugaeng.localhostingserver.impl.socket.tunneling.client.TunnelClient;
import site.ugaeng.localhostingserver.impl.socket.tunneling.client.TunnelClientRepository;
import site.ugaeng.localhostingserver.tunnel.TunnelRegisterRequest;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;

import static site.ugaeng.localhostingserver.utils.ObjectUtils.convertToObject;
import static site.ugaeng.localhostingserver.utils.SocketIOUtils.getReader;
import static site.ugaeng.localhostingserver.utils.SocketIOUtils.getWriter;

@Slf4j
public class TunnelRegisterer implements Runnable {

    private final Socket registerClient;
    private final SocketDataLineReader clientReader;
    private final SocketDataLineWriter clientWriter;

    private final TunnelClientRepository tunnelClientRepository;

    public TunnelRegisterer(Socket registerClient) throws IOException {
        this.registerClient = registerClient;
        this.clientReader = getReader(registerClient);
        this.clientWriter = getWriter(registerClient);

        this.tunnelClientRepository = TunnelClientRepository.getInstance();
    }

    @Override
    public void run() {
        try {
            TunnelRegisterRequest request = receiveTunnelRegisterRequest();

            String requestTunnelName = request.getTunnelName();

            if (!isExistingTunnelName(requestTunnelName)) {
                log.info("new Tunnel, Tunnel Name : {}", requestTunnelName);
                sendRegisterResponse();
                registerClient.close();
                return;
            }
            log.info("exising Tunnel, Tunnel Name : {}", requestTunnelName);

            sendRegisterResponse();


        } catch (IOException ioException) {
            throw new RuntimeException(ioException);
        }
    }

    private void sendRegisterResponse() throws IOException {
        BufferedWriter bw = clientWriter.getBufferedWriter();

        bw.write("HTTP/1.1 200 OK\r\nConnection: Keep-Alive\r\n\r\n");
        bw.flush();
    }

    private TunnelRegisterRequest receiveTunnelRegisterRequest() {
        try {
            Request httpRequest = RequestReader.readFromReader(clientReader);

            validateTunnelRegisterRequest(httpRequest);

            return convertToObject(httpRequest.getEntity(), TunnelRegisterRequest.class);
        } catch (RuntimeException e) {
            throw e;
        }
    }

    private void validateTunnelRegisterRequest(Request httpRequest) {
        RequestLine requestLine = httpRequest.getRequestLine();

        if (!requestLine.getMethod().equals("POST")) {
            throw new RuntimeException();
        }

        if (!requestLine.getUri().equals("/tunnels")) {
            throw new RuntimeException();
        }

    }

    private boolean isExistingTunnelName(String requestTunnelName) {
        return tunnelClientRepository.find(requestTunnelName)
                                     .isPresent();
    }

}
