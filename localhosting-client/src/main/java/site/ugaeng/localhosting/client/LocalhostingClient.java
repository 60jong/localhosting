package site.ugaeng.localhosting.client;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import site.ugaeng.localhosting.tunnel.TunnelRegisterer;
import site.ugaeng.localhosting.tunnel.conn.TunnelConnection;
import site.ugaeng.localhosting.tunnel.conn.TunnelConnectionService;
import site.ugaeng.localhosting.tunnel.forward.RequestForwardingService;

import static site.ugaeng.localhosting.env.Environment.getTunnelName;

@Slf4j
@RequiredArgsConstructor
public class LocalhostingClient {

    private final TunnelConnectionService connectionService;
    private final RequestForwardingService forwardingService;

    public void start() {
        registerTunnel();
        log.info("now you can use your domain : http://{}.localhosting.do-main.site + REQUEST_URI", getTunnelName());
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
