package site.ugaeng.localhosting.http;

import lombok.RequiredArgsConstructor;
import site.ugaeng.localhosting.http.request.Request;
import site.ugaeng.localhosting.http.response.Response;

@RequiredArgsConstructor
public class ProxyResponse {

    private final Response response;
}
