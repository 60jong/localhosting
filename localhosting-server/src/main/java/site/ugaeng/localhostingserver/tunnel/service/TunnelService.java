package site.ugaeng.localhostingserver.tunnel.service;

import site.ugaeng.localhostingserver.tunnel.TunnelRegisterRequest;
import site.ugaeng.localhostingserver.tunnel.domain.Tunnel;

public interface TunnelService {

    void create(String remoteAddr, int remotePort, TunnelRegisterRequest request);

    Tunnel findByName(String tunnelName);
}
