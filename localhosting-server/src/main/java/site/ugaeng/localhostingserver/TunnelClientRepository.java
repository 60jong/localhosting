package site.ugaeng.localhostingserver;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Repository
public class TunnelClientRepository {

    private final Map<String, Socket> clientStore;

    public TunnelClientRepository() {
        clientStore = new HashMap<>();
    }

    public void save(String domainName, Socket socket) {
        clientStore.put(domainName, socket);
    }

    public Socket find(String domainName) {
        Socket socket = clientStore.get(domainName);
        log.info("find client : {}", socket);
        return socket;
    }
}
