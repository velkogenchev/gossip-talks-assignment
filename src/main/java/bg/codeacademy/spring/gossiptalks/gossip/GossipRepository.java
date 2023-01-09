package bg.codeacademy.spring.gossiptalks.gossip;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface GossipRepository extends JpaRepository<Gossip, Long> {
  List<Gossip> getAllByUsername(String username);
}
