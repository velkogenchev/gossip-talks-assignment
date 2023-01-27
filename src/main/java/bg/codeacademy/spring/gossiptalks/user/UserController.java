package bg.codeacademy.spring.gossiptalks.user;

import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class UserController {
  private final UserService userService;

  @GetMapping("/users")
  public List<UserDto> getAllUsers(
      @RequestParam("name") @Nullable final String name,
      @RequestParam("f") @Nullable final boolean following
  ) throws NotFoundException {
    return this.userService.getAllUsers(name, following);
  }

  @GetMapping("/users/{id}")
  public UserDto getUser(@PathVariable Long id) throws NotFoundException {
    return this.userService.getUserById(id);
  }

  @GetMapping("/users/me")
  public UserDto getMe() throws NotFoundException {
    return this.userService.getCurrentUser();
  }

  @PostMapping("/users/me")
  public UserDto putMe(@ModelAttribute @Valid UserMeDto userMeDto) {
    return this.userService.updateCurrentUser(userMeDto);
  }

  @PostMapping("/users/{username}/follow")
  public UserDto follow(@PathVariable @NotBlank String username) throws NotFoundException {
    return this.userService.follow(username);
  }

  @DeleteMapping("/users/{id}")
  public void deleteUser(@PathVariable Long id)
  {
    this.userService.deleteUser(id);
  }
}
