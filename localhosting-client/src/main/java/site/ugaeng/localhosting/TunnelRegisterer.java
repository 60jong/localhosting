package site.ugaeng.localhosting;

import lombok.extern.slf4j.Slf4j;
import site.ugaeng.localhosting.exception.LocalhostingException;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static java.net.http.HttpRequest.BodyPublishers.*;
import static java.net.http.HttpResponse.BodyHandlers.discarding;
import static site.ugaeng.localhosting.env.Environment.*;
import static site.ugaeng.localhosting.env.EnvironmentConst.TUNNELING_SERVER_ADDR;
import static site.ugaeng.localhosting.http.HttpConstant.APPLICATION_JSON;
import static site.ugaeng.localhosting.http.HttpConstant.CONTENT_TYPE;

@Slf4j
public class TunnelRegisterer {

    public void registerTunnel(String tunnelName) {
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://" + (String) getProperty(TUNNELING_SERVER_ADDR)+ "/tunnels"))
                .header(CONTENT_TYPE, APPLICATION_JSON)
                .POST(ofString("{\"tunnelName\":\"" + tunnelName + "\"}"))
                .build();

        try {
            HttpResponse<Void> response = client.send(request, discarding());
            log.info("tunnel register status : {}", response.statusCode());

        } catch (Exception e) {
            throw new LocalhostingException(e);
        }
    }
}
