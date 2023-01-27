package bg.codeacademy.spring.gossiptalks.gossip;

import bg.codeacademy.spring.gossiptalks.user.User;
import bg.codeacademy.spring.gossiptalks.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GossipService {
  private final GossipRepository gossipRepository;
  private final GossipDtoFactory gossipDtoFactory;
  private final UserRepository userRepository;

  public Page<GossipDto> getAllGossips(final int page, final int pageSize)
      throws NotFoundException {
    Pageable pageable = PageRequest.of(page, pageSize);
    Page<Gossip> gossips = this.gossipRepository.findAll(pageable);
    List<GossipDto> gossipDtos = new ArrayList<>();
    for (Gossip gossip : gossips) {
      if (userRepository.isFollowing(getCurrentUser().getId(), gossip.getUser().getId())) {
        gossipDtos.add(this.gossipDtoFactory.createFromEntity(gossip));
      }
    }

    return new PageImpl<>(gossipDtos);
  }

  public Page<GossipDto> getAllGossipsByUsername(final int page, final int pageSize, final String username) {
    Pageable pageable = PageRequest.of(page, pageSize);
    Page<Gossip> gossips = this.gossipRepository.getAllByUsername(username, pageable);
    List<GossipDto> gossipDtos = new ArrayList<>();
    for (Gossip gossip : gossips) {
      gossipDtos.add(this.gossipDtoFactory.createFromEntity(gossip));
    }

    return new PageImpl<>(gossipDtos);
  }

  public GossipDto createGossip(String text) throws NotFoundException {
    Gossip gossip = new Gossip();
    gossip.setUser(getCurrentUser());
    gossip.setText(text);
    Gossip createdGossip = this.gossipRepository.save(gossip);

    return this.gossipDtoFactory.createFromEntity(createdGossip);
  }

  private User getCurrentUser() throws NotFoundException {
    String email = SecurityContextHolder.getContext().getAuthentication().getName();
    Optional<User> user = this.userRepository.getByEmail(email);
    if (user.isPresent()) {
      return user.get();
    }
    throw new NotFoundException();
  }
}
