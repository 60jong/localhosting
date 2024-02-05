package site.ugaeng.localhostingserver.http.request.reader;

import jakarta.servlet.http.HttpServletRequest;
import site.ugaeng.localhostingserver.http.request.Request;
import site.ugaeng.localhostingserver.http.request.RequestLine;
import site.ugaeng.localhostingserver.http.request.RequestLineBuilder;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static site.ugaeng.localhostingserver.http.HttpConstant.*;

public class RequestReader {

    public static Request readFromHttpServletRequest(HttpServletRequest httpRequest) throws IOException {
        final RequestLine requestLine = getRequestLine(httpRequest);
        final Map<String, String> requestHeaders = getRequestHeaders(httpRequest);
        final String requestBody = getRequestBody(httpRequest);

        return new Request(requestLine, requestHeaders, requestBody);
    }

    private static RequestLine getRequestLine(HttpServletRequest httpRequest) {
        return RequestLineBuilder.build(String.join(SP,
                                                    httpRequest.getMethod(),
                                                    getActualUri(httpRequest),
                                                    httpRequest.getProtocol()));
    }

    private static Map<String, String> getRequestHeaders(HttpServletRequest request) {
        Map<String, String> headers = new HashMap<>();
        request.getHeaderNames()
               .asIterator()
               .forEachRemaining(headerName -> headers.put(headerName, request.getHeader(headerName)));

        return headers;
    }
    private static String getRequestBody(HttpServletRequest httpRequest) throws IOException {
        return httpRequest.getReader()
                          .lines()
                          .collect(Collectors.joining());
    }

    private static String getActualUri(HttpServletRequest httpRequest) {
        String originUri = httpRequest.getRequestURI();

        final String actualUri = getSubstringFromThirdSlash(originUri);
        final String queryString = httpRequest.getQueryString();

        return addQueryStringIfExists(actualUri, queryString);
    }

    private static String getSubstringFromThirdSlash(String originUri) {
        String[] resources = originUri.split(SEP);

        String[] originResources = resources.length > 2 ?
                Arrays.copyOfRange(resources, 3, resources.length) :
                new String[0];

        return SEP + String.join(SEP, originResources);
    }

    private static String addQueryStringIfExists(String actualUri, String queryString) {
        return queryString == null ? actualUri : actualUri + "?" + queryString;
    }
}
