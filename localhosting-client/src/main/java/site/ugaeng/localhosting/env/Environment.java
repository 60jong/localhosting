package site.ugaeng.localhosting.env;

import java.util.HashMap;
import java.util.Map;

import static site.ugaeng.localhosting.env.EnvironmentConst.*;
import static site.ugaeng.localhosting.impl.amqp.RabbitMQConstant.*;

public class Environment {

    private static final Map<String, Object> properties = new HashMap<>();

    public static void init(TunnelArgs args) {
        // client arguments
        properties.put(LOCAL_PROCESS_PORT, args.getLocalProcessPort());
        properties.put(TUNNEL_NAME, args.getTunnelName());

        // server arguments
        properties.put(TUNNEL_PLATFORM, args.getTunnelingPlatform());
        properties.put(PROXY_SERVER_ADDR, args.getProxyServerAddr());
        properties.put(TUNNELING_SERVER_ADDR, args.getTunnelingServerAddr());

        // rmq arguments
        properties.put(RMQ_HOST, args.getRmqHost());
        properties.put(RMQ_PORT, args.getRmqPort());
        properties.put(RMQ_USERNAME, args.getRmqUsername());
        properties.put(RMQ_PASSWORD, args.getRmqPassword());
    }

    public static Object getProperty(String key) {
        return properties.get(key);
    }

    public static String getTunnelName() {
        return (String) getProperty(TUNNEL_NAME);
    }
}
