package site.ugaeng.localhosting.http.request;

import lombok.Builder;
import lombok.Getter;
import site.ugaeng.localhosting.http.ProtocolVersion;

import static site.ugaeng.localhosting.http.HttpConstant.SP;

@Getter
@Builder
public class RequestLine {

    private String method;
    private String uri;
    private ProtocolVersion version;

    public static RequestLine from(String line) {
        final String[] elements = line.split(SP);

        final var method = elements[0];
        final var uri = elements[1];
        final var version = elements[2];

        return builder()
                .method(method)
                .uri("http://localhost:8080" + uri)
                .version(ProtocolVersion.of(version))
                .build();
    }

    @Override
    public String toString() {
        final StringBuffer buffer = new StringBuffer();

        return buffer.append(method)
                     .append(SP)
                     .append(uri)
                     .append(SP)
                     .append(version.getValue())
                     .append(SP)
                     .toString();
    }

}
