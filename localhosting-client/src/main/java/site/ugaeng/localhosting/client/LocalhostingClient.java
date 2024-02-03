package site.ugaeng.localhosting.client;

import lombok.RequiredArgsConstructor;
import site.ugaeng.localhosting.tunnel.TunnelRegisterer;
import site.ugaeng.localhosting.tunnel.conn.TunnelConnection;
import site.ugaeng.localhosting.tunnel.conn.TunnelConnectionService;
import site.ugaeng.localhosting.tunnel.forward.RequestForwardingService;

import static site.ugaeng.localhosting.env.Environment.getTunnelName;

@RequiredArgsConstructor
public class LocalhostingClient {

    private final TunnelConnectionService connectionService;
    private final RequestForwardingService forwardingService;

    public void start() {
        registerTunnel();

        forwardRequestFromTunnel();
    }

    private void registerTunnel() {
        TunnelRegisterer registerer = new TunnelRegisterer();
        registerer.registerTunnel(getTunnelName());
    }

    private void forwardRequestFromTunnel() {
        TunnelConnection connection = connectionService.connect();
        forwardingService.startForwarding(connection);
    }
}
