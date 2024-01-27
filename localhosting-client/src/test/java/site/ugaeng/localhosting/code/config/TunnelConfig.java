package site.ugaeng.localhosting.code.config;

import site.ugaeng.localhosting.code.RequestForwardingService;
import site.ugaeng.localhosting.code.TunnelConnectionService;

public abstract class TunnelConfig {

    public RequestForwardingService requestForwardingService() {
        return null;
    }

    public abstract TunnelConnectionService tunnelConnectionService();
}
