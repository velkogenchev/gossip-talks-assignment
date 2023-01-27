package bg.codeacademy.spring.gossiptalks.user;

import bg.codeacademy.spring.gossiptalks.validation.CheckOldPassword;
import bg.codeacademy.spring.gossiptalks.validation.ConfirmPassword;
import bg.codeacademy.spring.gossiptalks.validation.ConfirmPasswordInterface;
import bg.codeacademy.spring.gossiptalks.validation.Password;
import lombok.Data;
import javax.validation.constraints.NotBlank;

@Data
@ConfirmPassword
public class UserMeDto implements ConfirmPasswordInterface {
  @NotBlank
  @Password
  private String password;
  @NotBlank
  private String passwordConfirmation;
  @NotBlank
  @CheckOldPassword
  private String oldPassword;
}
