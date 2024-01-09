package site.ugaeng.localhosting.http.client;

import site.ugaeng.localhosting.http.request.Request;
import site.ugaeng.localhosting.http.response.Response;

public interface RequestClient {

    Response performRequest(Request request);
}
