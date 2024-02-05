package site.ugaeng.localhostingserver.http.request.reader;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import site.ugaeng.localhostingserver.http.request.Request;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

class RequestReaderTest {

    @Test
    @DisplayName("호스팅을 URI는 3번째 리소스부터")
    void readRequest_from_HttpServletRequest() throws IOException {
        // given
        MockHttpServletRequest mockRequest = new MockHttpServletRequest("GET", "/host/tunnelName/posts");

        // when
        Request request = RequestReader.readFromHttpServletRequest(mockRequest);

        // then
        assertThat(request.getRequestLine().getUri()).isEqualTo("/posts");
    }

    @Test
    @DisplayName("빈 URI는 /로 요청된다.")
    void readRequest_from_HttpServletRequest_empty_uri() throws IOException {
        // given
        MockHttpServletRequest mockRequest = new MockHttpServletRequest("GET", "/host/tunnelName");

        // when
        Request request = RequestReader.readFromHttpServletRequest(mockRequest);

        // then
        assertThat(request.getRequestLine().getUri()).isEqualTo("/");
    }
}