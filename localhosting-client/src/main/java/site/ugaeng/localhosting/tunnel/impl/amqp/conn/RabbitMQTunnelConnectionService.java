package site.ugaeng.localhosting.tunnel.impl.amqp.conn;

import site.ugaeng.localhosting.tunnel.conn.TunnelConnection;
import site.ugaeng.localhosting.tunnel.conn.TunnelConnectionService;

import static site.ugaeng.localhosting.env.Environment.getRabbitMQWorkConsumers;

public class RabbitMQTunnelConnectionService implements TunnelConnectionService {

    @Override
    public TunnelConnection connect() {
        return new RabbitMQTunnelConnection(getRabbitMQWorkConsumers());
    }
}
