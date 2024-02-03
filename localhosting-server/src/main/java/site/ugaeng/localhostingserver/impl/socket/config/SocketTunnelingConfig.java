package site.ugaeng.localhostingserver.impl.socket.config;

import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import site.ugaeng.localhostingserver.forward.RequestForwarder;
import site.ugaeng.localhostingserver.impl.socket.SocketTunnelingRequestForwarder;
import site.ugaeng.localhostingserver.impl.socket.tunneling.TunnelingServer;
import site.ugaeng.localhostingserver.tunnel.repository.TunnelRepository;
import site.ugaeng.localhostingserver.tunnel.service.SocketTunnelService;
import site.ugaeng.localhostingserver.tunnel.service.TunnelService;

@Profile("socket")
@Configuration
public class SocketTunnelingConfig {

    @PostConstruct
    void init() {
        startTunnelingServerThread();
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

    @Bean
    public RequestForwarder socketTunnelingRequestForwarder() {
        return new SocketTunnelingRequestForwarder();
    }

    @Bean
    public TunnelService tunnelService(TunnelRepository tunnelRepository) {
        return new SocketTunnelService(tunnelRepository);
    }
}
