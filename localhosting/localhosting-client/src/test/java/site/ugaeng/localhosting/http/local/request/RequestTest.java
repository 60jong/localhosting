package site.ugaeng.localhosting.http.local.request;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import site.ugaeng.localhosting.test.EnvironmentConfig;

import java.io.*;

import static org.assertj.core.api.Assertions.assertThat;
import static site.ugaeng.localhosting.test.TestHttpRequestMessage.HTTP_REQUEST_MESSAGE_POST_JSON;
import static site.ugaeng.localhosting.test.TestHttpRequestMessage.HTTP_REQUEST_MESSAGE_POST_kr;
import static site.ugaeng.localhosting.util.IOUtils.readNBytes;

class RequestTest {

    @BeforeEach
    void beforeEach() {
        EnvironmentConfig.setupEnvironment();
    }

    @Test
    void read_http_request_message() {
        try (var reader =  new BufferedReader(new InputStreamReader(new ByteArrayInputStream(HTTP_REQUEST_MESSAGE_POST_kr.getBytes())))) {
            Request request = Request.readFromReader(reader);

            assertThat(request.getEntity()).isEqualTo(
                    "{\r\n" +
                            "    \"name\" : \"안녕\"\r\n" +
                            "}"
            );
        } catch (IOException e) {
            // pass test //
        }
    }

    @Test
    void bufferedReader_read_bytes_en() {
        final String body =
                "{\r\n" +
                "    \"name\" : \"hello\"\r\n" +
                "}";

        int contentLength = body.getBytes().length;

        try (var reader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(body.getBytes())))) {

            String readBody = readNBytes(reader, contentLength);
            assertThat(readBody).isEqualTo(body);
        } catch (IOException e) {
            // pass test //
        }
    }

    @Test
    void byte_to_char_not_matching() {
        final String body =
                "{\r\n" +
                "    \"name\" : \"안녕\"\r\n" +
                "}";

        int contentLength = body.getBytes().length;

        try (var reader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(body.getBytes())))) {
            String readBody = readNBytes(reader, contentLength);
            assertThat(readBody).isEqualTo(body);
        } catch (IOException e) {
            // pass test //
        }
    }

    @Test
    void read_from_json() {
        try (var reader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(HTTP_REQUEST_MESSAGE_POST_JSON.getBytes())))) {

        } catch (IOException e) {

        }
    }
}