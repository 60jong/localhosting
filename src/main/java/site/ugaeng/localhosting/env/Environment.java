package site.ugaeng.localhosting.env;

import site.ugaeng.localhosting.HostingArgs;

import java.util.HashMap;
import java.util.Map;

import static site.ugaeng.localhosting.env.EnvironmentConst.*;

public class Environment {

    private static final Map<String, Object> properties = new HashMap<>();

    public static void init(HostingArgs args) {
        properties.put(HOSTING_PORT, args.getHostingPort());
        properties.put(HOSTING_DOMAIN_NAME, args.getDomainName());
    }

    public static Object getProperty(String key) {
        return properties.get(key);
    }
}
