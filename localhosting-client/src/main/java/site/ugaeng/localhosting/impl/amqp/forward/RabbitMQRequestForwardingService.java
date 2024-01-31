package site.ugaeng.localhosting.impl.amqp.forward;

import com.rabbitmq.client.Channel;
import site.ugaeng.localhosting.conn.TunnelConnection;
import site.ugaeng.localhosting.exception.LocalhostingException;
import site.ugaeng.localhosting.forward.RequestForwardingService;
import site.ugaeng.localhosting.impl.amqp.TunnelConsumer;
import site.ugaeng.localhosting.impl.amqp.conn.RabbitMQTunnelConnection;

import java.io.IOException;

import static site.ugaeng.localhosting.env.Environment.getTunnelName;

public class RabbitMQRequestForwardingService implements RequestForwardingService {

    @Override
    public void startForwarding(TunnelConnection connection) {
        startWorkConsumers((RabbitMQTunnelConnection) connection);
    }

    private void startWorkConsumers(RabbitMQTunnelConnection connection) {
        final int workConsumers = connection.getWorkConsumers();
        final String requestQueue = "tunnel-" + getTunnelName();

        // start consumers with sharing Thread Pool
        try
        {
            for (int i = 0; i < workConsumers; i++) {
                Channel channel = connection.createChannel();

                TunnelConsumer consumer = new TunnelConsumer(channel, processClient);

                channel.basicConsume(requestQueue,
                        false,
                        consumer);
            }
        }
        catch (IOException ioEx)
        {
            throw new LocalhostingException(ioEx);
        }
    }
}
