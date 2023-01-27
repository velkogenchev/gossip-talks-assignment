package bg.codeacademy.spring.gossiptalks.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public final class ConfirmPasswordValidator implements ConstraintValidator<ConfirmPassword, ConfirmPasswordInterface> {
  @Override
  public void initialize(final ConfirmPassword confirmPassword) {
  }

  @Override
  public boolean isValid(final ConfirmPasswordInterface passwordInterface, final ConstraintValidatorContext context) {
    if (passwordInterface.getPassword() == null || passwordInterface.getPasswordConfirmation() == null ) {
      return true;
    }

    return passwordInterface.getPassword().equals(passwordInterface.getPasswordConfirmation());
  }
}
