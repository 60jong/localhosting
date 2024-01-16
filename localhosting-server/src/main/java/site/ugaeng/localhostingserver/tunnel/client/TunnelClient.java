package site.ugaeng.localhostingserver.tunnel.client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.net.Socket;

public record TunnelClient(
        Socket client,
        BufferedReader clientReader,
        BufferedWriter clientWriter
) {

    public boolean isClosed() {
        // TODO : check heartbeat
        return false;
    }
}
