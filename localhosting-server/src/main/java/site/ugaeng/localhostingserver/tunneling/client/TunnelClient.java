package site.ugaeng.localhostingserver.tunneling.client;

import site.ugaeng.localhostingserver.utils.ClosableUtils;

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

    public void close() {
        ClosableUtils.close(client, clientReader, clientWriter);
    }
}
