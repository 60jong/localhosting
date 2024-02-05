package site.ugaeng.localhostingserver;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import site.ugaeng.localhostingserver.http.request.Request;
import site.ugaeng.localhostingserver.impl.socket.io.SocketDataLineWriter;
import site.ugaeng.localhostingserver.tunnel.TunnelRegisterRequest;
import site.ugaeng.localhostingserver.utils.ObjectUtils;

import java.io.IOException;
import java.net.Socket;

public class RequestTest {

    ObjectMapper om = new ObjectMapper();

    @Test
    void read_json() throws JsonProcessingException {
        // given
        final String json =
                "{\n" +
                "  \"requestLine\":{\n" +
                "    \"method\":\"POST\",\n" +
                "    \"uri\":\"/ugaeng/host\",\n" +
                "    \"version\":\"HTTP_1_1\"\n" +
                "  },\n" +
                "  \"headers\":{\n" +
                "    \"content-length\":\"33\",\n" +
                "    \"postman-token\":\"990857a6-7b27-433b-85d8-01ae1e1b9cf9\",\n" +
                "    \"host\":\"localhost:8080\",\n" +
                "    \"content-type\":\"application/json\",\n" +
                "    \"connection\":\"keep-alive\",\n" +
                "    \"cache-control\":\"no-cache\",\n" +
                "    \"accept-encoding\":\"gzip, deflate, br\",\n" +
                "    \"user-agent\":\"PostmanRuntime/7.36.1\",\n" +
                "    \"accept\":\"*/*\"\n" +
                "  },\n" +
                "  \"entity\":\"{\\r\\n \\\"domainName\\\" : \\\"ugaeng\\\"\\r\\n}\"\n" +
                "}";
        // when
        Request request = om.readValue(json, Request.class);

        // then
        System.out.println(request.getRequestLine());
        System.out.println(request.getHeaders());
        System.out.println(request.getEntity());
    }

    @Test
    void read_body() {
        // given
        String entity = "{\"tunnelName\" : \"hello\"}";

        // when
        TunnelRegisterRequest request = ObjectUtils.convertToObject(entity, TunnelRegisterRequest.class);

        // then
        System.out.println(request.getTunnelName());
    }
}
