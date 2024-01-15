package site.ugaeng.localhostingserver;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import site.ugaeng.localhostingserver.http.Request;

public class ResponseTest {

    ObjectMapper om = new ObjectMapper();

    @Test
    void map_response() throws JsonProcessingException {
        // given
        final String json =
                "{\n" +
                "  \"statusLine\":{\n" +
                "    \"version\":\"HTTP_1_1\",\n" +
                "    \"reasonPhrase\":\"OK\",\n" +
                "    \"statusCode\": 200\n" +
                "  },\n" +
                "  \"headers\":{\n" +
                "    \"Content-Type\":\"application/json\"\n" +
                "  },\n" +
                "  \"entity\":\"{\\r\\n \\\"domainName\\\" : \\\"ugaeng\\\"\\r\\n}\"\n" +
                "}";

        // when
        Response response = om.readValue(json, Response.class);

        // then
        System.out.println(response.getStatusLine());
        System.out.println(response.getHeaders());
        System.out.println(response.getEntity());
    }

}
