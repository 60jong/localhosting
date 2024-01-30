package site.ugaeng.localhosting.code;

import site.ugaeng.localhosting.code.config.TunnelConfig;
import site.ugaeng.localhosting.code.config.TunnelConfigFactory;
import site.ugaeng.localhosting.env.ClientArgs;
import site.ugaeng.localhosting.env.Environment;
import site.ugaeng.localhosting.env.ServerArgs;
import site.ugaeng.localhosting.env.TunnelArgs;

public class MyApp {

    public static void main(String[] args) {
        printWelcomeMessage();

        TunnelConfig tunnelConfig = configure(args);

        LocalhostingClient client = getLocalhostingClient(tunnelConfig);
        client.start();
    }

    private static TunnelConfig configure(String[] args) {
        TunnelArgs tunnelArgs = getTunnelArgs(args);
        Environment.init(tunnelArgs);

        return TunnelConfigFactory.getTunnelConfig(tunnelArgs.getTunnelingPlatform());
    }

    private static TunnelArgs getTunnelArgs(String[] args) {
        ClientArgs clientArgs = new ClientArgs(args);
        ServerArgs serverArgs = ServerArgsLoader.load();

        return new TunnelArgs(clientArgs, serverArgs);
    }

    private static void printWelcomeMessage() {
        System.out.println("HELLO");
    }

    private static LocalhostingClient getLocalhostingClient(TunnelConfig tunnelConfig) {
        TunnelConnectionService tunnelConnectionService = tunnelConfig.tunnelConnectionService();
        RequestForwardingService requestForwardingService = tunnelConfig.requestForwardingService();

        return new LocalhostingClient(tunnelConnectionService,
                                      requestForwardingService);
    }
}
