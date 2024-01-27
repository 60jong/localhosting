package site.ugaeng.localhosting.code.config;

import site.ugaeng.localhosting.code.RabbitMQTunnelConnectionService;
import site.ugaeng.localhosting.code.TunnelConnectionService;
import site.ugaeng.localhosting.env.TunnelArgs;

public class RabbitMQTunnelConfig extends TunnelConfig {

    @Override
    public TunnelConnectionService tunnelConnectionService() {
        return new RabbitMQTunnelConnectionService();
    }
}
