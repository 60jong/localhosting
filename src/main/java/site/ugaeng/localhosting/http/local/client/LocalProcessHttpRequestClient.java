package site.ugaeng.localhosting.http.client;

import lombok.RequiredArgsConstructor;
import site.ugaeng.localhosting.http.ProtocolVersion;
import site.ugaeng.localhosting.http.request.LocalRequest;
import site.ugaeng.localhosting.http.request.RequestLine;
import site.ugaeng.localhosting.http.response.LocalResponse;
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
public class LocalProcessHttpRequestClient implements LocalProcessRequestClient {

    private final HttpClient client;

    @Override
    public LocalResponse performLocalRequest(LocalRequest request) {
        try
        {
            final HttpRequest httpRequest = HttpRequestBuilder.buildHttpRequest(request);

            HttpResponse<String> httpResponse = client.send(httpRequest, BodyHandlers.ofString());

            return LocalResponseMapper.mapLocalResponse(httpResponse);
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }

    private static class HttpRequestBuilder {

        private static HttpRequest buildHttpRequest(LocalRequest request) {
            // HTTP RequestLine
            final RequestLine requestLine = request.getRequestLine();

            final String method = requestLine.getMethod();
            final String uri = requestLine.getUri();
            final String version = requestLine.getVersion().getValue();

            // TODO : request header 추가
            // HTTP RequestHeaders

            return HttpRequest.newBuilder()
                    .method(method, HttpRequest.BodyPublishers.noBody())
                    .uri(URI.create(uri))
                    .version(getVersion(version))
                    .build();
        }

        private static Version getVersion(String version) {

            if (version.equals("HTTP/1.1")) {
                return Version.HTTP_1_1;
            }

            return null;
        }
    }

    private static class LocalResponseMapper {

        private static LocalResponse mapLocalResponse(HttpResponse<String> httpResponse) {
            return LocalResponse.builder()
                    .statusLine(buildStatusLine(httpResponse))
                    .headers(flattenValuedMap(httpResponse.headers()))
                    .entity(httpResponse.body())
                    .build();
        }

        private static Map<String, Object> flattenValuedMap(HttpHeaders headers) {
            final Map<String, Object> flattenValuedMap = new HashMap<>();

            Map<String, List<String>> headerMap = headers.map();

            headerMap.keySet()
                    .stream()
                    .forEach(key -> {
                        final var valueSequence = String.join(SEMI_COLON, headerMap.get(key));
                        flattenValuedMap.put(key, valueSequence);
                    });

            // remove [Transfer-Encoding: chunked]
            System.out.println(flattenValuedMap);
            flattenValuedMap.remove("transfer-encoding");
            return flattenValuedMap;
        }

        private static StatusLine buildStatusLine(HttpResponse<String> httpResponse) {
            final String version = httpResponse.version().name();
            final int statusCode = httpResponse.statusCode();

            return StatusLine.builder()
                    .version(ProtocolVersion.of(version))
                    .statusCode(statusCode)
                    .reasonPhrase("OK")
                    .build();
        }

    }
}

