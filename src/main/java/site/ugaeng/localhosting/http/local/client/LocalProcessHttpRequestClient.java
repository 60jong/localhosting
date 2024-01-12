package site.ugaeng.localhosting.http.local.client;

import site.ugaeng.localhosting.http.local.request.Request;
import site.ugaeng.localhosting.http.local.response.ResponseMapper;
import site.ugaeng.localhosting.http.request.RequestLine;
import site.ugaeng.localhosting.http.local.response.Response;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;

import static java.net.http.HttpClient.*;
import static java.net.http.HttpResponse.*;

public class LocalProcessHttpRequestClient implements LocalProcessRequestClient {

    private static LocalProcessHttpRequestClient instance;

    private final HttpClient client;

    private LocalProcessHttpRequestClient() {
        client = createHttpClient();
    }

    private HttpClient createHttpClient() {
        allowRestrictedHeaders();

        return HttpClient.newHttpClient();
    }

    private static void allowRestrictedHeaders() {
        System.setProperty("jdk.httpclient.allowRestrictedHeaders", "connection,host,content-length");
    }

    public static LocalProcessHttpRequestClient getInstance() {
        if (instance == null) {
            instance = new LocalProcessHttpRequestClient();
        }

        return instance;
    }

    @Override
    public Response performRequest(Request request) {
        try {
            final HttpRequest httpRequest = HttpRequestBuilder.buildHttpRequest(request);

            HttpResponse<String> httpResponse = client.send(httpRequest, BodyHandlers.ofString());

            return ResponseMapper.mapToResponse(httpResponse);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static class HttpRequestBuilder {

        private static HttpRequest buildHttpRequest(Request request) {
            // HTTP RequestLine
            final RequestLine requestLine = request.getRequestLine();

            final String method = requestLine.getMethod();
            final String uri = requestLine.getUri();
            final String version = requestLine.getVersion().getValue();

            // HTTP RequestHeaders
            final Map<String, Object> requestHeaders = request.getHeaders();

            // HTTP Body
            final String body = request.getEntity();

            HttpRequest.Builder builder = HttpRequest.newBuilder();
            setUri(builder, uri);
            setVersion(builder, version);
            setHeaders(builder, requestHeaders);
            setMethodAndBody(builder, method, body);
            return builder.build();
        }

        private static void setUri(HttpRequest.Builder builder, String uri) {
            builder.uri(URI.create(uri));
        }

        private static void setVersion(HttpRequest.Builder builder, String version) {
            builder.version(getVersion(version));
        }

        private static Version getVersion(String version) {
            if (version.equals("HTTP/1.1")) {
                return Version.HTTP_1_1;
            }

            if (version.equals("HTTP/2")) {
                return Version.HTTP_2;
            }

            return null;
        }

        private static void setHeaders(HttpRequest.Builder builder, Map<String, Object> requestHeaders) {
            for (var header : requestHeaders.entrySet()) {
                builder.header(header.getKey(), (String) header.getValue());
            }
        }

        private static void setMethodAndBody(HttpRequest.Builder builder, String method, String body) {
            if (hasNoBody(method)) {
                builder.method(method, HttpRequest.BodyPublishers.noBody());
                return;
            }

            if (hasBody(method)) {
                builder.method(method, HttpRequest.BodyPublishers.ofString(body));
                return;
            }

            throw new RuntimeException("Unsupported HTTP Method");
        }

        private static boolean hasBody(String method) {
            return method.equals("POST") || method.equals("PUT");
        }

        private static boolean hasNoBody(String method) {
            return method.equals("GET") || method.equals("DELETE");
        }
    }
}

