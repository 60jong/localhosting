package site.ugaeng.localhosting;

import lombok.extern.slf4j.Slf4j;
import site.ugaeng.localhosting.exception.LocalhostingException;

import java.io.IOException;
import java.net.Socket;

import static site.ugaeng.localhosting.env.Environment.*;
import static site.ugaeng.localhosting.env.EnvironmentConst.TUNNELING_SERVER_ADDR;
import static site.ugaeng.localhosting.http.HttpConstant.COLON;

@Slf4j
public class TunnelingSocketConnector {

    private static TunnelingSocketConnection serverConnection;

    public static TunnelingSocketConnection getSocketConnection() {
        if (hasLiveConnection()) {
            return serverConnection;
        }
        return serverConnection = getNewConnection();
    }

    private static boolean hasLiveConnection() {
        return serverConnection != null && serverConnection.isConnected();
    }

    private static TunnelingSocketConnection getNewConnection() {
        Socket connection = createSocketConnection();

        return new TunnelingSocketConnection(connection);
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

    public static void closeConnection() {
        if (serverConnection != null) {
            serverConnection.close();
        }
    }
}
