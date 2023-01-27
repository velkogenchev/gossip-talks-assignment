package bg.codeacademy.spring.gossiptalks.auth;

import bg.codeacademy.spring.gossiptalks.validation.ConfirmPassword;
import bg.codeacademy.spring.gossiptalks.validation.ConfirmPasswordInterface;
import bg.codeacademy.spring.gossiptalks.validation.EmailExists;
import bg.codeacademy.spring.gossiptalks.validation.Password;
import bg.codeacademy.spring.gossiptalks.validation.UsernameExists;
import lombok.Data;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@ConfirmPassword
public class RegistrationDto implements ConfirmPasswordInterface {
  @NotBlank
  @Size(min = 2, max = 255)
  private String name;
  @NotBlank
  @Size(min = 2, max = 255)
  @UsernameExists
  private String username;
  @NotBlank
  @Email
  @Size(min = 2, max = 255)
  @EmailExists
  private String email;
  @NotBlank
  @Password
  private String password;
  @NotBlank
  private String passwordConfirmation;
}
