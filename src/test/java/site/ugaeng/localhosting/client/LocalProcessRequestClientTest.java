package site.ugaeng.localhosting.client;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import site.ugaeng.localhosting.http.local.LocalRequests;
import site.ugaeng.localhosting.http.local.client.LocalProcessRequestClient;
import site.ugaeng.localhosting.http.local.request.Request;
import site.ugaeng.localhosting.http.local.response.Response;
import site.ugaeng.localhosting.test.EnvironmentConfig;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import static org.assertj.core.api.Assertions.*;
import static site.ugaeng.localhosting.test.TestHttpRequestMessage.*;

class LocalProcessRequestClientTest {

    LocalProcessRequestClient client;

    @BeforeEach
    void beforeEach() {
        EnvironmentConfig.setupEnvironment();

        client = LocalRequests.getLocalRequestClient();
    }

    @Test
    void get_request() throws IOException {
        // given
        BufferedReader reader =  new BufferedReader(new InputStreamReader(new ByteArrayInputStream(HTTP_REQUEST_MESSAGE_GET.getBytes())));
        Request request = Request.readFromReader(reader);

        // when
        Response response = client.performRequest(request);

        // then
        assertThat(response.getStatusLine().toString()).isEqualTo("HTTP/1.1 200 ");
    }

    @Test
    void post_request() throws IOException {
        // given
        BufferedReader reader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(HTTP_REQUEST_MESSAGE_POST_en.getBytes())));
        Request request = Request.readFromReader(reader);

        // when
        Response response = client.performRequest(request);

        // then
        assertThat(response.getStatusLine().toString()).isEqualTo("HTTP/1.1 200 ");
    }


}