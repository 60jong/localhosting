package site.ugaeng.localhostingserver.forward;

import site.ugaeng.localhostingserver.tunnel.service.TunnelService;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import static site.ugaeng.localhostingserver.utils.ClosableUtils.*;

public class TunnelRegisterProcessor {

    private static final int DEFAULT_PORT = 9000;
    private static final int DEFAULT_BACKLOG = 50;

    private final ServerSocket serverSocket;

    private boolean running;

    public TunnelRegisterProcessor() {
        this.serverSocket = createServerSocket();
        this.running = true;
    }

    public void start() {
        while (running) {
            try {
                registerTunnel();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void registerTunnel() throws IOException {
        Socket registerClient = serverSocket.accept();

        TunnelRegisterer registerer = createRegisterer(registerClient);
        registerer.run();
    }

    private TunnelRegisterer createRegisterer(Socket tunnelClient) throws IOException {
        return new TunnelRegisterer(tunnelClient);
    }

    private void stop() {
        running = false;
        close(serverSocket);
    }

    private ServerSocket createServerSocket() {
        try {
            return new ServerSocket(DEFAULT_PORT, DEFAULT_BACKLOG);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
