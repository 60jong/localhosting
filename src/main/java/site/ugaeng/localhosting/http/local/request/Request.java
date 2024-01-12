package site.ugaeng.localhosting.http.local.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import site.ugaeng.localhosting.http.request.RequestLine;
import site.ugaeng.localhosting.http.request.RequestLineBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import static site.ugaeng.localhosting.http.HttpConstant.*;
import static site.ugaeng.localhosting.util.StringUtils.hasText;

@Getter
@AllArgsConstructor
public final class Request {

    private RequestLine requestLine;
    private Map<String, Object> headers;
    private String entity;

    public static Request readFromReader(BufferedReader reader) throws IOException {
        final RequestLine requestLine = readRequestLine(reader);
        final Map<String, Object> requestHeaders = readHeaders(reader);
        final String requestEntity = readEntityIfContentExists(reader, requestHeaders);

        return new Request(requestLine, requestHeaders, requestEntity);
    }

    private static RequestLine readRequestLine(BufferedReader reader) throws IOException {
        final String requestLine = reader.readLine();

        return RequestLineBuilder.buildRequestLine(requestLine);
    }

    private static Map<String, Object> readHeaders(BufferedReader reader) throws IOException {
        Map<String, Object> requestHeaders = new HashMap<>();

        String line = null;
        while (hasText((line = reader.readLine()))) {
            String[] headerAndValue = line.split(COLON);

            final var header = headerAndValue[0].trim();
            final var value = headerAndValue[1].trim();

            requestHeaders.put(header, value);
        }

        return requestHeaders;
    }

    private static String readEntityIfContentExists(BufferedReader reader, Map<String, Object> requestHeaders) throws IOException {
        if (contentExists(requestHeaders)) {
            return "";
        }

        int byteContentLength = Integer.parseInt(String.valueOf(requestHeaders.get("Content-Length")));
        char bodyChars[] = new char[byteContentLength];
        reader.read(bodyChars, 0, byteContentLength);

        return String.copyValueOf(bodyChars);
    }

    private static boolean contentExists(Map<String, Object> requestHeaders) {
        return !requestHeaders.containsKey("Content-Length") || Integer.parseInt(String.valueOf(requestHeaders.get("Content-Length"))) == 0;
    }
}
