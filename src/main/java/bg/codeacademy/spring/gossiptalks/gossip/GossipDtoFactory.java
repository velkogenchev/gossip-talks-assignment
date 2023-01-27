package bg.codeacademy.spring.gossiptalks.gossip;

import org.springframework.stereotype.Component;

@Component
public class GossipDtoFactory {
  public GossipDto createFromEntity(Gossip gossip) {
    return new GossipDto()
        .setId(gossip.getId())
        .setUsername(gossip.getUser().getUsername())
        .setText(gossip.getText());
  }
}
