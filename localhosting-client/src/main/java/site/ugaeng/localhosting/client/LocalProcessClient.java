package site.ugaeng.localhosting.client;

import site.ugaeng.localhosting.http.local.request.Request;
import site.ugaeng.localhosting.http.local.response.Response;

public interface LocalProcessClient {

    Response performRequest(Request request);
}
