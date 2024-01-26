package site.ugaeng.localhostingserver.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import site.ugaeng.localhostingserver.impl.amqp.AMQPTunnelingConfig;
import site.ugaeng.localhostingserver.impl.socket.SocketTunnelingConfig;

@Import(AMQPTunnelingConfig.class)
//@Import(SocketTunnelingConfig.class)
@Configuration
public class TunnelingConfig {
}

