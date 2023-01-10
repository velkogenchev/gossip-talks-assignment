package bg.codeacademy.spring.gossiptalks.gossip;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GossipDtoFactory {
  @Autowired
  private ModelMapper modelMapper;

  public GossipDto createFromEntity(Gossip gossip)
  {
    return this.modelMapper.map(gossip, GossipDto.class);
  }
}
