package site.ugaeng.localhosting.http.local.response;

import lombok.Builder;
import lombok.Getter;
import site.ugaeng.localhosting.http.response.StatusLine;

import java.util.Map;

import static java.util.stream.Collectors.*;
import static site.ugaeng.localhosting.http.HttpConstant.*;

@Getter
@Builder
public final class LocalResponse {

    private StatusLine statusLine;
    private Map<String, Object> headers;
    private String entity;

    public String generateHttpResponse() {

        final String httpStatusLine = generateHttpStatusLine();
        final String httpResponseHeaders = generateHttpResponseHeaders();
        final String httpEntity = generateHttpEntity();

        return String.join(CRLF, httpStatusLine, httpResponseHeaders, EMPTY, httpEntity);
    }

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
}
