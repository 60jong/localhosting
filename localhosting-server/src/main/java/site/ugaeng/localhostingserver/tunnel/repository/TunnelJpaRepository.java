package site.ugaeng.localhostingserver.tunnel.repository;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import site.ugaeng.localhostingserver.tunnel.domain.Tunnel;

import java.util.List;
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

    @Override
    public List<String> findAllTunnelName() {
        return em.createQuery("select t.name from Tunnel t", String.class)
                 .getResultList();
    }

    @Override
    public void deleteTunnel(String tunnelName) {
        em.createQuery("delete from Tunnel t where t.name = :name")
          .setParameter("name", tunnelName);
    }

    @Override
    public void clear() {
        em.createQuery("delete from Tunnel t");
    }
}
