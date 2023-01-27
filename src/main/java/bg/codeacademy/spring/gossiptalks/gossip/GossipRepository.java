package bg.codeacademy.spring.gossiptalks.gossip;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface GossipRepository extends JpaRepository<Gossip, Long> {
  @Query("SELECT g FROM Gossip g JOIN g.user u WHERE u.username = :username")
  Page<Gossip> getAllByUsername(String username, Pageable pageable);
}
