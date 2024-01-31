package site.ugaeng.localhosting.impl.socket.config;

import site.ugaeng.localhosting.config.TunnelConfig;
import site.ugaeng.localhosting.conn.TunnelConnectionService;
import site.ugaeng.localhosting.forward.RequestForwardingService;
import site.ugaeng.localhosting.impl.socket.conn.SocketTunnelConnectionService;

public class SocketTunnelConfig extends TunnelConfig {

    @Override
    public RequestForwardingService requestForwardingService() {
        return null;
    }

    @Override
    public TunnelConnectionService tunnelConnectionService() {
        return new SocketTunnelConnectionService();
    }
}
