package site.ugaeng.localhosting.http.local;

import site.ugaeng.localhosting.env.Environment;
import site.ugaeng.localhosting.http.local.client.LocalProcessHttpRequestClient;
import site.ugaeng.localhosting.http.local.client.LocalProcessRequestClient;

import static site.ugaeng.localhosting.env.EnvironmentConst.*;

public class LocalRequests {

    public static LocalProcessRequestClient getLocalRequestClient() {
        return LocalProcessHttpRequestClient.getInstance();
    }

    public static int getLocalHostingPort() {
        return (int) Environment.getProperty(HOSTING_PORT);
    }

    public static String getLocalRequestHost() {
        return "http://localhost:" + getLocalHostingPort();
    }
}
