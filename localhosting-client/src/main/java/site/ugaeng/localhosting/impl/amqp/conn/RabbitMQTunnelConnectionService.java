package site.ugaeng.localhosting.impl.amqp.conn;

import site.ugaeng.localhosting.conn.TunnelConnection;
import site.ugaeng.localhosting.conn.TunnelConnectionService;

import static site.ugaeng.localhosting.env.Environment.getRabbitMQWorkConsumers;

public class RabbitMQTunnelConnectionService implements TunnelConnectionService {

    @Override
    public TunnelConnection connect() {
        return new RabbitMQTunnelConnection(getRabbitMQWorkConsumers());
    }
}
