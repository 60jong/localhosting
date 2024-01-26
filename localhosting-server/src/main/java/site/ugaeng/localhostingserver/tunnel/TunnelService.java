package site.ugaeng.localhostingserver.tunnel;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.ugaeng.localhostingserver.tunnel.domain.Address;
import site.ugaeng.localhostingserver.tunnel.domain.Tunnel;
import site.ugaeng.localhostingserver.tunnel.repository.TunnelRepository;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class TunnelService {

    private final TunnelRepository tunnelRepository;

    @Transactional
    public void create(String remoteAddr, int remotePort, TunnelRegisterRequest request) {
        tunnelRepository.save(new Tunnel(request.getTunnelName(), new Address(remoteAddr, remotePort)));
    }

    public Tunnel findByName(String tunnelName) {
        return tunnelRepository.findByName(tunnelName)
                               .orElseThrow();
    }

}
