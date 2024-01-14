package site.ugaeng.localhostingserver.tunnel;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import site.ugaeng.localhostingserver.TunnelConnection;
import site.ugaeng.localhostingserver.tunnel.service.TunnelService;

import java.io.IOException;
import java.util.Set;

@RequiredArgsConstructor
@RequestMapping("/tunnel")
@RestController
public class TunnelController {

    private final TunnelConnections tunnelConnections;
    private final TunnelService tunnelService;

    @PostMapping
    public String createTunnel(@RequestBody TunnelCreateRequest tunnelRequest,
                               HttpServletResponse httpResponse) throws IOException {

        String tunnelName = tunnelService.create(tunnelRequest);

        TunnelConnection tunnelConnection = new TunnelConnection(httpResponse.getOutputStream());
        tunnelConnections.put(tunnelName, tunnelConnection);

        return tunnelName;
    }

    @GetMapping("/connections/now")
    public String getCurrentTunnelConnections() {
        Set<String> domainNames = tunnelConnections.getDomainNames();

        StringBuilder sb = new StringBuilder();
        for (var name : domainNames) {
            TunnelConnection connection = tunnelConnections.get(name);

            sb.append(name);
            sb.append(" / ");
            sb.append(connection);
            sb.append("\n");
        }
        return sb.toString();
    }
}
