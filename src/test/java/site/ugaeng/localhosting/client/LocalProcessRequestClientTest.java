package site.ugaeng.localhosting.client;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import site.ugaeng.localhosting.http.ProtocolVersion;
import site.ugaeng.localhosting.http.local.LocalRequests;
import site.ugaeng.localhosting.http.local.client.LocalProcessHttpRequestClient;
import site.ugaeng.localhosting.http.local.client.LocalProcessRequestClient;
import site.ugaeng.localhosting.http.local.request.Request;
import site.ugaeng.localhosting.http.request.RequestLine;
import site.ugaeng.localhosting.http.local.response.Response;
import site.ugaeng.localhosting.http.request.RequestLineBuilder;

import java.net.http.HttpClient;

import static org.assertj.core.api.Assertions.*;

class LocalProcessRequestClientTest {

    LocalProcessRequestClient client;

    @BeforeEach
    void beforeEach() {
        client = LocalRequests.getLocalRequestClient();
    }

    @Test
    void get_request() {
        // given
        Request request = Request.builder()
                .requestLine(RequestLineBuilder.buildLocalRequestLine("GET /posts HTTP/1.1"))
                .build();

        // when
        Response response = client.performRequest(request);

        // then
        assertThat(response.getStatusLine().toString()).isEqualTo("HTTP/1.1 200 OK");
    }

}