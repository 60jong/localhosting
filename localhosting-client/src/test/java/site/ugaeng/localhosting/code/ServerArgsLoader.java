package site.ugaeng.localhosting.code;

import org.yaml.snakeyaml.Yaml;
import site.ugaeng.localhosting.env.ServerArgs;
import site.ugaeng.localhosting.exception.LocalhostingException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ServerArgsLoader {

    public static ServerArgs load() {
        try {
            final String yamlPath = System.getProperty("user.dir") + "/src/main/resources/localhosting.yml";
            File yamlFile = new File(yamlPath);

            return new Yaml().loadAs(new FileInputStream(yamlFile), ServerArgs.class);
        } catch (FileNotFoundException fileNotFoundEx) {
            throw new LocalhostingException(fileNotFoundEx);
        }

    }
}
