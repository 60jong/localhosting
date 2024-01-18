package site.ugaeng.localhosting;

import lombok.extern.slf4j.Slf4j;
import site.ugaeng.localhosting.exception.LocalhostingException;
import site.ugaeng.localhosting.io.SocketDataLineWriter;

import java.io.IOException;
import java.net.Socket;

import static site.ugaeng.localhosting.env.Environment.*;
import static site.ugaeng.localhosting.env.EnvironmentConst.TUNNELING_SERVER_ADDR;
import static site.ugaeng.localhosting.http.HttpConstant.COLON;

@Slf4j
public class TunnelingServerConnector {

    private static TunnelingServerConnection serverConnection;

    public static TunnelingServerConnection getServerConnection() {
        if (hasLiveConnection()) {
            return serverConnection;
        }
        return serverConnection = getNewConnection();
    }

    private static boolean hasLiveConnection() {
        return serverConnection != null && serverConnection.isConnected();
    }

    private static TunnelingServerConnection getNewConnection() {
        Socket connection = createSocketConnection();

        TunnelingServerConnection serverConnection = new TunnelingServerConnection(connection);
        writeTunnelName(serverConnection);

        return serverConnection;
    }

    private static Socket createSocketConnection() {
        String tunnelingServerAddr = (String) getProperty(TUNNELING_SERVER_ADDR);

        String[] hostAndPort = tunnelingServerAddr.split(COLON);

        final String tunnelingServerHost = hostAndPort[0];
        final int port = Integer.parseInt(hostAndPort[1]);

        return connect(tunnelingServerHost, port);
    }

    private static Socket connect(String tunnelingServerHost, int port) {
        try
        {
            log.info("Trying to connect to Tunneling server...");

            Socket socketConnection = new Socket(tunnelingServerHost, port);

            log.info("Connection Success [local port : {}]", socketConnection.getLocalPort());
            return socketConnection;
        }
        catch (IOException e)
        {
            log.error("Failed to connect to Tunneling server...");
            log.info("Sorry. Tunneling server is not running :(");
            throw new LocalhostingException(e);
        }
    }

    private static void writeTunnelName(TunnelingServerConnection connection) {
        SocketDataLineWriter socketWriter = connection.getSocketWriter();
        socketWriter.writeWithLine(getTunnelName());
    }

    public static void closeConnection() {
        serverConnection.close();
    }
}
