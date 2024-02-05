package site.ugaeng.localhosting.tunnel.impl.socket.conn;

import lombok.Getter;
import site.ugaeng.localhosting.tunnel.conn.TunnelConnection;
import site.ugaeng.localhosting.tunnel.impl.socket.io.SocketDataLineReader;
import site.ugaeng.localhosting.tunnel.impl.socket.io.SocketDataLineWriter;

import java.net.Socket;

import static site.ugaeng.localhosting.util.SocketIOUtils.*;
import static site.ugaeng.localhosting.util.SocketIOUtils.getReader;

@Getter
public class SocketTunnelConnection implements TunnelConnection {

    private final Socket connection;
    private final SocketDataLineReader socketReader;
    private final SocketDataLineWriter socketWriter;

    public SocketTunnelConnection(Socket connection) {
        this.connection = connection;
        this.socketReader = getReader(this.connection);
        this.socketWriter = getWriter(this.connection);
    }

    public boolean isConnected() {
        return connection.isConnected();
    }
}
