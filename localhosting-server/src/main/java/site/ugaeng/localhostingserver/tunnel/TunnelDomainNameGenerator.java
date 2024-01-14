package site.ugaeng.localhostingserver.tunnel;

public class TunnelDomainNameGenerator {

    public static String generateRandomName() {

        return "domain-" + System.currentTimeMillis();
    }
}
