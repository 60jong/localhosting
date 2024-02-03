package site.ugaeng.localhostingserver.impl.socket.tunneling;

import java.net.Socket;

public interface TunnelProcessor {

    void run(Socket clientSocket);
}
