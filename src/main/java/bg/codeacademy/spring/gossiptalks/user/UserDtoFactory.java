package bg.codeacademy.spring.gossiptalks.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class UserDtoFactory {

  public UserDto createFromEntity(User user)
  {
    UserDto dto = new UserDto();
    dto.setId(user.getId());
    dto.setName(user.getName());
    dto.setEmail(user.getEmail());
    dto.setUsername(user.getUsername());
    dto.setFollowing(false);

    return dto;
  }

  public UserDto createFromEntity(User user, boolean following)
  {
    UserDto dto = new UserDto();
    dto.setId(user.getId());
    dto.setName(user.getName());
    dto.setEmail(user.getEmail());
    dto.setUsername(user.getUsername());
    dto.setFollowing(following);

    return dto;
  }
}
