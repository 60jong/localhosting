package site.ugaeng.localhostingserver.tunnel.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.ugaeng.localhostingserver.tunnel.repository.TunnelRepository;
import site.ugaeng.localhostingserver.utils.TunnelDomainNameGenerator;
import site.ugaeng.localhostingserver.tunnel.Tunnel;

import static org.springframework.util.StringUtils.hasText;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class TunnelService {

    private final TunnelRepository tunnelRepository;

    @Transactional
    public String create(TunnelCreateRequest request) {
        final String domainName = getValidTunnelDomainName(request.getName());

        // save Tunnel
        Tunnel tunnel = new Tunnel(domainName);
        tunnelRepository.save(tunnel);

        return tunnel.getName();
    }

    private String getValidTunnelDomainName(String domainName) {
        if (hasText(domainName)) {
            validateTunnelDomainName(domainName);
            return domainName;
        }
        return TunnelDomainNameGenerator.generateRandomName();
    }

    private void validateTunnelDomainName(String domainName) {
        checkDomainNameExists(domainName);
    }

    private void checkDomainNameExists(String domainName) {
        if (tunnelRepository.exists(domainName)) {
            throw new RuntimeException();
        }
    }
}
