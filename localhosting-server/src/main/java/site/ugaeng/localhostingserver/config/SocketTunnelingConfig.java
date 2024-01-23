package site.ugaeng.localhostingserver.config;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import site.ugaeng.localhostingserver.tunnel.TunnelRepository;
import site.ugaeng.localhostingserver.tunneling.TunnelingServer;
import site.ugaeng.localhostingserver.tunneling.client.TunnelClientRepository;

@RequiredArgsConstructor
public class SocketTunnelingConfig {

    private final TunnelRepository tunnelRepository;

    @PostConstruct
    void init() {
        initializeTunnelClientRepository();

        startTunnelingServerThread();
    }

    private void initializeTunnelClientRepository() {
        TunnelClientRepository.initialize(tunnelRepository);
    }

    private void startTunnelingServerThread() {
        Thread tunnelingServerThread = createTunnelingServerThread();

        tunnelingServerThread.start();
    }

    private Thread createTunnelingServerThread() {
        var tunnelingServerThread = new Thread(() -> {
            TunnelingServer tunnelingServer = new TunnelingServer();
            tunnelingServer.run();
        });
        tunnelingServerThread.setName("tunnelingserver");
        return tunnelingServerThread;
    }
}
