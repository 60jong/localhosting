package site.ugaeng.localhostingserver.impl.socket;

import lombok.extern.slf4j.Slf4j;
import site.ugaeng.localhostingserver.forward.RequestForwarder;
import site.ugaeng.localhostingserver.http.response.Response;
import site.ugaeng.localhostingserver.http.request.Request;
import site.ugaeng.localhostingserver.impl.socket.tunneling.client.TunnelClient;
import site.ugaeng.localhostingserver.impl.socket.tunneling.client.TunnelClientRepository;
import site.ugaeng.localhostingserver.tunnel.domain.Tunnel;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

import static site.ugaeng.localhostingserver.utils.ObjectUtils.*;

@Slf4j
public class SocketTunnelingRequestForwarder implements RequestForwarder {

    @Override
    public Response forwardRequestForTunnel(Tunnel tunnel, Request request) throws IOException {
        TunnelClient tunnelClient = getTunnelClient(tunnel.getName());

        sendRequest(tunnelClient, request);

        return receiveResponse(tunnelClient);
    }

    private TunnelClient getTunnelClient(String tunnelName) {
        return TunnelClientRepository.getInstance()
                .find(tunnelName)
                .orElseThrow(() -> new RuntimeException("TUNNEL NAME NOT FOUND"));
    }

    private void sendRequest(TunnelClient tunnelClient, Request request) {
        BufferedWriter writer = tunnelClient.clientWriter();

        try {
            String convertedRequest = convertToString(request);

            writer.write(convertedRequest);
            writer.newLine();
            writer.flush();

            log.info("request forwarded [{}] to tunnel client [{}]", convertedRequest, tunnelClient.client());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Response receiveResponse(TunnelClient tunnelClient) throws IOException {
        BufferedReader reader = tunnelClient.clientReader();

        final String json = reader.readLine();

        log.info("response received [{}] from tunnel client [{}]", json, tunnelClient.client());
        return convertToObject(json, Response.class);
    }
}
