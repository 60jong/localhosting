package site.ugaeng.localhostingserver.utils;

import site.ugaeng.localhostingserver.impl.socket.io.SocketDataLineReader;
import site.ugaeng.localhostingserver.impl.socket.io.SocketDataLineWriter;

import java.net.Socket;

public class SocketIOUtils {

    public static SocketDataLineReader getReader(Socket socket) {
        return new SocketDataLineReader(socket);
    }

    public static SocketDataLineWriter getWriter(Socket socket) {
        return new SocketDataLineWriter(socket);
    }
}
