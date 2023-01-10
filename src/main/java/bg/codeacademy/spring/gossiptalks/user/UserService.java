package bg.codeacademy.spring.gossiptalks.user;

import lombok.NoArgsConstructor;
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
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
  private final UserRepository userRepository;
  private final UserDtoFactory userDtoFactory;
  private final UserEntityFactory userEntityFactory;

  public Page<UserDto> getAllUsers(final int page, final int pageSize, final String name, final boolean followingFilter) throws NotFoundException {
    Long loggedInUserId = getCurrentUser().getId();
    Pageable pageable = PageRequest.of(page, pageSize);
    Page<User> users = this.userRepository.findAll(pageable);

    if (name != null) {
      users = this.userRepository.findByNameContainsIgnoreCaseOrUsernameContainsIgnoreCase(name, name, pageable);
    }

    List<UserDto> userDtos = new ArrayList<>();
    for (User user : users) {
      boolean isFollowing = userRepository.isFollowing(loggedInUserId, user.getId());
      userDtos.add(this.userDtoFactory.createFromEntity(user, isFollowing));
    }

    if (followingFilter) {
     userDtos = userDtos.stream().filter(u -> u.isFollowing()).collect(Collectors.toList());
    }

    return new PageImpl<>(userDtos, pageable, userDtos.size());
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

  public UserDto createUser(UserDto userDto) {
    User createdUser = this.userRepository.save(this.userEntityFactory.createFromDto(userDto));
    return this.userDtoFactory.createFromEntity(createdUser);
  }

  public void deleteUser(Long id) {
    this.userRepository.deleteById(id);
  }
}
