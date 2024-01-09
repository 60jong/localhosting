package site.ugaeng.localhosting;

import lombok.extern.slf4j.Slf4j;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.util.Map;

@Slf4j
public class Application {

    public static void main(String[] args) {
        log.info("...Localhosting started...");

        log.info("...setting Localhosting System started...");
        setup();
        log.info("...setting Localhosting System ended...");
    }

    private static void setup() {

        Map<String, Object> systemEnvs = getSystemEnvs();

        for (Map.Entry<String, Object> entry : systemEnvs.entrySet()) {
            log.info("System Environments : " + entry);
        }
    }

    private static Map<String, Object> getSystemEnvs() {
        try (InputStream systemEnvReader = getSystemEnvReader())
        {
            return new Yaml().load(systemEnvReader);
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static InputStream getSystemEnvReader() throws FileNotFoundException {
        final String systemEnvFilePath = System.getProperty("user.dir") + "\\src\\main\\resources\\localhosting.yml";

        return new FileInputStream(systemEnvFilePath);
    }
}