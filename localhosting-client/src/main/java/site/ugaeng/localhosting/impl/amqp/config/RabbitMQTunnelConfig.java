package site.ugaeng.localhosting.impl.amqp.config;

import site.ugaeng.localhosting.config.TunnelConfig;
import site.ugaeng.localhosting.conn.TunnelConnectionService;
import site.ugaeng.localhosting.forward.RequestForwardingService;
import site.ugaeng.localhosting.impl.amqp.forward.RabbitMQRequestForwardingService;
import site.ugaeng.localhosting.impl.amqp.conn.RabbitMQTunnelConnectionService;

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
