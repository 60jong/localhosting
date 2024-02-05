package site.ugaeng.localhosting.tunnel.impl.socket.forward;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import site.ugaeng.localhosting.tunnel.conn.TunnelConnection;
import site.ugaeng.localhosting.tunnel.forward.RequestForwardingService;
import site.ugaeng.localhosting.http.request.Request;
import site.ugaeng.localhosting.http.response.Response;
import site.ugaeng.localhosting.tunnel.impl.socket.conn.SocketTunnelConnection;

@Slf4j
public class SocketRequestForwardingService implements RequestForwardingService {

    @Override
    public void startForwarding(TunnelConnection connection) {
        Thread forwardingWorkerThread = new Thread(
                new SocketForwarder((SocketTunnelConnection) connection));
        forwardingWorkerThread.setName("forwarding-worker");

        forwardingWorkerThread.start();
    }

    @RequiredArgsConstructor
    private static class SocketForwarder implements Runnable {

        private final SocketTunnelConnection connection;

        @Override
        public void run() {
            forward();
        }

        private void forward() {
            while (isRunning()) {
                Request request = receiveRequest();

                Response response = performRequest(request);

                sendResponse(response);
            }
        }

        private Request receiveRequest() {
            log.info("Waiting Request from Tunnel");
            Request request = connection.getSocketReader()
                                        .readRequestByLine();
            log.info("Forwarded Request [{}]", request);
            return request;
        }

        private Response performRequest(Request request) {
            return processClient.performRequest(request);
        }

        private void sendResponse(Response response) {
            log.info("Forwarded Response [{}]", response);
            connection.getSocketWriter()
                      .writeResponseWithLine(response);
        }

        private boolean isRunning() {
            return connection.isConnected();
        }
    }
}
