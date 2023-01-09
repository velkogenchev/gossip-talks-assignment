package bg.codeacademy.spring.gossiptalks.auth;

import lombok.Data;

@Data
public class RegistrationDto {
  private String name;
  private String username;
  private String email;
  private String password;
  private String confirmPassword;
}
