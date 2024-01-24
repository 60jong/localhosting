package site.ugaeng.localhostingserver.tunnel.repository;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import site.ugaeng.localhostingserver.tunnel.domain.Tunnel;

import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class TunnelJpaRepository implements TunnelRepository {

    private final EntityManager em;

    public void save(Tunnel tunnel) {
        em.persist(tunnel);
    }

    @Override
    public Optional<Tunnel> findByName(String tunnelName) {
        return em.createQuery("select t from Tunnel t where t.name = :name")
                 .setParameter("name", tunnelName)
                 .getResultStream()
                 .findAny();
    }
}
