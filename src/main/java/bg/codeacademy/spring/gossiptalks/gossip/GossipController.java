package bg.codeacademy.spring.gossiptalks.gossip;

import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
      @RequestParam("page") @Positive @NotNull final int page,
      @RequestParam("pageSize") @Positive @NotNull final int pageSize
  ) {
    return this.gossipService.getAllGossips(page - 1, pageSize);
  }

  @GetMapping("/users/{username}/gossips")
  public Page<GossipDto> getAllGossipsByUsername(
      @RequestParam("page") @Positive @NotNull final int page,
      @RequestParam("pageSize") @Positive @NotNull final int pageSize,
      @PathVariable @NotBlank String username
  ) {
    return this.gossipService.getAllGossipsByUsername(page - 1, pageSize, username);
  }

  @PostMapping("/gossips")
  public GossipDto getAllGossipsByUsername(@RequestBody @NotBlank String text)
      throws NotFoundException {
    return this.gossipService.createGossip(text);
  }
}
