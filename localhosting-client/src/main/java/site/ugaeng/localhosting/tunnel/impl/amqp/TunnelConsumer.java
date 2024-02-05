package site.ugaeng.localhosting.tunnel.impl.amqp;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import lombok.extern.slf4j.Slf4j;
import site.ugaeng.localhosting.client.LocalProcessClient;
import site.ugaeng.localhosting.exception.LocalhostingException;
import site.ugaeng.localhosting.http.request.Request;
import site.ugaeng.localhosting.http.response.Response;
import site.ugaeng.localhosting.util.ObjectUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static java.nio.charset.StandardCharsets.UTF_8;
import static site.ugaeng.localhosting.util.ObjectUtils.*;

@Slf4j
public class TunnelConsumer extends DefaultConsumer {

    private final LocalProcessClient processClient;
    private final Channel channel;
    /**
     * Constructs a new instance and records its association to the passed-in channel.
     *
     * @param channel the channel to which this consumer is attached
     */

    public TunnelConsumer(Channel channel, LocalProcessClient processClient) {
        super(channel);

        this.processClient = processClient;
        this.channel = channel;
    }

    @Override
    public void handleDelivery(String consumerTag, Envelope envelope, BasicProperties properties, byte[] body) {
        String replyQueue = properties.getReplyTo();
        BasicProperties replyProps = buildReplyBasicProperties(properties);
        String responseData = getResponseData(body);

        try {
            channel.basicPublish("",
                                 replyQueue,
                                 replyProps,
                                 responseData.getBytes(UTF_8));

            channel.basicAck(envelope.getDeliveryTag(), false);
        } catch (IOException ioEx) {
            throw new LocalhostingException(ioEx);
        }

    }

    private String getResponseData(byte[] body) {
        Request request = getRequest(body);
        log.info("request received : Request [{}]", request);

        Response response = processClient.performRequest(request);
        String convertedResponse = convertToString(response);
        return convertedResponse;
    }

    private static BasicProperties buildReplyBasicProperties(BasicProperties properties) {
        return new BasicProperties()
                .builder()
                .correlationId(properties.getCorrelationId())
                .build();
    }

    private static Request getRequest(byte[] body) {
        String deliveredData = new String(body, UTF_8);
        Request request = convertToObject(deliveredData, Request.class);
        return request;
    }
}
