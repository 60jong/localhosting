package site.ugaeng.localhosting.code.tunnel.stream;

import site.ugaeng.localhosting.http.local.request.Request;
import site.ugaeng.localhosting.http.local.response.Response;

public interface TunnelStream {

    Request receiveRequest();

    void sendResponse(Response response);
}
