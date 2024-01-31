package site.ugaeng.localhosting.http.request;

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
    private Map<String, String> headers;
    private String entity;

    @Override
    public String toString() {
        return "Request{" +
                "requestLine=" + requestLine +
                ", headers=" + headers +
                ", entity='" + entity + '\'' +
                '}';
    }
}
