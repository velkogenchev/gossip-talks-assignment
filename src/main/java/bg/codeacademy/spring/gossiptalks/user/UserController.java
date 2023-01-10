package bg.codeacademy.spring.gossiptalks.user;

import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class UserController {
  private final UserService userService;

  @GetMapping("/users")
  public Page<UserDto> getAllUsers(
      @RequestParam("page") @Positive @NotNull final int page,
      @RequestParam("name") @Nullable final String name,
      @RequestParam("following") @Nullable final boolean following
  ) throws NotFoundException {
    return this.userService.getAllUsers(page - 1, 10, name, following);
  }

  @GetMapping("/users/{id}")
  public UserDto getUser(@PathVariable Long id) throws NotFoundException {
    return this.userService.getUserById(id);
  }

  @GetMapping("/users/me")
  public UserDto getMe() throws NotFoundException {
    return this.userService.getCurrentUser();
  }

  @PostMapping("/users")
  public UserDto createUser(@RequestBody UserDto user) {
   return this.userService.createUser(user);
  }

  @DeleteMapping("/users/{id}")
  public void deleteUser(@PathVariable Long id)
  {
    this.userService.deleteUser(id);
  }
}
