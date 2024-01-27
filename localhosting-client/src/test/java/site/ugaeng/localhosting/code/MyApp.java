package site.ugaeng.localhosting.code;

import site.ugaeng.localhosting.code.config.TunnelConfig;
import site.ugaeng.localhosting.code.config.TunnelConfigFactory;
import site.ugaeng.localhosting.env.ClientArgs;
import site.ugaeng.localhosting.env.TunnelArgs;

public class MyApp {

    public static void main(String[] args) {
        printWelcomeMessage();

        configure();

        startClient(args);
    }

    private static void configure() {
        // Configure
        TunnelArgs tunnelArgs = new TunnelArgs(clientArgs, serverArgs);

        TunnelConfig tunnelConfig = TunnelConfigFactory.getTunnelConfig(tunnelArgs.getTunnelPlatform());
    }

    private static void printWelcomeMessage() {
        System.out.println("HELLO");
    }

    private static void startClient(String[] args) {
        ClientArgs clientArgs = new ClientArgs(args);

        var client = new LocalhostingClient(clientArgs);
        client.start();
    }
}
