package site.ugaeng.localhostingserver.impl.socket.tunneling;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import site.ugaeng.localhostingserver.http.request.Request;
import site.ugaeng.localhostingserver.http.request.reader.RequestReader;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import static site.ugaeng.localhostingserver.utils.ClosableUtils.*;

@Slf4j
@Component
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
                Socket registerClient = serverSocket.accept();
                log.info("client connected [{}]", registerClient);

                service(registerClient);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void service(Socket registerClient) {
        TunnelProcessor processor = getProcessor(registerClient);
        processor.run(registerClient);
    }

    private TunnelProcessor getProcessor(Socket registerClient) {
        Request request = RequestReader.readFromSocket(registerClient);

        return TunnelingProcessorFactory.createTunnelProcessorByRequest(request);
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
