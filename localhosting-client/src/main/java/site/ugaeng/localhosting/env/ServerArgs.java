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

    public String getRabbitMQHost() {
        return hosting.getTunneling()
                .getRabbitmq()
                .getHost();
    }

    public int getRabbitMQPort() {
        return hosting.getTunneling()
                .getRabbitmq()
                .getPort();
    }

    public String getRabbitMQUsername() {
        return hosting.getTunneling()
                .getRabbitmq()
                .getUsername();
    }

    public String getRabbitMQPassword() {
        return hosting.getTunneling()
                .getRabbitmq()
                .getPassword();
    }

    public int getRabbitMQWorkConsumers() {
        return hosting.getTunneling()
                .getRabbitmq()
                .getWorkConsumers();
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
        private RabbitMQArgs rabbitmq;
    }

    @Getter @Setter
    public static class RabbitMQArgs {

        private String host;
        private Integer port;
        private String username;
        private String password;
        private Integer workConsumers;
    }
}
