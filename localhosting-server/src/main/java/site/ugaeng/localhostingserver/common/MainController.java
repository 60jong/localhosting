package site.ugaeng.localhostingserver.common;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping
    public String home() {
        return "home";
    }
}
