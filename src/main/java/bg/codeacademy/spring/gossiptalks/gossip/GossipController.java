package bg.codeacademy.spring.gossiptalks.gossip;

import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class GossipController {
  private final GossipService gossipService;

  @GetMapping("/gossips")
  public Page<GossipDto> getAllGossips(
      @RequestParam("pageNo") @Positive @NotNull final int page,
      @RequestParam("pageSize") @Positive @NotNull final int pageSize
  ) throws NotFoundException {
    return this.gossipService.getAllGossips(page, pageSize);
  }

  @GetMapping("/users/{username}/gossips")
  public Page<GossipDto> getAllGossipsByUsername(
      @RequestParam("pageNo") @Positive @NotNull final int page,
      @RequestParam("pageSize") @Positive @NotNull final int pageSize,
      @PathVariable @NotBlank String username
  ) {
    return this.gossipService.getAllGossipsByUsername(page, pageSize, username);
  }

  @PostMapping("/gossips")
  public GossipDto createGossip(@ModelAttribute GossipDto gossipDto)
      throws NotFoundException {
    return this.gossipService.createGossip(gossipDto.getText());
  }
}
