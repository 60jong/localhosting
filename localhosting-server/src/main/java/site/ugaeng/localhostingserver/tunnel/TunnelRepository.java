package site.ugaeng.localhostingserver.tunnel;

import java.util.Optional;

public interface TunnelRepository {

    void save(Tunnel tunnel);

    Optional<Tunnel> findByName(String tunnelName);
}
