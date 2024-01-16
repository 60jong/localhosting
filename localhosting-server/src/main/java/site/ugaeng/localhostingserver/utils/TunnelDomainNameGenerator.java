package site.ugaeng.localhostingserver.utils;

public class TunnelDomainNameGenerator {

    public static String generateRandomName() {

        return "domain-" + System.currentTimeMillis();
    }
}
