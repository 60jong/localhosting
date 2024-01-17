package site.ugaeng.localhostingserver.tunnel.client;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
public class TunnelClientRepository {

    private static TunnelClientRepository instance;

    private final Map<String, TunnelClient> connectionMap;

    private TunnelClientRepository() {
        connectionMap = new HashMap<>();
    }

    public static TunnelClientRepository getInstance() {
        if (instance == null) {
            instance = new TunnelClientRepository();
            return instance;
        }
        return instance;
    }

    public void save(String tunnelName, TunnelClient connection) {
        connectionMap.put(tunnelName, connection);
    }

    public void changeTunnelClient(String tunnelName, TunnelClient tunnelClient) {
        save(tunnelName, tunnelClient);
    }

    public Optional<TunnelClient> find(String name) {
        if (connectionMap.containsKey(name)) {
            TunnelClient connection = connectionMap.get(name);
            log.info("find connection : {}", connection.client());
            return Optional.ofNullable(connection);
        }
        return Optional.empty();
    }

    /* For Health Check*/
    public List<String> findAllTunnelNames() {
        return connectionMap.keySet()
                .stream()
                .toList();
    }

    public void deleteTunnel(String tunnelName) {
        connectionMap.remove(tunnelName);
    }

    public boolean existsByTunnelName(String tunnelName) {
        return connectionMap.containsKey(tunnelName);
    }
}
