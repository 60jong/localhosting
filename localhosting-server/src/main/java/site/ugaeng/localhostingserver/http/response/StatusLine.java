package site.ugaeng.localhostingserver.http.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import site.ugaeng.localhostingserver.http.ProtocolVersion;

import static site.ugaeng.localhostingserver.http.HttpConstant.SP;


@Getter
@NoArgsConstructor
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