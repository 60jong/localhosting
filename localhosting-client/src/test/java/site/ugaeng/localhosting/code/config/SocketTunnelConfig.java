package site.ugaeng.localhosting.code.config;

import site.ugaeng.localhosting.code.SocketTunnelConnectionService;
import site.ugaeng.localhosting.code.TunnelConnectionService;
import site.ugaeng.localhosting.env.TunnelArgs;

public class SocketTunnelConfig extends TunnelConfig {

    @Override
    public TunnelConnectionService tunnelConnectionService() {
        return new SocketTunnelConnectionService();
    }
}
