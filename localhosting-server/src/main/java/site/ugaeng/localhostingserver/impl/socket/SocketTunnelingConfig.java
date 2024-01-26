package site.ugaeng.localhostingserver.impl.socket;

import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Bean;
import site.ugaeng.localhostingserver.forward.RequestForwarder;
import site.ugaeng.localhostingserver.impl.socket.tunneling.TunnelingServer;

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
}
