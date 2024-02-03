package site.ugaeng.localhosting.util;

import site.ugaeng.localhosting.tunnel.impl.socket.io.SocketDataLineReader;
import site.ugaeng.localhosting.tunnel.impl.socket.io.SocketDataLineWriter;
import java.net.Socket;

public class SocketIOUtils {

    public static SocketDataLineReader getReader(Socket socket) {
        return new SocketDataLineReader(socket);
    }

    public static SocketDataLineWriter getWriter(Socket socket) {
        return new SocketDataLineWriter(socket);
    }
}
