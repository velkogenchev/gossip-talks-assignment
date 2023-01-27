package bg.codeacademy.spring.gossiptalks.gossip;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import javax.persistence.Id;
import javax.validation.constraints.Size;

@Getter
@Setter
@Accessors(chain = true)
public class GossipDto {
  @Id
  private Long id;
  private String username;
  @Size(min = 1, max = 255)
  private String text;
}
