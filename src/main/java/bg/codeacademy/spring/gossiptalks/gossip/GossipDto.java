package bg.codeacademy.spring.gossiptalks.gossip;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Getter
@Setter
public class GossipDto {
  @Id
  private Long id;
  private String username;
  private String text;
}
