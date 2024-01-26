package site.ugaeng.localhostingserver.tunnel;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/tunnels")
public class TunnelController {

    private final TunnelService tunnelService;

    @PostMapping
    public String createTunnel(HttpServletRequest httpRequest,
                               @RequestBody TunnelRegisterRequest registerRequest) {
        tunnelService.create(httpRequest.getRemoteAddr(), httpRequest.getRemotePort(), registerRequest);

        return "SUCCESS";
    }
}
