package site.ugaeng.localhosting.http.local;

import site.ugaeng.localhosting.HostingParams;
import site.ugaeng.localhosting.http.local.client.LocalProcessHttpRequestClient;
import site.ugaeng.localhosting.http.local.client.LocalProcessRequestClient;

import java.util.HashMap;
import java.util.Map;

public class LocalRequests {

    private static final String LOCAL_REQUEST_CLIENT = "LOCAL_REQUEST_CLIENT";
    private static final String LOCAL_REQUEST_PORT = "LOCAL_REQUEST_PORT";

    private static final Map<String, Object> localRequestVariables = new HashMap<>();

    public static void init(HostingParams params) {
        localRequestVariables.put(LOCAL_REQUEST_CLIENT, LocalProcessHttpRequestClient.getInstance());
        localRequestVariables.put(LOCAL_REQUEST_PORT, params.getLocalPort());
    }

    public static LocalProcessRequestClient getLocalRequestClient() {
        return (LocalProcessRequestClient) localRequestVariables.get(LOCAL_REQUEST_CLIENT);
    }

    public static int getLocalRequestPort() {
        return (int) localRequestVariables.get(LOCAL_REQUEST_PORT);
    }

    public static String getLocalRequestHost() {
        return "http://localhost:" + getLocalRequestPort();
    }
}
