package site.ugaeng.localhosting.code.tunnel.stream;

import site.ugaeng.localhosting.http.local.request.Request;
import site.ugaeng.localhosting.http.local.response.Response;

public class RabbitMQTunnelStream implements TunnelStream {

    @Override
    public Request receiveRequest() {
        return null;
    }

    @Override
    public void sendResponse(Response response) {

    }
}
