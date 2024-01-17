package site.ugaeng.localhostingserver.host;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class HostController {

    private final HostService hostService;

    @RequestMapping("/host/{tunnelName}/**")
    public void forwardRequest(@PathVariable String tunnelName,
                               HttpServletRequest httpRequest,
                               HttpServletResponse httpResponse)
    {
        hostService.host(tunnelName, httpRequest, httpResponse);
    }
}
