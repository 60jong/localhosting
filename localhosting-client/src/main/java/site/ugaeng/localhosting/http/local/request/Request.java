package site.ugaeng.localhosting.http.local.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import site.ugaeng.localhosting.http.request.RequestLine;
import java.util.Map;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public final class Request {

    private RequestLine requestLine;
    private Map<String, Object> headers;
    private String entity;
}
