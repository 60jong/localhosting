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
