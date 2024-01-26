package site.ugaeng.localhosting.amqp;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import site.ugaeng.localhosting.exception.LocalhostingException;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeoutException;

import static site.ugaeng.localhosting.env.Environment.getTunnelName;

public class RabbitMQConnector {

    private static final boolean QUEUE_AUTO_DELETE = false;

    private static ConnectionFactory connectionFactory;
    private static Connection connection;

    public static Connection getConnection(int maxThreads) {
        if (connection == null) {
            init(maxThreads);
        }
        return connection;
    }

    private static void init(int maxThreads) {
        connectionFactory = createConnectionFactory();

        connection = createConnection(maxThreads);

        declareRPCQueue();
    }

    private static Connection createConnection(int maxThreads) {
        try
        {
            return connectionFactory.newConnection(Executors.newFixedThreadPool(maxThreads));
        }
        catch (IOException e)
        {
            throw new LocalhostingException(e);
        }
        catch (TimeoutException te)
        {
            throw new LocalhostingException(te);
        }
    }

    private static ConnectionFactory createConnectionFactory() {
        connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");

        return connectionFactory;
    }

    private static void declareRPCQueue() {
        try (var channel = connection.createChannel()) {
            channel.queueDeclare("tunnel-" + getTunnelName(),
                    false,
                    false,
                    QUEUE_AUTO_DELETE,
                    null);
        } catch (IOException e) {
            throw new LocalhostingException(e);
        } catch (TimeoutException e) {
            throw new LocalhostingException(e);
        }
    }
}
