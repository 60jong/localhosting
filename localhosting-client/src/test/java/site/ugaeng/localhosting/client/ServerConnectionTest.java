package site.ugaeng.localhosting.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import site.ugaeng.localhosting.http.request.Request;

import java.io.*;
import java.net.Socket;

public class ServerConnectionTest {

    static ObjectMapper om = new ObjectMapper();

    public static void main(String[] args) throws IOException {
        // given
        Socket socket = new Socket("localhost", 9000);

        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        System.out.println(1);
        // register Tunnel
        writer.write("TEST");
        writer.newLine();
        writer.flush();

        System.out.println(2);
        // receive Request
        String requestJson = reader.readLine();
        Request request = om.readValue(requestJson, Request.class);

        System.out.println(3);
        // send Response
        final String responseJson =
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
        writer.write(responseJson);
        writer.newLine();
        writer.flush();
        System.out.println(4);

    }
}
