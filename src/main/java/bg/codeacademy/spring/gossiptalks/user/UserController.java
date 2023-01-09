package bg.codeacademy.spring.gossiptalks.user;

import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class UserController {
  private final UserRepository userRepository;
  private final UserFactory userFactory;

  @GetMapping("/users")
  public List<UserDto> getAllUsers()
  {
    List<UserDto> userDtos = new ArrayList<>();
    List<User> allUsers = this.userRepository.findAll();
    for(User user : allUsers) {
      userDtos.add(this.userFactory.createFromEntity(user));
    }

    return userDtos;
  }

  @GetMapping("/users/{id}")
  public UserDto getUser(@PathVariable Long id) throws NotFoundException {
    Optional<User> user = this.userRepository.findById(id);
    if (user.isPresent()) {
      return this.userFactory.createFromEntity(user.get());
    }
    throw new NotFoundException();
  }

  @GetMapping("/users/me")
  public UserDto getMe() throws NotFoundException {
    String email = SecurityContextHolder.getContext().getAuthentication().getName();
    Optional<User> user = this.userRepository.findByEmail(email);
    if (user.isPresent()) {
      return this.userFactory.createFromEntity(user.get());
    }
    throw new NotFoundException();
  }

  @PostMapping("/users")
  public UserDto createUser(@RequestBody UserDto user)
  {
    User createdUser = this.userRepository.save(this.userFactory.createFromDto(user));
    return this.userFactory.createFromEntity(createdUser);
  }

  @DeleteMapping("/users/{id}")
  public void deleteUser(@PathVariable Long id)
  {
    this.userRepository.deleteById(id);
  }
}
