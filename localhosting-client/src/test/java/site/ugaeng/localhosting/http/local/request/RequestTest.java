package site.ugaeng.localhosting.http.local.request;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import site.ugaeng.localhosting.http.request.Request;
import site.ugaeng.localhosting.http.request.RequestReader;
import site.ugaeng.localhosting.test.EnvironmentConfig;
import site.ugaeng.localhosting.util.ObjectUtils;

import java.io.*;

import static org.assertj.core.api.Assertions.assertThat;
import static site.ugaeng.localhosting.test.TestHttpRequestMessage.*;

class RequestTest {

    @BeforeEach
    void beforeEach() {
        EnvironmentConfig.setupEnvironment();
    }

    @Test
    void read_to_json() {
        try (var reader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(HTTP_REQUEST_MESSAGE_GET_JSON.getBytes())))) {
            Request request = RequestReader.readFromReader(reader);

            String s = ObjectUtils.convertToString(request);

            assertThat(s).isEqualTo(HTTP_REQUEST_MESSAGE_GET_JSON);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}