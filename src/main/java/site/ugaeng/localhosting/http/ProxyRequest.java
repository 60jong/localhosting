package site.ugaeng.localhosting.http;

import lombok.RequiredArgsConstructor;
import site.ugaeng.localhosting.http.request.Request;

@RequiredArgsConstructor
public class ProxyRequest {

    private final Request request;
}
