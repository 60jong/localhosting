package site.ugaeng.localhosting;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static site.ugaeng.localhosting.util.ClosableUtils.*;

public class ProxyConnector {

    private static final int DEFAULT_PORT = 9000;
    private static final int DEFAULT_BACKLOG = 50;

    private final ServerSocket serverSocket;
    private final ExecutorService executorService;

    private static boolean running;

    public ProxyConnector() {
        serverSocket = createServerSocket();
        executorService = createThreadPool();
        running = true;
    }

    public void start() {
        while (running) {
            try {
                connectProxy();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void connectProxy() throws IOException {
        Socket clientSocket = serverSocket.accept();
        executorService.execute(createForwarder(clientSocket));
    }

    private ProxyForwarder createForwarder(Socket clientSocket) throws IOException {
        return new ProxyForwarder(clientSocket);
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

    private ThreadPoolExecutor createThreadPool() {
        return new ThreadPoolExecutor(25,
                200,
                0,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(50));
    }

}
