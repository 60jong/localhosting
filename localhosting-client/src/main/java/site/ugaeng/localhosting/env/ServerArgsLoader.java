package site.ugaeng.localhosting.env;

import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.error.YAMLException;
import site.ugaeng.localhosting.exception.LocalhostingException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ServerArgsLoader {

    private static final String YAML_PATH = "/server.conf/localhosting.yml";

    public static ServerArgs load() {
        try {
            final String yamlPath = System.getProperty("user.dir") + YAML_PATH;

            File yamlFile = new File(yamlPath);

            return new Yaml().loadAs(new FileInputStream(yamlFile), ServerArgs.class);
        } catch (FileNotFoundException fileNotFoundEx) {
            throw new LocalhostingException(fileNotFoundEx);
        } catch (YAMLException yamlEx) {
            throw new LocalhostingException("illegal YAML arguments");
        }

    }
}
