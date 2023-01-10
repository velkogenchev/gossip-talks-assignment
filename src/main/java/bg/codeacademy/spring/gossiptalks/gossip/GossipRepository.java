package bg.codeacademy.spring.gossiptalks.gossip;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GossipRepository extends JpaRepository<Gossip, Long> {
  Page<Gossip> getAllByUsername(String username, Pageable pageable);
}
