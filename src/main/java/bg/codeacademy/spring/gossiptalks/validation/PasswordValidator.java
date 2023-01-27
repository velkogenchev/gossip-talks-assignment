package bg.codeacademy.spring.gossiptalks.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class PasswordValidator implements ConstraintValidator<Password, String> {

  private final String regex = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,20}$";

  @Override
  public void initialize(final Password password) {
  }

  @Override
  public boolean isValid(final String password, final ConstraintValidatorContext context) {
    if (password == null) {
      return true;
    }
    Pattern p = Pattern.compile(this.regex);
    Matcher m = p.matcher(password);
    return m.matches();
  }
}