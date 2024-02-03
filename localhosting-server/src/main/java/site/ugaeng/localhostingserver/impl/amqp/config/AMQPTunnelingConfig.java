package site.ugaeng.localhostingserver.impl.amqp.config;

import jakarta.annotation.PostConstruct;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import site.ugaeng.localhostingserver.forward.RequestForwarder;
import site.ugaeng.localhostingserver.impl.amqp.AMQPTunnelingRequestForwarder;
import site.ugaeng.localhostingserver.tunnel.repository.TunnelRepository;
import site.ugaeng.localhostingserver.tunnel.service.RabbitMQTunnelService;
import site.ugaeng.localhostingserver.tunnel.service.TunnelService;

@Profile("rabbitmq")
@Configuration
public class AMQPTunnelingConfig {

    @PostConstruct
    void init() {
        System.out.println("AMPQ");
    }

    @Value("${spring.rabbitmq.host}")
    private String rabbitmqHost;

    @Value("${spring.rabbitmq.port}")
    private int rabbitmqPort;

    @Value("${spring.rabbitmq.username}")
    private String rabbitmqUsername;

    @Value("${spring.rabbitmq.password}")
    private String rabbitmqPassword;

    /* Caching / ThreadChannel / PoolChannel */
    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setHost(rabbitmqHost);
        connectionFactory.setPort(rabbitmqPort);
        connectionFactory.setUsername(rabbitmqUsername);
        connectionFactory.setPassword(rabbitmqPassword);

        return connectionFactory;
    }

    @Bean
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory());
        rabbitTemplate.setMessageConverter(jackson2JsonMessageConverter());

        return rabbitTemplate;
    }

    @Bean
    public MessageConverter jackson2JsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RequestForwarder requestForwarder() {
        return new AMQPTunnelingRequestForwarder(rabbitTemplate());
    }

    @Bean
    public TunnelService tunnelService(TunnelRepository tunnelRepository) {
        return new RabbitMQTunnelService(tunnelRepository, rabbitTemplate());
    }
}
