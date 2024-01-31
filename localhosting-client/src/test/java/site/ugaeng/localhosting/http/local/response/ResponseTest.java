package site.ugaeng.localhosting.http.local.response;

import org.junit.jupiter.api.Test;
import site.ugaeng.localhosting.http.ProtocolVersion;
import site.ugaeng.localhosting.http.response.Response;
import site.ugaeng.localhosting.http.response.StatusLine;
import site.ugaeng.localhosting.util.ObjectUtils;

import java.util.HashMap;
import java.util.Map;

public class ResponseTest {

    @Test
    void convert_response_to_json() {
        StatusLine statusLine = new StatusLine(ProtocolVersion.HTTP_1_1, 200, "OK");
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "text/html");
        headers.put("Content-Length", "192");

        String entity = "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "<meta charset=\"UTF-8\">\n" +
                "<title>UGAENG</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<div class=\"container\">\n" +
                "<h1> UGAENG </h1>\n" +
                "INDEX\n" +
                "</div>\n" +
                "</body>\n" +
                "</html>";
        Response response = new Response(statusLine, headers, entity);

        System.out.println(ObjectUtils.convertToString(response));
    }
}
