package site.ugaeng.localhosting.env;

import java.util.HashMap;
import java.util.Map;

import static site.ugaeng.localhosting.env.EnvironmentConst.*;

public class Environment {

    private static final Map<String, Object> properties = new HashMap<>();

    public static void init(HostingArgs args) {
        // client arguments
        ClientArgs clientArgs = args.getClientArgs();
        properties.put(LOCAL_PROCESS_PORT, clientArgs.getLocalProcessPort());
        properties.put(TUNNEL_NAME, clientArgs.getTunnelName());

        // server arguments
        ServerArgs serverArgs = args.getServerArgs();
        properties.put(PROXY_SERVER_ADDR, serverArgs.getProxyServerAddr());
        properties.put(TUNNELING_SERVER_ADDR, serverArgs.getTunnelRegisterServerAddr());
    }

    public static Object getProperty(String key) {
        return properties.get(key);
    }

    public static String getTunnelName() {
        return (String) getProperty(TUNNEL_NAME);
    }
}
