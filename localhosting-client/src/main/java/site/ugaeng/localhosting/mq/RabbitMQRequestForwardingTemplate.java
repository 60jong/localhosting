package site.ugaeng.localhosting.mq;

import site.ugaeng.localhosting.forward.RequestForwardingTemplate;
import site.ugaeng.localhosting.http.local.request.Request;
import site.ugaeng.localhosting.http.local.response.Response;

import java.io.IOException;

public class RabbitMQRequestForwardingTemplate extends RequestForwardingTemplate {

    @Override
    public Request receiveForwardedRequest()  {
        return null;
    }

    @Override
    public Response performForwardedRequest(Request request) {
        return null;
    }

    @Override
    public void sendForwardedResponse(Response response){

    }

    @Override
    public boolean isRunning() {
        return false;
    }
}
