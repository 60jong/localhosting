package site.ugaeng.localhostingserver;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;

import static java.util.stream.Collectors.*;
import static site.ugaeng.localhostingserver.http.HttpConstant.*;

@Getter
@NoArgsConstructor
public final class Response {

    private StatusLine statusLine;
    private Map<String, String> headers;
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
