package site.ugaeng.localhosting.http.forward;

import lombok.extern.slf4j.Slf4j;
import site.ugaeng.localhosting.TunnelingSocketConnection;
import site.ugaeng.localhosting.TunnelingSocketConnector;
import site.ugaeng.localhosting.forward.RequestForwarder;
import site.ugaeng.localhosting.client.LocalProcessClient;
import site.ugaeng.localhosting.http.local.request.Request;
import site.ugaeng.localhosting.http.local.response.Response;

@Slf4j
public class HttpRequestForwarder implements RequestForwarder {

    private final TunnelingSocketConnection connection;
    private final LocalProcessClient processClient;

    public HttpRequestForwarder(LocalProcessClient processClient) {
        this.connection = TunnelingSocketConnector.getSocketConnection();
        this.processClient = processClient;
    }

    @Override
    public void forward() {
        while (isRunning()) {
            Request request = receiveForwardedRequest();

            Response response = performForwardedRequest(request);

            sendForwardedResponse(response);
        }
    }

    private Request receiveForwardedRequest() {
        log.info("Waiting Request from Tunnel");
        Request request = connection.getSocketReader()
                                    .readRequestByLine();
        log.info("Forwarded Request [{}]", request);
        return request;
    }

    private Response performForwardedRequest(Request request) {
        return processClient.performRequest(request);
    }

    private void sendForwardedResponse(Response response) {
        log.info("Forwarded Response [{}]", response);
        connection.sendResponse(response);
    }

    private boolean isRunning() {
        return connection.isConnected();
    }
}
