package site.ugaeng.localhosting.http.local.request;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import site.ugaeng.localhosting.ClientArgs;
import site.ugaeng.localhosting.HostingArgs;
import site.ugaeng.localhosting.ServerArgs;
import site.ugaeng.localhosting.env.Environment;
import site.ugaeng.localhosting.test.EnvironmentConfig;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import static org.assertj.core.api.Assertions.assertThat;
import static site.ugaeng.localhosting.test.TestHttpRequestMessage.HTTP_REQUEST_MESSAGE_POST_kr;
import static site.ugaeng.localhosting.util.IOStreamUtils.getBufferedReader;

class RequestTest {

    @BeforeEach
    void beforeEach() {
        EnvironmentConfig.setupEnvironment();
    }

    @Test
    void read_http_request_message() {
        try (var reader = getBufferedReader(new ByteArrayInputStream(HTTP_REQUEST_MESSAGE_POST_kr.getBytes()))) {
            Request request = Request.readFromReader(reader);

            assertThat(request.getEntity()).isEqualTo(
                    "{\r\n" +
                            "    \"name\" : \"안녕\"\r\n" +
                            "}\r\n"
            );
        } catch (IOException e) {
            // pass test //
        }
    }

    @Test
    void bufferedReader_read_N_bytes() {
        final String body =
                "{\r\n" +
                "    \"name\" : \"hello\"\r\n" +
                "}";

        int contentLength = body.getBytes().length;

        try (var reader = getBufferedReader(new ByteArrayInputStream(body.getBytes()))) {
            char bodyChars[] = new char[contentLength];
            reader.read(bodyChars, 0, contentLength);

            assertThat(new String(bodyChars)).isEqualTo(body);
        } catch (IOException e) {
            // pass test //
        }
    }
}