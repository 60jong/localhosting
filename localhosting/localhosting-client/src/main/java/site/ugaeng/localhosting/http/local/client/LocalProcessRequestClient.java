package site.ugaeng.localhosting.http.local.client;

import site.ugaeng.localhosting.http.local.request.Request;
import site.ugaeng.localhosting.http.local.response.Response;

public interface LocalProcessRequestClient {

    Response performRequest(Request request);
}
