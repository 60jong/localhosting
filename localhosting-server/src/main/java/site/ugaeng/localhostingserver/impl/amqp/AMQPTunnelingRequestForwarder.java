package site.ugaeng.localhostingserver.impl.amqp;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import site.ugaeng.localhostingserver.forward.RequestForwarder;
import site.ugaeng.localhostingserver.http.request.Request;
import site.ugaeng.localhostingserver.http.response.Response;
import site.ugaeng.localhostingserver.tunnel.domain.Tunnel;

import static java.nio.charset.StandardCharsets.UTF_8;
import static site.ugaeng.localhostingserver.utils.ObjectUtils.*;

@Slf4j
@RequiredArgsConstructor
public class AMQPTunnelingRequestForwarder implements RequestForwarder {

    private final RabbitTemplate rabbitTemplate;

    @Override
    public Response forwardRequestForTunnel(Tunnel tunnel, Request request) {

        Message message = MessageBuilder
                .withBody(convertToString(request).getBytes(UTF_8))
                .build();

        Message response = rabbitTemplate.sendAndReceive("tunnel-" + tunnel.getName(), message);

        String result = new String(response.getBody(), UTF_8);
        return convertToObject(result, Response.class);
    }
}
