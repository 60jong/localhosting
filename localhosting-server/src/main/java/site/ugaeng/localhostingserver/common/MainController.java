package site.ugaeng.localhostingserver.common;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import site.ugaeng.localhostingserver.tunnel.repository.TunnelRepository;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class MainController {

    private final TunnelRepository tunnelRepository;

    public MainController(TunnelRepository tunnelRepository) {
        this.tunnelRepository = tunnelRepository;
    }

    @GetMapping
    public String home(Model model) {
        model.addAttribute("currentTunnelUrls", getCurrentTunnelUrls());

        return "home";
    }

    private List<String> getCurrentTunnelUrls() {
        List<String> tunnelNames = tunnelRepository.findAllTunnelName();

        return tunnelNames.stream()
                          .map(tunnelName -> tunnelName + ".localhosting.do-main.site")
                          .collect(Collectors.toList());
    }
}
