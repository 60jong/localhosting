package site.ugaeng.localhosting;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;

public class ProxyConnector {

    private static final int DEFAULT_PORT = 9000;
    private static final int DEFAULT_BACKLOG = 50;

    private final ServerSocket serverSocket;
    private final ExecutorService threadPool;

    public ProxyConnector() {
        serverSocket = new ServerSocket(DEFAULT_PORT, DEFAULT_BACKLOG);
    }

    public void connect() {
        Socket accept = serverSocket.accept();

    }
}
