package site.ugaeng.localhosting.client;

import site.ugaeng.localhosting.http.request.Request;
import site.ugaeng.localhosting.http.response.Response;

public interface LocalProcessClient {

    Response performRequest(Request request);
}
