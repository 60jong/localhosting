package site.ugaeng.localhostingserver.forward;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import site.ugaeng.localhostingserver.http.Response;
import site.ugaeng.localhostingserver.http.Request;
import site.ugaeng.localhostingserver.tunneling.client.TunnelClient;
import site.ugaeng.localhostingserver.tunneling.client.TunnelClientRepository;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

import static site.ugaeng.localhostingserver.utils.ObjectUtils.*;

@Slf4j
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

        log.info("response received [{}] from tunnel client [{}]", json, tunnelClient.client());
        return convertToObject(json, Response.class);
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

    private TunnelClient getTunnelClient(String tunnelName) {
        return TunnelClientRepository.getInstance()
                .find(tunnelName)
                .orElseThrow(() -> new RuntimeException("TUNNEL NAME NOT FOUND"));
    }
}
