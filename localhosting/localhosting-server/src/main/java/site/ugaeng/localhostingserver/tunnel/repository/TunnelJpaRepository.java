package site.ugaeng.localhostingserver.tunnel.repository;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import site.ugaeng.localhostingserver.tunnel.Tunnel;

@RequiredArgsConstructor
public class TunnelJpaRepository implements TunnelRepository {

    private final EntityManager em;

    @Override
    public void save(Tunnel tunnel) {
        em.persist(tunnel);
    }

    @Override
    public boolean exists(String domainName) {
        return em.createQuery("select t from Tunnel t where t.domainName = :domainName")
                .setParameter("domainName", domainName)
                .getResultStream()
                .findAny()
                .isPresent();
    }
}
