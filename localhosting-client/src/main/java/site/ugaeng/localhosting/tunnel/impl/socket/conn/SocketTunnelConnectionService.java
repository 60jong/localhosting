package site.ugaeng.localhosting.tunnel.impl.socket.conn;

import lombok.extern.slf4j.Slf4j;
import site.ugaeng.localhosting.tunnel.conn.TunnelConnection;
import site.ugaeng.localhosting.tunnel.conn.TunnelConnectionService;
import site.ugaeng.localhosting.exception.LocalhostingException;
import site.ugaeng.localhosting.tunnel.impl.socket.io.SocketDataLineReader;
import site.ugaeng.localhosting.tunnel.impl.socket.io.SocketDataLineWriter;
import site.ugaeng.localhosting.tunnel.request.TunnelRequest;

import java.io.IOException;
import java.net.Socket;

import static site.ugaeng.localhosting.env.Environment.getProperty;
import static site.ugaeng.localhosting.env.Environment.getTunnelName;
import static site.ugaeng.localhosting.env.EnvironmentConst.TUNNELING_SERVER_ADDR;
import static site.ugaeng.localhosting.http.HttpConstant.COLON;
import static site.ugaeng.localhosting.util.ObjectUtils.*;

@Slf4j
public class SocketTunnelConnectionService implements TunnelConnectionService {

    @Override
    public TunnelConnection connect() {
        return createSocketTunnelConnection();
    }

    private SocketTunnelConnection createSocketTunnelConnection() {
        String tunnelingServerAddr = (String) getProperty(TUNNELING_SERVER_ADDR);
        String[] hostAndPort = tunnelingServerAddr.split(COLON);

        final String tunnelingServerHost = hostAndPort[0];
        final int port = Integer.parseInt(hostAndPort[1]);

        return connectToServer(tunnelingServerHost, port);
    }

    private SocketTunnelConnection connectToServer(String tunnelingServerHost, int port) {
        try
        {
            log.info("Trying to connect to Tunneling server...");

            Socket socket = new Socket(tunnelingServerHost, port);
            SocketTunnelConnection connection = new SocketTunnelConnection(socket);

            confirmConnection(connection);

            log.info("Connection Success [local port : {}]", connection.getConnection().getLocalPort());
            return connection;
        }
        catch (IOException e)
        {
            log.error("Failed to connect to Tunneling server...");
            log.info("Sorry. Tunneling server is not running :(");
            throw new LocalhostingException(e);
        }
    }

    private void confirmConnection(SocketTunnelConnection connection) {
        sendConnectRequest(connection.getSocketWriter());
        receiveSuccessResponse(connection.getSocketReader());
    }

    private void receiveSuccessResponse(SocketDataLineReader reader) {
        String response = reader.readLine();

        if (!response.equals("SUCCESS")) {
            throw new RuntimeException();
        }
    }

    private void sendConnectRequest(SocketDataLineWriter writer) {
        var tunnelRequest = new TunnelRequest("CONNECTION", getTunnelName());
        writer.writeWithLine(convertToString(tunnelRequest));
    }
}
