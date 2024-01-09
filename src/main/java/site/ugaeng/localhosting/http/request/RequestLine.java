package site.ugaeng.localhosting.http.request;

import lombok.Builder;
import lombok.Getter;
import site.ugaeng.localhosting.http.ProtocolVersion;

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
                     .append(" ")
                     .append(uri)
                     .append(" ")
                     .append(version.getValue())
                     .append(" ")
                     .toString();
    }

}
