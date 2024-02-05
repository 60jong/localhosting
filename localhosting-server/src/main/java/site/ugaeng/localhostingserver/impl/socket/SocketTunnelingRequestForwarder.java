package site.ugaeng.localhostingserver.impl.socket;

import lombok.extern.slf4j.Slf4j;
import site.ugaeng.localhostingserver.forward.RequestForwarder;
import site.ugaeng.localhostingserver.http.response.Response;
import site.ugaeng.localhostingserver.http.request.Request;
import site.ugaeng.localhostingserver.impl.socket.io.SocketDataLineReader;
import site.ugaeng.localhostingserver.impl.socket.tunneling.client.TunnelClientRepository;
import site.ugaeng.localhostingserver.impl.socket.tunneling.client.TunnelClient;
import site.ugaeng.localhostingserver.tunnel.domain.Tunnel;

import static site.ugaeng.localhostingserver.utils.ObjectUtils.*;

@Slf4j
public class SocketTunnelingRequestForwarder implements RequestForwarder {

    private final TunnelClientRepository tunnelClientRepository;

    public SocketTunnelingRequestForwarder() {
        this.tunnelClientRepository = TunnelClientRepository.getInstance();
    }

    @Override
    public Response forwardRequestForTunnel(Tunnel tunnel, Request request) {
        TunnelClient tunnelClient = getTunnelClient(tunnel.getName());

        sendRequest(tunnelClient, request);

        return receiveResponse(tunnelClient);
    }

    private TunnelClient getTunnelClient(String tunnelName) {
        return tunnelClientRepository.find(tunnelName)
                .orElseThrow(() -> new RuntimeException("TUNNEL NAME NOT FOUND"));
    }

    private void sendRequest(TunnelClient tunnelClient, Request request) {
        tunnelClient.getClientWriter()
                    .writeObjectWithLine(request);

    }

    private Response receiveResponse(TunnelClient tunnelClient) {
        SocketDataLineReader reader = tunnelClient.getClientReader();

        final String json = reader.readLine();

        log.info("response received [{}] from tunnel client [{}]", json, tunnelClient.getConnection());
        return convertToObject(json, Response.class);
    }
}
