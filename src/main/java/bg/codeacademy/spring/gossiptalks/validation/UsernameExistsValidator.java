package bg.codeacademy.spring.gossiptalks.validation;

import bg.codeacademy.spring.gossiptalks.user.UserRepository;
import lombok.RequiredArgsConstructor;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RequiredArgsConstructor
public final class UsernameExistsValidator implements ConstraintValidator<UsernameExists, String> {
  private final UserRepository userRepository;
  @Override
  public void initialize(final UsernameExists username) {
  }

  @Override
  public boolean isValid(final String username, final ConstraintValidatorContext context) {
    if (username == null) {
      return true;
    }

    return !this.userRepository.existsByUsername(username);
  }
}