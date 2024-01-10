package site.ugaeng.localhosting;

import lombok.extern.slf4j.Slf4j;
import org.yaml.snakeyaml.Yaml;
import site.ugaeng.localhosting.env.Environment;

import java.io.*;

@Slf4j
public class Application {

    public static void main(String[] args) {
        log.info("## Localhosting start ##");

        configure(args);

        var server = new LocalForwardServer();
        server.run();
    }

    private static void configure(String[] args) {
        configEnvironmentProperties(args);
    }

    private static void configEnvironmentProperties(String[] args) {
        ClientArgs clientArgs = new ClientArgs(args);
        ServerArgs serverArgs = getServerArgs();

        // set Environment
        Environment.init(new HostingArgs(clientArgs, serverArgs));
    }

    private static ServerArgs getServerArgs() {
        try (InputStream systemArgsReader = getServerArgsReader()) {
            return new Yaml().load(systemArgsReader);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static InputStream getServerArgsReader() throws FileNotFoundException {
        final String systemEnvFilePath = System.getProperty("user.dir") + "\\src\\main\\resources\\localhosting.yml";

        return new FileInputStream(systemEnvFilePath);
    }
}