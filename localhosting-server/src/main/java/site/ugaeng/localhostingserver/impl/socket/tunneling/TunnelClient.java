package site.ugaeng.localhostingserver.impl.socket.tunneling;

import lombok.Getter;
import site.ugaeng.localhostingserver.impl.socket.io.SocketDataLineReader;
import site.ugaeng.localhostingserver.impl.socket.io.SocketDataLineWriter;

import java.net.Socket;

import static site.ugaeng.localhostingserver.utils.SocketIOUtils.*;

@Getter
public class TunnelClient {

    private final Socket connection;
    private final SocketDataLineReader clientReader;
    private final SocketDataLineWriter clientWriter;

    public TunnelClient(Socket connection) {
        this.connection = connection;
        this.clientReader = getReader(connection);
        this.clientWriter = getWriter(connection);
    }
}
