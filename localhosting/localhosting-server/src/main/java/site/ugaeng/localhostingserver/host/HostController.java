package site.ugaeng.localhostingserver.host;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import site.ugaeng.localhostingserver.TunnelClientRepository;
import site.ugaeng.localhostingserver.http.Request;
import site.ugaeng.localhostingserver.tunnel.repository.TunnelRepository;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;

@RequiredArgsConstructor
@RestController
public class HostController {

    private final TunnelRepository tunnelRepository;
    private final TunnelClientRepository tunnelClientRepository;
    private ObjectMapper om = new ObjectMapper();
    @RequestMapping("/{tunnelDomainName}/host")
    public String forwardRequest(@PathVariable String tunnelDomainName,
                                  HttpServletRequest httpRequest) throws IOException {

        Request request = Request.readFromHttpServletRequest(httpRequest);
        Socket client = tunnelClientRepository.find(tunnelDomainName);

        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
        String s = om.writeValueAsString(request);
        System.out.println(s);
        writer.write(s);
        writer.flush();

        return "SUCCESS";
    }
}
