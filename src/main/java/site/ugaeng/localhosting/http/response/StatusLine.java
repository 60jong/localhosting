package site.ugaeng.localhosting.http.response;

import lombok.Builder;
import lombok.Getter;
import site.ugaeng.localhosting.http.ProtocolVersion;

@Getter
@Builder
public class StatusLine {

    private ProtocolVersion version;
    private int statusCode;
    private String reasonPhrase;

    @Override
    public String toString() {
        final StringBuffer buffer = new StringBuffer();

        return buffer.append(version.getValue())
                     .append(" ")
                     .append(statusCode)
                     .append(" ")
                     .append(reasonPhrase)
                     .toString();
    }
}