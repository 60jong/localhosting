package site.ugaeng.localhostingserver.impl.socket.tunneling.client;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class TunnelClientRepository {

    private static TunnelClientRepository instance;

    private final Map<String, TunnelClient> tunnelClientMap;

    private TunnelClientRepository() {
        this.tunnelClientMap = new ConcurrentHashMap<>();
    }

    public static TunnelClientRepository getInstance() {
        if (instance == null) {
            instance = new TunnelClientRepository();
        }

        return instance;
    }

    public void save(String tunnelName, TunnelClient client) {
        tunnelClientMap.put(tunnelName, client);
    }

    public Optional<TunnelClient> find(String tunnelName) {
        return Optional.ofNullable(tunnelClientMap.get(tunnelName));
    }
}
