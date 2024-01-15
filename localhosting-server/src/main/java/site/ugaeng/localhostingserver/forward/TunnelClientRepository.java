package site.ugaeng.localhostingserver.route;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import site.ugaeng.localhostingserver.tunnel.TunnelClient;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Repository
public class TunnelClientRepository {

    private final Map<String, TunnelClient> connectionMap;

    public TunnelClientRepository() {
        connectionMap = new HashMap<>();
    }

    public void save(String domainName, TunnelClient connection) {
        connectionMap.put(domainName, connection);
    }

    public TunnelClient find(String domainName) {
        TunnelClient connection = connectionMap.get(domainName);
        log.info("find connection : {}", connection);

        return connection;
    }
}
