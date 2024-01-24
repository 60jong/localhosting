package site.ugaeng.localhostingserver.tunnel.repository;

import site.ugaeng.localhostingserver.tunnel.domain.Tunnel;

import java.util.Optional;

public interface TunnelRepository {

    void save(Tunnel tunnel);

    Optional<Tunnel> findByName(String tunnelName);
}
