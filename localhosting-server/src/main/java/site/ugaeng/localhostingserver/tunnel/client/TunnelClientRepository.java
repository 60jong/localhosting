package site.ugaeng.localhostingserver.forward;

import lombok.extern.slf4j.Slf4j;
import site.ugaeng.localhostingserver.tunnel.TunnelClient;

import java.util.HashMap;
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

    public Optional<TunnelClient> find(String name) {
        if (connectionMap.containsKey(name)) {
            TunnelClient connection = connectionMap.get(name);
            log.info("find connection : {}", connection.client());
            return Optional.ofNullable(connection);
        }
        return Optional.empty();
    }

    public int removeClosed() {
        int removes = 0;

        for (var entry : connectionMap.entrySet()) {
            TunnelClient client = entry.getValue();
            if (client.isClosed()) {
                connectionMap.remove(entry.getKey());
                removes++;
            }
        }

        return removes;
    }

    public void changeTunnelClient(String tunnelName, TunnelClient tunnelClient) {
        save(tunnelName, tunnelClient);
    }
}
