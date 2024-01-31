package site.ugaeng.localhostingserver.impl.socket.tunneling.client;

import lombok.extern.slf4j.Slf4j;
import site.ugaeng.localhostingserver.tunnel.domain.Address;
import site.ugaeng.localhostingserver.tunnel.domain.Tunnel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

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
        Address address = new Address(connection.getClientAddr(), connection.getClientPort());
        Tunnel tunnel = new Tunnel(tunnelName, address);

        connectionMap.put(tunnelName, connection);
    }

    public void changeTunnelClient(String tunnelName, TunnelClient tunnelClient) {
        save(tunnelName, tunnelClient);
    }

    public Optional<TunnelClient> find(String name) {
        if (connectionMap.containsKey(name)) {
            TunnelClient connection = connectionMap.get(name);
            log.info("tunnel client found : {}", connection.client());
            return Optional.ofNullable(connection);
        }
        return Optional.empty();
    }

    /* For Health Check*/
    public List<String> findAllTunnelNames() {
        return connectionMap.keySet()
                .stream()
                .collect(Collectors.toList());
    }

    public void deleteTunnel(String tunnelName) {
        closeTunnel(tunnelName);
        removeTunnel(tunnelName);
    }

    private void closeTunnel(String tunnelName) {
        TunnelClient tunnelClient = connectionMap.get(tunnelName);
        tunnelClient.close();
    }

    private void removeTunnel(String tunnelName) {
        connectionMap.remove(tunnelName);
    }
    
    public void deleteAllTunnel() {
        connectionMap.clear();
    }
}
