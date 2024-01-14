package site.ugaeng.localhostingserver.tunnel;

import org.springframework.stereotype.Component;
import site.ugaeng.localhostingserver.TunnelConnection;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component
public class TunnelConnections {

    private final Map<String, TunnelConnection> tunnelConnectionMap;

    public TunnelConnections() {
        tunnelConnectionMap = new HashMap<>();
    }

    public void put(String domainName, TunnelConnection connection) {
        tunnelConnectionMap.put(domainName, connection);
    }

    public TunnelConnection get(String domainName) {
        return tunnelConnectionMap.get(domainName);
    }

    public Set<String> getDomainNames() {
        return tunnelConnectionMap.keySet();
    }
}
