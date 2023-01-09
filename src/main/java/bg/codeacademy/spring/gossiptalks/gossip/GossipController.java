package bg.codeacademy.spring.gossiptalks.gossip;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class GossipController {
  private final GossipRepository gossipRepository;
  private final GossipFactory gossipFactory;

  @GetMapping("/gossips")
  public List<GossipDto> getAllGossips()
  {
    List<GossipDto> gossipDtos = new ArrayList<>();
    List<Gossip> allGossips = this.gossipRepository.findAll();
    for (Gossip gossip : allGossips) {
      gossipDtos.add(this.gossipFactory.createFromEntity(gossip));
    }

    return gossipDtos;
  }

  @GetMapping("/users/{username}/gossips")
  public List<GossipDto> getAllGossipsByUsername(@PathVariable String username)
  {
    List<GossipDto> gossipDtos = new ArrayList<>();
    List<Gossip> allGossipsByUsername = this.gossipRepository.getAllByUsername(username);
    for (Gossip gossip : allGossipsByUsername) {
      gossipDtos.add(this.gossipFactory.createFromEntity(gossip));
    }

    return gossipDtos;
  }

  @PostMapping("/gossips")
  public GossipDto getAllGossipsByUsername(@RequestBody GossipDto gossip)
  {
    Gossip createdGossip = this.gossipRepository.save(this.gossipFactory.createFromDto(gossip));
    return this.gossipFactory.createFromEntity(createdGossip);
  }
}
