package site.ugaeng.localhosting.code.config;

import site.ugaeng.localhosting.env.TunnelPlatform;
import site.ugaeng.localhosting.exception.LocalhostingException;

import static site.ugaeng.localhosting.env.TunnelPlatform.RABBIT_MQ;
import static site.ugaeng.localhosting.env.TunnelPlatform.SOCKET;

public class TunnelConfigFactory {

    public static TunnelConfig getTunnelConfig(TunnelPlatform platform) {
        if (platform.equals(SOCKET)) {
            return new SocketTunnelConfig();
        }

        if (platform.equals(RABBIT_MQ)) {
            return new RabbitMQTunnelConfig();
        }

        throw new LocalhostingException("NOT SUPPORTED PLATFORM");
    }
}
