package site.ugaeng.localhostingserver.http;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static site.ugaeng.localhostingserver.http.HttpConstant.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public final class Request {

    private RequestLine requestLine;
    private Map<String, String> headers;
    private String entity;

    public static Request readFromHttpServletRequest(HttpServletRequest httpRequest) throws IOException {
        // HTTP 요청 라인 가져오기
        String httpRequestLine = String.join(SP,
                                             httpRequest.getMethod(),
                                             getActualUri(httpRequest),
                                             httpRequest.getProtocol());
        final RequestLine requestLine = RequestLineBuilder.build(httpRequestLine);

        // HTTP 요청 헤더 가져오기
        final Map<String, String> requestHeaders = new HashMap<>();
        httpRequest.getHeaderNames()
                   .asIterator()
                   .forEachRemaining(headerName -> {
                       requestHeaders.put(headerName, httpRequest.getHeader(headerName));
                   });

        // HTTP 요청 바디 가져오기
        final String requestBody = httpRequest.getReader()
                                              .lines()
                                              .collect(Collectors.joining());

        return new Request(requestLine, requestHeaders, requestBody);
    }

    private static String getActualUri(HttpServletRequest httpRequest) {
        String originUri = httpRequest.getRequestURI();
        int actualUriStartIdx = originUri.indexOf("host");

        final String actualUri = originUri.substring(actualUriStartIdx + 4);
        final String queryString = httpRequest.getQueryString();

        return addQueryStringIfExists(actualUri, queryString);
    }

    private static String addQueryStringIfExists(String actualUri, String queryString) {
        return queryString == null ? actualUri : actualUri + "?" + queryString;
    }
}
