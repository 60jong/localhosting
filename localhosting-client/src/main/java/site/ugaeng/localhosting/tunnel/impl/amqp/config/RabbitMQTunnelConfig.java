package site.ugaeng.localhosting.tunnel.impl.amqp.config;

import site.ugaeng.localhosting.tunnel.config.TunnelConfig;
import site.ugaeng.localhosting.tunnel.conn.TunnelConnectionService;
import site.ugaeng.localhosting.tunnel.forward.RequestForwardingService;
import site.ugaeng.localhosting.tunnel.impl.amqp.forward.RabbitMQRequestForwardingService;
import site.ugaeng.localhosting.tunnel.impl.amqp.conn.RabbitMQTunnelConnectionService;

public class RabbitMQTunnelConfig extends TunnelConfig {

    @Override
    public RequestForwardingService requestForwardingService() {
        return new RabbitMQRequestForwardingService();
    }

    @Override
    public TunnelConnectionService tunnelConnectionService() {
        return new RabbitMQTunnelConnectionService();
    }
}
