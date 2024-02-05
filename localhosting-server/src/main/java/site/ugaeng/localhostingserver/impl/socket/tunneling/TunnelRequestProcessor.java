package site.ugaeng.localhostingserver.impl.socket.tunneling;

import site.ugaeng.localhostingserver.impl.socket.tunneling.client.TunnelClient;

public interface TunnelRequestProcessor {

    void run(TunnelClient client);
}
