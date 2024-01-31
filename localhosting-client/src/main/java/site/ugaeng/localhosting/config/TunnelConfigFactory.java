package site.ugaeng.localhosting.config;

import site.ugaeng.localhosting.exception.LocalhostingException;
import site.ugaeng.localhosting.impl.amqp.config.RabbitMQTunnelConfig;
import site.ugaeng.localhosting.impl.socket.config.SocketTunnelConfig;

import static site.ugaeng.localhosting.env.TunnelingPlatform.RABBITMQ;
import static site.ugaeng.localhosting.env.TunnelingPlatform.SOCKET;

public class TunnelConfigFactory {

    public static TunnelConfig getTunnelConfig(String platform) {
        if (platform.equals(SOCKET)) {
            return new SocketTunnelConfig();
        }

        if (platform.equals(RABBITMQ)) {
            return new RabbitMQTunnelConfig();
        }

        throw new LocalhostingException("NOT SUPPORTED PLATFORM");
    }
}
