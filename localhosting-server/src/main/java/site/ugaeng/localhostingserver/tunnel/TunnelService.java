package site.ugaeng.localhostingserver.tunnel;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TunnelService {

    private final TunnelRepository tunnelRepository;

    public void create(String remoteAddr, int remotePort, String tunnelName) {
        tunnelRepository.save(new Tunnel(tunnelName, new Address(remoteAddr, remotePort)));
    }

    public Tunnel findByName(String tunnelName) {
        return tunnelRepository.findByName(tunnelName)
                               .orElseThrow();
    }

}
