package site.ugaeng.localhostingserver.impl.socket.tunneling;

import lombok.extern.slf4j.Slf4j;
import site.ugaeng.localhostingserver.http.request.Request;
import site.ugaeng.localhostingserver.impl.socket.io.SocketDataLineWriter;
import site.ugaeng.localhostingserver.tunnel.TunnelRegisterRequest;
import java.io.IOException;
import java.net.Socket;

import static site.ugaeng.localhostingserver.utils.ClosableUtils.close;
import static site.ugaeng.localhostingserver.utils.ObjectUtils.convertToObject;
import static site.ugaeng.localhostingserver.utils.SocketIOUtils.getWriter;

@Slf4j
public class TunnelRegisterer implements TunnelProcessor {

    private final Request request;

    public TunnelRegisterer(Request request) {
        this.request = request;
    }

    @Override
    public void run(Socket clientSocket) {
        TunnelRegisterRequest request = getTunnelRegisterRequest();

        registerTunnel(request.getTunnelName());

        sendRegisterResponse(clientSocket);
    }

    private TunnelRegisterRequest getTunnelRegisterRequest() {
        return convertToObject(request.getEntity(), TunnelRegisterRequest.class);
    }

    private void registerTunnel(String requestTunnelName) {
        if (!isExistingTunnelName(requestTunnelName)) {
            log.info("new Tunnel, Tunnel Name : {}", requestTunnelName);
            return;
        }
        log.info("exising Tunnel, Tunnel Name : {}", requestTunnelName);
    }

    private boolean isExistingTunnelName(String requestTunnelName) {
        return false;
    }

    private void sendRegisterResponse(Socket client) {
        try (SocketDataLineWriter writer = getWriter(client))
        {
            writer.writeWithLine("HTTP/1.1 200 OK\r\n");
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
        finally {
            close(client);
        }
    }
}
