package site.ugaeng.localhostingserver.forward;

import org.springframework.stereotype.Component;
import site.ugaeng.localhostingserver.http.Response;
import site.ugaeng.localhostingserver.http.Request;
import site.ugaeng.localhostingserver.tunnel.client.TunnelClient;
import site.ugaeng.localhostingserver.tunnel.client.TunnelClientRepository;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

import static site.ugaeng.localhostingserver.utils.ObjectUtils.*;

@Component
public class RequestForwarder {

    public Response forwardRequestForTunnel(String tunnelName, Request request) throws IOException {
        TunnelClient tunnelClient = getTunnelClient(tunnelName);

        sendRequest(tunnelClient, request);

        return receiveResponse(tunnelClient);
    }

    private Response receiveResponse(TunnelClient tunnelClient) throws IOException {
        BufferedReader reader = tunnelClient.clientReader();

        final String json = reader.readLine();

        return convertToObject(json, Response.class);
    }

    private void sendRequest(TunnelClient tunnelClient, Request request) {
        BufferedWriter writer = tunnelClient.clientWriter();

        try {
            writer.write(convertToString(request));
            writer.newLine();
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private TunnelClient getTunnelClient(String tunnelName) {
        return TunnelClientRepository.getInstance()
                .find(tunnelName)
                .orElseThrow(() -> new RuntimeException("TUNNEL NAME NOT FOUND"));
    }
}
