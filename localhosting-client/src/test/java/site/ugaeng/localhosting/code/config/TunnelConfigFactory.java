package site.ugaeng.localhosting.code.config;

import site.ugaeng.localhosting.env.TunnelingPlatform;
import site.ugaeng.localhosting.exception.LocalhostingException;

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
