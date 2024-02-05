package site.ugaeng.localhosting.tunnel.forward;

import site.ugaeng.localhosting.client.LocalProcessClient;
import site.ugaeng.localhosting.tunnel.conn.TunnelConnection;
import site.ugaeng.localhosting.http.local.LocalRequests;

public interface RequestForwardingService {

    LocalProcessClient processClient = LocalRequests.getLocalRequestHttpClient();

    void startForwarding(TunnelConnection connection);
}
