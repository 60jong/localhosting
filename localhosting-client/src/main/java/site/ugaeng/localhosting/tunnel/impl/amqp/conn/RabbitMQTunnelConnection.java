package site.ugaeng.localhosting.tunnel.impl.amqp.conn;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import lombok.Getter;
import site.ugaeng.localhosting.tunnel.conn.TunnelConnection;
import site.ugaeng.localhosting.exception.LocalhostingException;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeoutException;

import static site.ugaeng.localhosting.env.Environment.*;

@Getter
public class RabbitMQTunnelConnection implements TunnelConnection {

    private final int workConsumers;
    private final Connection connection;

    public RabbitMQTunnelConnection(int workConsumers) {
        this.workConsumers = workConsumers;

        ConnectionFactory connectionFactory = buildConnectionFactory();
        this.connection = createConnection(connectionFactory, workConsumers);
    }

    private ConnectionFactory buildConnectionFactory() {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(getRabbitMQHost());
        factory.setPort(getRabbitMQPORT());
        factory.setUsername(getRabbitMQUsername());
        factory.setPassword(getRabbitMQPassword());

        return factory;
    }

    private Connection createConnection(ConnectionFactory factory, int workConsumers) {
        try {
            return factory.newConnection(Executors.newFixedThreadPool(workConsumers));
        } catch (IOException e) {
            throw new LocalhostingException(e);
        } catch (TimeoutException e) {
            throw new LocalhostingException(e);
        }
    }

    public Channel createChannel() {
        try {
            return connection.createChannel();
        } catch (IOException e) {
            throw new LocalhostingException(e);
        }
    }
}
