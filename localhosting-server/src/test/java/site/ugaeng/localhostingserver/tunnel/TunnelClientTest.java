package site.ugaeng.localhostingserver.tunnel;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import site.ugaeng.localhostingserver.Response;
import site.ugaeng.localhostingserver.utils.IOUtils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.net.Socket;

import static site.ugaeng.localhostingserver.utils.IOUtils.*;

public class TunnelClientTest {

    final String TEST_HTTP_RESPONSE_JSON =
            "{\n" +
            "  \"statusLine\":{\n" +
            "    \"version\":\"HTTP_1_1\",\n" +
            "    \"reasonPhrase\":\"OK\",\n" +
            "    \"statusCode\": 200\n" +
            "  },\n" +
            "  \"headers\":{\n" +
            "    \"content-type\":\"application/json\"\n" +
            "  },\n" +
            "  \"entity\":\"{\\r\\n \\\"domainName\\\" : \\\"ugaeng\\\"\\r\\n}\"\n" +
            "}";
    @Test
    void socket() throws IOException {
        Socket socket = new Socket("localhost", 9000);

        BufferedWriter writer = getWriter(socket.getOutputStream());
        BufferedReader reader = getReader(socket.getInputStream());

        // register TUNNEL
        writer.write("TEST");
        writer.newLine();
        writer.flush();

        // receive REQUEST
        System.out.println("WAITING");
        String request = reader.readLine();

        // send RESPONSE
        writer.write(TEST_HTTP_RESPONSE_JSON);
        writer.newLine();
        writer.flush();

        reader.close();
        writer.close();
        socket.close();
    }


}
