package site.ugaeng.localhosting.forward;

import site.ugaeng.localhosting.http.local.request.Request;
import site.ugaeng.localhosting.http.local.response.Response;

public abstract class RequestForwardingTemplate {

    public void run() {
        while (isRunning()) {
            Request request = receiveForwardedRequest();

            Response response = performForwardedRequest(request);

            sendForwardedResponse(response);
        }
    }

    public abstract Request receiveForwardedRequest();

    public abstract Response performForwardedRequest(Request request);

    public abstract void sendForwardedResponse(Response response);

    public abstract boolean isRunning();
}
