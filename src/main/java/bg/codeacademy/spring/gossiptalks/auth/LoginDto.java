package bg.codeacademy.spring.gossiptalks.auth;

import lombok.Data;

@Data
public class LoginDto {
  private String username;
  private String password;
}
