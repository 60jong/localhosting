package site.ugaeng.localhostingserver.http;

import jakarta.servlet.ServletRequest;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import site.ugaeng.localhostingserver.http.RequestLine;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static site.ugaeng.localhostingserver.http.HttpConstant.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public final class Request {

    private RequestLine requestLine;
    private Map<String, Object> headers;
    private String entity;

    public static Request readFromHttpServletRequest(HttpServletRequest httpRequest) throws IOException {
        // HTTP 요청 라인 가져오기
        String httpRequestLine = String.join(SP, httpRequest.getMethod(), httpRequest.getRequestURI(), httpRequest.getProtocol());
        final RequestLine requestLine = RequestLineBuilder.buildRequestLine(httpRequestLine);

        // HTTP 요청 헤더 가져오기
        final Map<String, Object> requestHeaders = new HashMap<>();
        Enumeration<String> headerNames = httpRequest.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            String headerValue = httpRequest.getHeader(headerName);
            requestHeaders.put(headerName, headerValue);
        }

        // HTTP 요청 바디 가져오기
        final String requestBody = httpRequest.getReader().lines()
                .collect(Collectors.joining(System.lineSeparator()));

        return new Request(requestLine, requestHeaders, requestBody);
    }
}
