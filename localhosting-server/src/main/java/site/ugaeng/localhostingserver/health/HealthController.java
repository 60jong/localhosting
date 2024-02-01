package site.ugaeng.localhostingserver.health;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import site.ugaeng.localhostingserver.impl.socket.tunneling.client.TunnelClientRepository;
import site.ugaeng.localhostingserver.tunnel.repository.TunnelRepository;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/health")
@Controller
public class HealthController {

    private final TunnelRepository tunnelRepository;

    @GetMapping("/tunnels")
    @ResponseBody
    public List<String> getTunnels() {
        return tunnelRepository.findAllTunnelName();
    }

    @DeleteMapping("/tunnels")
    @ResponseBody
    public String deleteTunnel(@RequestParam String tunnelName) {
        tunnelRepository.deleteTunnel(tunnelName);

        return "DELETE SUCCESS";
    }

    @DeleteMapping("/tunnels/clear")
    @ResponseBody
    public String deleteTunnel() {
        tunnelRepository.clear();

        return "DELETE ALL SUCCESS";
    }
}
