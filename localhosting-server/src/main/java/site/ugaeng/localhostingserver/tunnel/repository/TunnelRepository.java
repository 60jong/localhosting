package site.ugaeng.localhostingserver.tunnel.repository;

import site.ugaeng.localhostingserver.tunnel.Tunnel;

public interface TunnelRepository {

    void save(Tunnel tunnel);

    boolean exists(String domainName);
}
