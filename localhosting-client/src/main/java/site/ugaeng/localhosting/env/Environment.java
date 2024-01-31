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
        properties.put(RMQ_PORT, args.getRmqPort() != null ? args.getRmqPort() : RMQ_DEFAULT_PORT);
        properties.put(RMQ_USERNAME, args.getRmqUsername());
        properties.put(RMQ_PASSWORD, args.getRmqPassword());
        properties.put(RMQ_WORK_CONSUMERS, args.getRmqWorkConsumers() != null ? args.getRmqWorkConsumers() : RMQ_DEFAULT_WORK_CONSUMERS);
    }

    public static Object getProperty(String key) {
        return properties.get(key);
    }

    public static String getTunnelName() {
        return (String) getProperty(TUNNEL_NAME);
    }

    public static String getRabbitMQHost() {
        return (String) getProperty(RMQ_HOST);
    }

    public static int getRabbitMQPORT() {
        return (int) getProperty(RMQ_PORT);
    }

    public static String getRabbitMQUsername() {
        return (String) getProperty(RMQ_USERNAME);
    }

    public static String getRabbitMQPassword() {
        return (String) getProperty(RMQ_PASSWORD);
    }

    public static int getRabbitMQWorkConsumers() {
        return (int) getProperty(RMQ_WORK_CONSUMERS);
    }
}
