package site.ugaeng.localhostingserver.tunnel.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import site.ugaeng.localhostingserver.tunnel.TunnelCreateRequest;
import site.ugaeng.localhostingserver.tunnel.repository.TunnelRepository;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class TunnelServiceTest {

    @InjectMocks
    TunnelService tunnelService;

    @Mock
    TunnelRepository tunnelRepository;

    @Test
    @DisplayName("요청한 이름이 유효하면 배정한다.")
    void create_tunnel() {
        // given
        String tunnelDomainName = "myTunnel";
        TunnelCreateRequest request = new TunnelCreateRequest(tunnelDomainName);

        // when
        String domainName = tunnelService.create(request);

        // then
        assertThat(domainName).isEqualTo(tunnelDomainName);
    }

    @Test
    @DisplayName("요청한 이름이 없으면 랜덤 이름 배정한다.(NOT NULL)")
    void create_tunnel_by_null_name() {
        // given
        TunnelCreateRequest request = new TunnelCreateRequest();

        // when
        String domainName = tunnelService.create(request);

        // then
        assertThat(domainName).isNotNull();
    }
}