package site.ugaeng.localhosting.http.local;

import site.ugaeng.localhosting.env.Environment;
import site.ugaeng.localhosting.http.local.client.LocalProcessHttpClient;
import site.ugaeng.localhosting.client.LocalProcessClient;

import static site.ugaeng.localhosting.env.EnvironmentConst.*;

public class LocalRequests {

    public static LocalProcessClient getLocalRequestHttpClient() {
        return LocalProcessHttpClient.getInstance();
    }

    public static int getLocalHostingPort() {
        return (int) Environment.getProperty(LOCAL_PROCESS_PORT);
    }

    public static String getLocalRequestAddr() {
        return "http://localhost:" + getLocalHostingPort();
    }
}
