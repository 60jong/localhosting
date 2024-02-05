package site.ugaeng.localhosting;

import site.ugaeng.localhosting.exception.LocalhostingException;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static java.net.http.HttpRequest.BodyPublishers.ofString;
import static java.net.http.HttpResponse.BodyHandlers.discarding;
import static site.ugaeng.localhosting.http.HttpConstant.APPLICATION_JSON;
import static site.ugaeng.localhosting.http.HttpConstant.CONTENT_TYPE;

class TunnelRegistererTest {

    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            HttpClient client = HttpClient.newHttpClient();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://localhosting.do-main.site:8080/tunnels"))
                    .header(CONTENT_TYPE, APPLICATION_JSON)
                    .POST(ofString("{\"tunnelName\":\"" + "test-" + i + "\"}"))
                    .build();

            try {
                HttpResponse<Void> response = client.send(request, discarding());

            } catch (Exception e) {
                throw new LocalhostingException(e);
            }
        }
    }

}