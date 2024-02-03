package site.ugaeng.localhostingserver.tunnel.service;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.ugaeng.localhostingserver.tunnel.TunnelRegisterRequest;
import site.ugaeng.localhostingserver.tunnel.domain.Address;
import site.ugaeng.localhostingserver.tunnel.domain.Tunnel;
import site.ugaeng.localhostingserver.tunnel.repository.TunnelRepository;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@RequiredArgsConstructor
@Service
public class RabbitMQTunnelService implements TunnelService {

    private final TunnelRepository tunnelRepository;
    private final RabbitTemplate rabbitTemplate;

    @Transactional
    @Override
    public void create(String remoteAddr, int remotePort, TunnelRegisterRequest request) {
        saveTunnel(remoteAddr, remotePort, request);

        createTunnelQueue(request.getTunnelName());
    }

    private void createTunnelQueue(String tunnelName) {
        ConnectionFactory connectionFactory = rabbitTemplate.getConnectionFactory();

        try (var conn = connectionFactory.createConnection();
             var channel = conn.createChannel(true))
        {
            channel.queueDeclare("tunnel-" + tunnelName,
                    false,
                    false,
                    true,
                    null);
        } catch (IOException | TimeoutException e) {
            throw new RuntimeException(e);
        }
    }

    private void saveTunnel(String remoteAddr, int remotePort, TunnelRegisterRequest request) {
        tunnelRepository.save(new Tunnel(request.getTunnelName(), new Address(remoteAddr, remotePort)));
    }

    @Override
    public Tunnel findByName(String tunnelName) {
        return tunnelRepository.findByName(tunnelName)
                .orElseThrow();
    }

}
