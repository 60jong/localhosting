package site.ugaeng.localhostingserver.health;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import site.ugaeng.localhostingserver.tunneling.client.TunnelClientRepository;

import java.util.List;

@RequestMapping("/health")
@Controller
public class HealthController {

    private final TunnelClientRepository tunnelClientRepository;

    public HealthController() {
        tunnelClientRepository = TunnelClientRepository.getInstance();
    }

    @GetMapping("/tunnels")
    @ResponseBody
    public List<String> getTunnels() {
        return tunnelClientRepository.findAllTunnelNames();
    }

    @DeleteMapping("/tunnels")
    @ResponseBody
    public String deleteTunnel(@RequestParam String tunnelName) {
        tunnelClientRepository.deleteTunnel(tunnelName);

        return "DELETE SUCCESS";
    }
}
