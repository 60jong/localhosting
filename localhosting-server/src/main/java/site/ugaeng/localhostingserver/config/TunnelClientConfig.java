package site.ugaeng.localhostingserver.config;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import site.ugaeng.localhostingserver.forward.TunnelRegisterServer;
import site.ugaeng.localhostingserver.tunnel.service.TunnelService;

@RequiredArgsConstructor
@Configuration
public class TunnelClientConfig {


    @PostConstruct
    void init() {
        startTunnelRegisterServerThread();
    }

    private void startTunnelRegisterServerThread() {
        var registerServerThread = new Thread(() -> {
            TunnelRegisterServer registerServer = new TunnelRegisterServer();
            registerServer.run();
        });
        registerServerThread.setName("tunnel-reg-serv");

        registerServerThread.start();
    }
}
