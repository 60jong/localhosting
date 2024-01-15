package site.ugaeng.localhostingserver.tunnel;

import java.net.Socket;

public class TunnelConnection {

    private final Socket client;

    public TunnelConnection(Socket client) {
        this.client = client;
    }
}
