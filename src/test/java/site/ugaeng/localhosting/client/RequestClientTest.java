package site.ugaeng.localhosting.client;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import site.ugaeng.localhosting.http.ProtocolVersion;
import site.ugaeng.localhosting.http.client.HttpRequestClient;
import site.ugaeng.localhosting.http.client.RequestClient;
import site.ugaeng.localhosting.http.request.Request;
import site.ugaeng.localhosting.http.request.RequestLine;
import site.ugaeng.localhosting.http.response.Response;

import java.net.http.HttpClient;

import static org.assertj.core.api.Assertions.*;

class RequestClientTest {

    RequestClient client;

    @BeforeEach
    void beforeEach() {
        client = new HttpRequestClient(HttpClient.newHttpClient());
    }

    @Test
    void get_request() {
        // given
        Request request = Request.builder()
                .requestLine(RequestLine.builder()
                        .method("GET")
                        .uri("http://localhost:8080/posts")
                        .version(ProtocolVersion.of("HTTP/1.1"))
                        .build())
                .build();

        // when
        Response response = client.performRequest(request);

        // then
        assertThat(response.getStatusLine().toString()).isEqualTo("HTTP/1.1 200 OK");
    }

}