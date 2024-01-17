package site.ugaeng.localhostingserver.tunnel;

import org.springframework.stereotype.Service;
import site.ugaeng.localhostingserver.tunneling.client.TunnelClientRepository;

@Service
public class TunnelService {

    private final TunnelClientRepository tunnelClientRepository;

    public TunnelService() {
        tunnelClientRepository = TunnelClientRepository.getInstance();
    }

    public boolean existsByTunnelName(final String tunnelName) {
        return tunnelClientRepository.existsByTunnelName(tunnelName);
    }
}
