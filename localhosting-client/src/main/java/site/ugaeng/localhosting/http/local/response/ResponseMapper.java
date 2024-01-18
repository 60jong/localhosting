package site.ugaeng.localhosting.http.local.response;

import site.ugaeng.localhosting.http.ProtocolVersion;
import site.ugaeng.localhosting.http.local.response.Response;
import site.ugaeng.localhosting.http.response.StatusLine;

import java.net.http.HttpHeaders;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static site.ugaeng.localhosting.http.HttpConstant.SEMI_COLON;
import static site.ugaeng.localhosting.http.HttpConstant.TRANSFER_ENCODING;

public class ResponseMapper {

    public static Response mapToResponse(HttpResponse<String> httpResponse) {

        final StatusLine statusLine = buildStatusLine(httpResponse);
        final Map<String, String> headers = flattenValuedMap(httpResponse.headers());
        removeUnsupportedHeaders(headers);

        final String body = httpResponse.body();

        return new Response(statusLine, headers, body);
    }

    private static Map<String, String> flattenValuedMap(HttpHeaders headers) {
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

    private static void removeUnsupportedHeaders(Map<String, String> flattenValuedMap) {
        // remove [Transfer-Encoding: chunked]
        flattenValuedMap.remove(TRANSFER_ENCODING);
    }

    private static StatusLine buildStatusLine(HttpResponse<String> response) {
        final String version = response.version().name();
        final int statusCode = response.statusCode();

        return new StatusLine(ProtocolVersion.of(version), statusCode, "OK");
    }

}