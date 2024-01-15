package site.ugaeng.localhostingserver.forward;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import site.ugaeng.localhostingserver.Response;
import site.ugaeng.localhostingserver.http.HttpConstant;
import site.ugaeng.localhostingserver.http.Request;
import site.ugaeng.localhostingserver.tunnel.TunnelClient;
import site.ugaeng.localhostingserver.utils.ObjectUtils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.stream.Collectors;

import static site.ugaeng.localhostingserver.http.HttpConstant.*;
import static site.ugaeng.localhostingserver.utils.ObjectUtils.*;

@Component
public class RequestForwarder {

    public Response forwardRequestForTunnel(String tunnelName, Request request) {
        TunnelClient tunnelClient = getTunnelClient(tunnelName);

        sendRequest(tunnelClient, request);

        return receiveResponse(tunnelClient);
    }

    private Response receiveResponse(TunnelClient tunnelClient) {
        BufferedReader reader = tunnelClient.clientReader();

        final String json = reader.lines()
                                  .collect(Collectors.joining(CRLF));
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
                .orElseThrow();
    }
}
