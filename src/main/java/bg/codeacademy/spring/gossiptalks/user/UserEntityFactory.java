package bg.codeacademy.spring.gossiptalks.user;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserEntityFactory {
  @Autowired
  private ModelMapper modelMapper;

  public User createFromDto(UserDto userDto)
  {
    return this.modelMapper.map(userDto, User.class);
  }
}
