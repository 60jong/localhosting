package site.ugaeng.localhostingserver.tunnel;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
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
