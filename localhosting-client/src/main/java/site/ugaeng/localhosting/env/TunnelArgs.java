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

    public TunnelArgs(ClientArgs clientArgs, ServerArgs serverArgs) {
        // client args
        this.localProcessPort = clientArgs.getLocalProcessPort();
        this.tunnelName = clientArgs.getTunnelName();

        // server args
        this.tunnelingPlatform = serverArgs.getTunnelingPlatform();
        this.proxyServerAddr = serverArgs.getProxyServerAddr();
        this.tunnelingServerAddr = serverArgs.getTunnelingServerAddr();
    }
}
