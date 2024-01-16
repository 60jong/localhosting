package site.ugaeng.localhostingserver.tunnel.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import site.ugaeng.localhostingserver.tunnel.Tunnel;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Repository
public class TunnelMemoryRepository implements TunnelRepository {

    private final Map<String, Tunnel> tunnelStore;

    public TunnelMemoryRepository() {
        this.tunnelStore = new HashMap<>();
    }

    @Override
    public void save(Tunnel tunnel) {
        tunnelStore.put(tunnel.getName(), tunnel);
    }

    @Override
    public boolean exists(String domainName) {
        return tunnelStore.containsKey(domainName);
    }
}
