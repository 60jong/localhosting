package site.ugaeng.localhostingserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
public class LocalhostingServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(LocalhostingServerApplication.class, args);
    }
}
