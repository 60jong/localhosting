package site.ugaeng.localhosting.forward;

import com.rabbitmq.client.Connection;
import site.ugaeng.localhosting.amqp.RabbitMQConnector;
import site.ugaeng.localhosting.amqp.RabbitMQConstant;
import site.ugaeng.localhosting.amqp.RabbitMQRequestForwarder;
import site.ugaeng.localhosting.client.LocalProcessClient;
import site.ugaeng.localhosting.http.forward.HttpRequestForwarder;
import site.ugaeng.localhosting.http.local.LocalRequests;

import static site.ugaeng.localhosting.amqp.RabbitMQConstant.DEFAULT_CONSUMERS_MAX;

public class RequestForwarders {

    public static HttpRequestForwarder getHttpRequestForwarder() {
        LocalProcessClient localRequestHttpClient = LocalRequests.getLocalRequestHttpClient();

        return new HttpRequestForwarder(localRequestHttpClient);
    }

    public static RabbitMQRequestForwarder getRabbitMQRequestForwarder() {
        Connection connection = RabbitMQConnector.getConnection(DEFAULT_CONSUMERS_MAX);
        LocalProcessClient localRequestHttpClient = LocalRequests.getLocalRequestHttpClient();

        return new RabbitMQRequestForwarder(connection, localRequestHttpClient);
    }

}
