package site.ugaeng.localhosting.http.forward;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import site.ugaeng.localhosting.TunnelingServerConnection;
import site.ugaeng.localhosting.forward.RequestForwardingTemplate;
import site.ugaeng.localhosting.client.LocalProcessClient;
import site.ugaeng.localhosting.http.local.request.Request;
import site.ugaeng.localhosting.http.local.response.Response;

@Slf4j
@RequiredArgsConstructor
public class HttpRequestForwardingTemplate extends RequestForwardingTemplate {

    private final TunnelingServerConnection connection;
    private final LocalProcessClient processClient;

    @Override
    public Request receiveForwardedRequest() {
        log.info("Waiting Request from Tunnel");
        Request request = connection.getSocketReader()
                                    .readRequestByLine();
        log.info("Forwarded Request [{}]", request);
        return request;
    }

    @Override
    public Response performForwardedRequest(Request request) {
        return processClient.performRequest(request);
    }

    @Override
    public void sendForwardedResponse(Response response) {
        log.info("Forwarded Response [{}]", response);
        connection.sendResponse(response);
    }

    @Override
    public boolean isRunning() {
        return connection.isConnected();
    }
}
