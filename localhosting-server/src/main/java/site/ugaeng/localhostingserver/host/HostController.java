package site.ugaeng.localhostingserver.host;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import site.ugaeng.localhostingserver.http.Response;
import site.ugaeng.localhostingserver.forward.RequestForwarder;
import site.ugaeng.localhostingserver.http.Request;

import java.io.IOException;

@RequiredArgsConstructor
@RestController
public class HostController {

    private final RequestForwarder forwarder;

    @RequestMapping("/{tunnelName}/host/**")
    public void forwardRequest(@PathVariable String tunnelName,
                               HttpServletRequest httpRequest,
                               HttpServletResponse httpResponse) throws IOException
    {
        Request request = Request.readFromHttpServletRequest(httpRequest);

        Response response = forwarder.forwardRequestForTunnel(tunnelName, request);

        writeResponse(response, httpResponse);
    }

    private void writeResponse(Response response, HttpServletResponse httpResponse) throws IOException {
        // statusCode
        reflectStatusLine(response, httpResponse);

        // header
        reflectHeaders(response, httpResponse);

        // entity
        writeEntity(response, httpResponse);
    }

    private static void reflectStatusLine(Response response, HttpServletResponse httpResponse) {
        final int statusCode = response.getStatusLine().getStatusCode();
        httpResponse.setStatus(statusCode);
    }

    private static void reflectHeaders(Response response, HttpServletResponse httpResponse) {
        response.getHeaders()
                .entrySet()
                .stream()
                .forEach(entry -> httpResponse.setHeader(entry.getKey(), entry.getValue()));
    }

    private static void writeEntity(Response response, HttpServletResponse httpResponse) throws IOException {
        final String entity = response.getEntity();
        httpResponse.getWriter()
                .write(entity);
    }
}
