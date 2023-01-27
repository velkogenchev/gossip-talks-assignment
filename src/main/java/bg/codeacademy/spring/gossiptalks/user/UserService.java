package bg.codeacademy.spring.gossiptalks.user;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
  private final UserRepository userRepository;
  private final UserDtoFactory userDtoFactory;
  private final PasswordEncoder passwordEncoder;

  public List<UserDto> getAllUsers(final String name, final boolean followingFilter) throws NotFoundException {
    Long loggedInUserId = getCurrentUser().getId();
    List<User> users = this.userRepository.findAll();

    if (name != null) {
      users = this.userRepository.findByNameContainsIgnoreCaseOrUsernameContainsIgnoreCase(name, name);
    }

    List<UserDto> userDtos = new ArrayList<>();
    for (User user : users) {
      boolean isFollowing = userRepository.isFollowing(loggedInUserId, user.getId());
      userDtos.add(this.userDtoFactory.createFromEntity(user, isFollowing));
    }

    if (followingFilter) {
     userDtos = userDtos.stream().filter(u -> u.isFollowing()).collect(Collectors.toList());
    }

    return userDtos;
  }

  public UserDto getUserById(Long id) throws NotFoundException {
    Optional<User> user = this.userRepository.findById(id);
    if (user.isPresent()) {
      return this.userDtoFactory.createFromEntity(user.get());
    }
    throw new NotFoundException();
  }

  public UserDto getCurrentUser() throws NotFoundException {
    String email = SecurityContextHolder.getContext().getAuthentication().getName();
    Optional<User> user = this.userRepository.getByEmail(email);
    if (user.isPresent()) {
      return this.userDtoFactory.createFromEntity(user.get());
    }
    throw new NotFoundException();
  }

  public UserDto updateCurrentUser(UserMeDto userMeDto) {
    Optional<User> loggedUser = this.getLoggedUser();
    User user = loggedUser.get();
    user.setPassword(this.passwordEncoder.encode(userMeDto.getPassword()));
    this.userRepository.save(user);

    return this.userDtoFactory.createFromEntity(user);
  }

  public UserDto follow(String username) throws NotFoundException {
    Optional<User> followingUser = userRepository.getByUsername(username);
    if (followingUser.isPresent()) {
      User loggedUser = getLoggedUser().get();

      if (loggedUser.getFollowing().contains(followingUser.get())) {
        loggedUser.unfollow(followingUser.get());
      } else {
        loggedUser.follow(followingUser.get());
      }

      this.userRepository.save(loggedUser);

      return this.userDtoFactory.createFromEntity(followingUser.get());
    }

    throw new NotFoundException();
  }

  public void deleteUser(Long id) {
    this.userRepository.deleteById(id);
  }

  private Optional<User> getLoggedUser() {
    String email = SecurityContextHolder.getContext().getAuthentication().getName();
    return this.userRepository.getByEmail(email);
  }
}
