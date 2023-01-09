package bg.codeacademy.spring.gossiptalks.gossip;

import bg.codeacademy.spring.gossiptalks.user.User;
import bg.codeacademy.spring.gossiptalks.user.UserDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GossipFactory {
  @Autowired
  private ModelMapper modelMapper;

  public GossipDto createFromEntity(Gossip gossip)
  {
    return this.modelMapper.map(gossip, GossipDto.class);
  }

  public Gossip createFromDto(GossipDto gossipDto)
  {
    return this.modelMapper.map(gossipDto, Gossip.class);
  }
}
