package site.ugaeng.localhosting.env;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TunnelArgs {

    private int localProcessPort;
    private String tunnelName;
    private String tunnelingPlatform;
    private String proxyServerAddr;
    private String tunnelingServerAddr;
    private String rmqHost;
    private int rmqPort;
    private String rmqUsername;
    private String rmqPassword;

    public TunnelArgs(ClientArgs clientArgs, ServerArgs serverArgs) {
        // client args
        this.localProcessPort = clientArgs.getLocalProcessPort();
        this.tunnelName = clientArgs.getTunnelName();

        // server args
        this.tunnelingPlatform = serverArgs.getTunnelingPlatform();
        this.proxyServerAddr = serverArgs.getProxyServerAddr();
        this.tunnelingServerAddr = serverArgs.getTunnelingServerAddr();
        this.rmqHost = serverArgs.getRabbitMqHost();
        this.rmqPort = serverArgs.getRabbitMqPort();
        this.rmqUsername = serverArgs.getRabbitMqUsername();
        this.rmqPassword = serverArgs.getRabbitMqPassword();
    }
}
