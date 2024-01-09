package site.ugaeng.localhosting.http.client;

import lombok.RequiredArgsConstructor;
import site.ugaeng.localhosting.http.HttpConstant;
import site.ugaeng.localhosting.http.ProtocolVersion;
import site.ugaeng.localhosting.http.request.Request;
import site.ugaeng.localhosting.http.request.RequestLine;
import site.ugaeng.localhosting.http.response.Response;
import site.ugaeng.localhosting.http.response.StatusLine;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.net.http.HttpClient.*;
import static java.net.http.HttpResponse.*;
import static site.ugaeng.localhosting.http.HttpConstant.*;

@RequiredArgsConstructor
public class HttpRequestClient implements RequestClient {

    private final HttpClient client;

    @Override
    public Response performRequest(Request request) {
        final HttpRequest httpRequest = buildHttpRequest(request);

        try
        {
            HttpResponse<String> httpResponse = client.send(httpRequest, BodyHandlers.ofString());

            return Response.builder()
                    .statusLine(generateHttpResponse(httpResponse))
                    .headers(flattenValuedMap(httpResponse.headers()))
                    .entity(httpResponse.body())
                    .build();
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }

    private Map<String, ? extends Object> flattenValuedMap(HttpHeaders headers) {
        final Map<String, String> flattenValuedMap = new HashMap<>();

        Map<String, List<String>> headerMap = headers.map();

        headerMap.keySet()
                .stream()
                .forEach(key -> {
                    final var valueSequence = String.join(SEMI_COLON, headerMap.get(key));
                    flattenValuedMap.put(key, valueSequence);
                });

        return flattenValuedMap;
    }

    private StatusLine generateHttpResponse(HttpResponse<String> httpResponse) {
        final String version = httpResponse.version().name();
        final int statusCode = httpResponse.statusCode();

        return StatusLine.builder()
                .version(ProtocolVersion.of(version))
                .statusCode(statusCode)
                .reasonPhrase("OK")
                .build();
    }

    private HttpRequest buildHttpRequest(Request request) {
        final RequestLine requestLine = request.getRequestLine();

        final String method = requestLine.getMethod();
        final String uri = requestLine.getUri();
        final String version = requestLine.getVersion().getValue();

        return HttpRequest.newBuilder()
                          .method(method, HttpRequest.BodyPublishers.noBody())
                          .uri(URI.create(uri))
                          .version(getVersion(version))
                          .build();
    }

    private Version getVersion(String version) {

        if (version.equals("HTTP/1.1")) {
            return Version.HTTP_1_1;
        }

        return null;
    }
}

