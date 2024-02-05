package site.ugaeng.localhostingserver.tunnel.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.ugaeng.localhostingserver.tunnel.TunnelRegisterRequest;
import site.ugaeng.localhostingserver.tunnel.domain.Address;
import site.ugaeng.localhostingserver.tunnel.domain.Tunnel;
import site.ugaeng.localhostingserver.tunnel.repository.TunnelRepository;

@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class SocketTunnelService implements TunnelService {

    private final TunnelRepository tunnelRepository;

    @Transactional
    @Override
    public void create(String remoteAddr, int remotePort, TunnelRegisterRequest request) {
        saveTunnel(remoteAddr, remotePort, request);
        log.info("new Tunnel [{}] created", request.getTunnelName());
    }

    private void saveTunnel(String remoteAddr, int remotePort, TunnelRegisterRequest request) {
        tunnelRepository.save(new Tunnel(request.getTunnelName(), new Address(remoteAddr, remotePort)));
    }

    @Override
    public Tunnel findByName(String tunnelName) {
        return tunnelRepository.findByName(tunnelName)
                .orElseThrow();
    }
}
