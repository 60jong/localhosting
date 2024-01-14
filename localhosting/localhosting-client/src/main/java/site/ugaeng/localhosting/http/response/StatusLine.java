package site.ugaeng.localhosting.http.response;

import lombok.Builder;
import lombok.Getter;
import site.ugaeng.localhosting.http.HttpConstant;
import site.ugaeng.localhosting.http.ProtocolVersion;

import static site.ugaeng.localhosting.http.HttpConstant.*;

@Getter
@Builder
public class StatusLine {

    private ProtocolVersion version;
    private int statusCode;
    private String reasonPhrase;

    @Override
    public String toString() {

        return String.join(SP,
                           version.getValue(),
                           String.valueOf(statusCode),
                           reasonPhrase);
    }
}