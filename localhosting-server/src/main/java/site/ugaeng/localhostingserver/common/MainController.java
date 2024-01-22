package site.ugaeng.localhostingserver.common;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import site.ugaeng.localhostingserver.tunneling.client.TunnelClientRepository;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class MainController {

    private final TunnelClientRepository tunnelClientRepository;

    public MainController() {
        tunnelClientRepository = TunnelClientRepository.getInstance();
    }

    @GetMapping
    public String home(Model model) {

        model.addAttribute("currentTunnelUrls", getCurrentTunnelUrls());

        return "home";
    }

    private List<String> getCurrentTunnelUrls() {
        List<String> tunnelNames = tunnelClientRepository.findAllTunnelNames();

        return tunnelNames.stream()
                          .map(tunnelName -> tunnelName + ".localhosting.do-main.site")
                          .collect(Collectors.toList());
    }
}
