package site.ugaeng.localhosting;

import lombok.Getter;
import site.ugaeng.localhosting.http.local.request.Request;
import site.ugaeng.localhosting.http.local.response.Response;
import site.ugaeng.localhosting.impl.socket.io.SocketDataLineReader;
import site.ugaeng.localhosting.impl.socket.io.SocketDataLineWriter;
import site.ugaeng.localhosting.util.ClosableUtils;

import java.net.Socket;

import static site.ugaeng.localhosting.env.Environment.getTunnelName;
import static site.ugaeng.localhosting.util.SocketIOUtils.getReader;
import static site.ugaeng.localhosting.util.SocketIOUtils.getWriter;

@Getter
public class TunnelingSocketConnection implements TunnelingConnection {

    private final Socket connection;
    private final SocketDataLineReader socketReader;
    private final SocketDataLineWriter socketWriter;

    public TunnelingSocketConnection(Socket connection) {
        this.connection = connection;
        this.socketReader = getReader(connection);
        this.socketWriter = getWriter(connection);

        sendEntryMessage();
    }

    private void sendEntryMessage() {
        socketWriter.writeWithLine(getTunnelName());
    }

    public boolean isConnected() {
        return connection.isConnected();
    }

    public Request readRequest() {
        return socketReader.readRequestByLine();
    }

    public void sendResponse(Response response) {
        socketWriter.writeResponseWithLine(response);
    }

    public void sendData(String data) {
        socketWriter.writeWithLine(data);
    }

    public void close() {
        ClosableUtils.close(socketReader, socketWriter, connection);
    }
}
