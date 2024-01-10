package site.ugaeng.localhosting.env;

import site.ugaeng.localhosting.ClientArgs;
import site.ugaeng.localhosting.HostingArgs;
import site.ugaeng.localhosting.ServerArgs;

import java.util.HashMap;
import java.util.Map;

import static site.ugaeng.localhosting.env.EnvironmentConst.*;

public class Environment {

    private static final Map<String, Object> properties = new HashMap<>();

    public static void init(HostingArgs args) {
        // client arguments
        ClientArgs clientArgs = args.getClientArgs();
        properties.put(HOSTING_PORT, clientArgs.getHostingPort());
        properties.put(HOSTING_DOMAIN_NAME, clientArgs.getDomainName());

        // server arguments
        ServerArgs serverArgs = args.getServerArgs();
        properties.put(PROXY_SERVER_HOST, serverArgs.getProxyServerHost());
    }

    public static Object getProperty(String key) {
        return properties.get(key);
    }
}
