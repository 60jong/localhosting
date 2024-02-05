package site.ugaeng.localhosting.tunnel.config;

import site.ugaeng.localhosting.tunnel.conn.TunnelConnectionService;
import site.ugaeng.localhosting.tunnel.forward.RequestForwardingService;

public abstract class TunnelConfig {

    public abstract RequestForwardingService requestForwardingService();

    public abstract TunnelConnectionService tunnelConnectionService();
}
