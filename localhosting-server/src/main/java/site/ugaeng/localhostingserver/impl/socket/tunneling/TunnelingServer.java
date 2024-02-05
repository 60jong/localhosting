package site.ugaeng.localhostingserver.impl.socket.tunneling;

import lombok.extern.slf4j.Slf4j;
import site.ugaeng.localhostingserver.impl.socket.tunneling.client.TunnelClient;
import site.ugaeng.localhostingserver.impl.socket.tunneling.request.TunnelRequest;

import java.io.IOException;
import java.net.ServerSocket;

import static site.ugaeng.localhostingserver.utils.ClosableUtils.*;

@Slf4j
public class TunnelingServer {

    private static final int DEFAULT_PORT = 9000;
    private static final int DEFAULT_BACKLOG = 50;

    private final ServerSocket serverSocket;

    private boolean running;

    public TunnelingServer() {
        this.serverSocket = createServerSocket();
        this.running = true;
    }

    public void run() {
        log.info("Tunneling started");
        while (running) {
            try {
                var client = new TunnelClient(serverSocket.accept());
                log.info("client connected [{}]", client);

                service(client);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        stop();
    }

    private void service(TunnelClient client) {
        TunnelRequestProcessor processor = getProcessor(client);
        processor.run(client);
    }

    private TunnelRequestProcessor getProcessor(TunnelClient client) {
        TunnelRequest tunnelRequest = TunnelRequest.readFromTunnelClient(client);

        return TunnelingProcessorFactory.createTunnelRequestProcessor(tunnelRequest);
    }

    private ServerSocket createServerSocket() {
        try {
            return new ServerSocket(DEFAULT_PORT, DEFAULT_BACKLOG);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void stop() {
        running = false;
        close(serverSocket);
    }
}
