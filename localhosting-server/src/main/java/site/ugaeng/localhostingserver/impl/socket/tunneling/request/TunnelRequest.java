package site.ugaeng.localhostingserver.impl.socket.tunneling.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import site.ugaeng.localhostingserver.impl.socket.tunneling.TunnelClient;

import static site.ugaeng.localhostingserver.utils.ObjectUtils.convertToObject;

@Getter
@NoArgsConstructor
public class TunnelRequest {

    private String type;
    private String tunnelName;

    public static TunnelRequest readFromTunnelClient(TunnelClient client) {
        return convertToObject(client.getClientReader().readLine(),
                               TunnelRequest.class);
    }
}
