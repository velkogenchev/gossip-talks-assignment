package bg.codeacademy.spring.gossiptalks.validation;

import bg.codeacademy.spring.gossiptalks.user.UserRepository;
import lombok.RequiredArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@RequiredArgsConstructor
public final class EmailExistsValidator implements ConstraintValidator<EmailExists, String> {
  private final UserRepository userRepository;
  @Override
  public void initialize(final EmailExists email) {
  }

  @Override
  public boolean isValid(final String email, final ConstraintValidatorContext context) {
    if (email == null) {
      return true;
    }

    return !this.userRepository.existsByEmail(email);
  }
}