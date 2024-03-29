package site.ugaeng.localhostingserver.host;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import site.ugaeng.localhostingserver.forward.RequestForwarder;
import site.ugaeng.localhostingserver.http.request.Request;
import site.ugaeng.localhostingserver.http.request.reader.RequestReader;
import site.ugaeng.localhostingserver.http.response.Response;
import site.ugaeng.localhostingserver.tunnel.domain.Tunnel;
import site.ugaeng.localhostingserver.tunnel.service.TunnelService;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
@Service
public class HostService {

    private final TunnelService tunnelService;
    private final RequestForwarder forwarder;

    public void host(String tunnelName, HttpServletRequest httpRequest, HttpServletResponse httpResponse) {
        Tunnel tunnel = tunnelService.findByName(tunnelName);

        hostForTunnel(tunnel, httpRequest, httpResponse);
    }

    private void hostForTunnel(Tunnel tunnel, HttpServletRequest httpRequest, HttpServletResponse httpResponse) {
        try
        {
            Request request = RequestReader.readFromHttpServletRequest(httpRequest);

            Response response = forwarder.forwardRequestForTunnel(tunnel, request);

            writeResponse(response, httpResponse);
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
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
