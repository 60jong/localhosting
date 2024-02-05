package site.ugaeng.localhosting.tunnel.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TunnelRequest {

    private String type;
    private String tunnelName;
}
