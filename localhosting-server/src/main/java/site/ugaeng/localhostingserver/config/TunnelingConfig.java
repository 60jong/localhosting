package site.ugaeng.localhostingserver.config;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import site.ugaeng.localhostingserver.forward.TunnelingServer;

@RequiredArgsConstructor
@Configuration
public class TunnelClientConfig {


    @PostConstruct
    void init() {
        startTunnelingServerThread();
    }

    private void startTunnelingServerThread() {
        var tunnelingServerThread = new Thread(() -> {
            TunnelingServer tunnelingServer = new TunnelingServer();
            tunnelingServer.run();
        });
        tunnelingServerThread.setName("tunnelingserver");

        tunnelingServerThread.start();
    }
}
