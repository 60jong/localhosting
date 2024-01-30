package site.ugaeng.localhosting.env;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ServerArgs {

    private HostingArgs hosting;

    public String getProxyServerAddr() {
        return hosting.getAddr()
                      .getProxyServerAddr();
    }

    public String getTunnelingServerAddr() {
        return hosting.getAddr()
                      .getTunnelingServerAddr();
    }

    public String getTunnelingPlatform() {
        return hosting.getTunneling()
                      .getPlatform();
    }

    public String getRabbitMqHost() {
        return hosting.getTunneling()
                .getRabbitmq()
                .getHost();
    }

    public int getRabbitMqPort() {
        return hosting.getTunneling()
                .getRabbitmq()
                .getPort();
    }

    public String getRabbitMqUsername() {
        return hosting.getTunneling()
                .getRabbitmq()
                .getUsername();
    }

    public String getRabbitMqPassword() {
        return hosting.getTunneling()
                .getRabbitmq()
                .getPassword();
    }

    @Getter @Setter
    public static class HostingArgs {
        private HostingAddress addr;
        private TunnelingArgs tunneling;
    }

    @Getter @Setter
    public static class HostingAddress {

        private String proxyServerAddr;
        private String tunnelingServerAddr;
    }

    @Getter @Setter
    public static class TunnelingArgs {

        private String platform;
        private RabbitMqArgs rabbitmq;
    }

    @Getter
    @Setter
    public class RabbitMqArgs {

        private String host;
        private int port;
        private String username;
        private String password;
    }
}
