package site.ugaeng.localhosting.tunnel.impl.socket.config;

import site.ugaeng.localhosting.tunnel.config.TunnelConfig;
import site.ugaeng.localhosting.tunnel.conn.TunnelConnectionService;
import site.ugaeng.localhosting.tunnel.forward.RequestForwardingService;
import site.ugaeng.localhosting.tunnel.impl.socket.conn.SocketTunnelConnectionService;
import site.ugaeng.localhosting.tunnel.impl.socket.forward.SocketRequestForwardingService;

public class SocketTunnelConfig extends TunnelConfig {

    @Override
    public RequestForwardingService requestForwardingService() {
        return new SocketRequestForwardingService();
    }

    @Override
    public TunnelConnectionService tunnelConnectionService() {
        return new SocketTunnelConnectionService();
    }
}
