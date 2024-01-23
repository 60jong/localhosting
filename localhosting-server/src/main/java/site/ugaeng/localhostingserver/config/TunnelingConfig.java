package site.ugaeng.localhostingserver.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

//@Import(MQTunnelingConfig.class)
@Import(SocketTunnelingConfig.class)
@Configuration
public class TunnelingConfig {
}

