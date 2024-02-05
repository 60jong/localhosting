package site.ugaeng.localhosting;

import lombok.extern.slf4j.Slf4j;
import site.ugaeng.localhosting.client.LocalhostingClient;
import site.ugaeng.localhosting.tunnel.config.TunnelConfig;
import site.ugaeng.localhosting.tunnel.config.TunnelConfigFactory;
import site.ugaeng.localhosting.tunnel.conn.TunnelConnectionService;
import site.ugaeng.localhosting.env.*;
import site.ugaeng.localhosting.exception.LocalhostingException;
import site.ugaeng.localhosting.tunnel.forward.RequestForwardingService;

@Slf4j
public class Application {

    public static void main(String[] args) {
        printWelcomeMessage();

        try {
            TunnelConfig tunnelConfig = configure(args);

            LocalhostingClient client = getLocalhostingClient(tunnelConfig);
            client.start();
        } catch (LocalhostingException localhostingEx) {
            log.error("error", localhostingEx);
        }
    }

    private static void printWelcomeMessage() {
        System.out.println(Message.logoMessage);
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

    private static LocalhostingClient getLocalhostingClient(TunnelConfig tunnelConfig) {
        TunnelConnectionService connectionService = tunnelConfig.tunnelConnectionService();
        RequestForwardingService forwardingService = tunnelConfig.requestForwardingService();

        return new LocalhostingClient(connectionService,
                forwardingService);
    }
}