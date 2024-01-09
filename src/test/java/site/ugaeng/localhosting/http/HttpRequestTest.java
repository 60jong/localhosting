package site.ugaeng.localhosting.http;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static java.net.http.HttpResponse.*;
import static org.assertj.core.api.Assertions.assertThat;

class HttpRequestTest {

    @Test
    void http_connect() throws IOException, URISyntaxException, InterruptedException {
        // given
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("http://localhost:8080/posts"))
                .version(HttpClient.Version.HTTP_1_1)
                .GET()
                .build();

        // when
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());

        // then
        assertThat(response.statusCode()).isEqualTo(200);
    }
}
