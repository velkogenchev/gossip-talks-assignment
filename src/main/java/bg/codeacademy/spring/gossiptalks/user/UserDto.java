package bg.codeacademy.spring.gossiptalks.user;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.Id;

@Setter
@Getter
public class UserDto {
  @Id
  private Long id;
  private String email;
  private String username;
  private String name;
  private boolean following;
}
