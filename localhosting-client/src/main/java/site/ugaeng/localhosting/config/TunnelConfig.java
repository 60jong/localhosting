package site.ugaeng.localhosting.config;

import site.ugaeng.localhosting.conn.TunnelConnectionService;
import site.ugaeng.localhosting.forward.RequestForwardingService;

public abstract class TunnelConfig {

    public abstract RequestForwardingService requestForwardingService();

    public abstract TunnelConnectionService tunnelConnectionService();
}
