package site.ugaeng.localhosting.http.request;

import site.ugaeng.localhosting.http.ProtocolVersion;
import site.ugaeng.localhosting.http.local.LocalRequests;

import static site.ugaeng.localhosting.http.HttpConstant.SP;

public class RequestLineBuilder {

    public static RequestLine buildRequestLine(String line) {
        final String[] elements = line.split(SP);

        final var method = elements[0];

        final var uri = elements[1];
        final var localUri = LocalRequests.getLocalRequestHost() + uri;

        final var version = elements[2];

        return RequestLine.builder()
                          .method(method)
                          .uri(localUri)
                          .version(ProtocolVersion.of(version))
                          .build();
    }
}
