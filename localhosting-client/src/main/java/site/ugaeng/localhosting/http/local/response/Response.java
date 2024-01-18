package site.ugaeng.localhosting.http.local.response;

import lombok.*;
import site.ugaeng.localhosting.http.response.StatusLine;

import java.util.Map;

import static java.util.stream.Collectors.*;
import static site.ugaeng.localhosting.http.HttpConstant.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public final class Response {

    private StatusLine statusLine;
    private Map<String, String> headers;
    private String entity;

    private String generateHttpStatusLine() {
        return statusLine.toString();
    }

    private String generateHttpResponseHeaders() {

        return headers.keySet()
                .stream()
                .map(key -> {
                            final String headerValue = String.valueOf(headers.get(key));
                            return String.join(COLON, key, headerValue);
                        }
                ).collect(joining(CRLF));
    }

    private String generateHttpEntity() {
        return entity;
    }

    @Override
    public String toString() {
        return "Response{" +
                "statusLine=" + statusLine +
                ", headers=" + headers +
                ", entity='" + entity + '\'' +
                '}';
    }
}
