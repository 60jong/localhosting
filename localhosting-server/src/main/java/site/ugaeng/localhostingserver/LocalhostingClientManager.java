package site.ugaeng.localhostingserver;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

@Slf4j
@RequiredArgsConstructor
@Component
public class LocalhostingClientManager {

    private final TunnelClientRepository tunnelClientRepository;

    @PostConstruct
    public void init() {
        Thread clientManageThread = new Thread(
                () -> {
                    try {
                        ServerSocket clientManageServerSocket = new ServerSocket(9000);
                        log.info("client managing server started");
                        while (true) {
                            Socket client = clientManageServerSocket.accept();
                            tunnelClientRepository.save("TEST", client);
                            log.info("client saved [{}] ", client);
                        }
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
        );
        clientManageThread.start();
    }
}
