package bg.codeacademy.spring.gossiptalks.validation;

import bg.codeacademy.spring.gossiptalks.user.User;
import bg.codeacademy.spring.gossiptalks.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

@RequiredArgsConstructor
public final class CheckOldPasswordValidator implements ConstraintValidator<CheckOldPassword, String> {
  private final UserRepository userRepository;
  @Autowired
  private PasswordEncoder passwordEncoder;
  @Override
  public void initialize(final CheckOldPassword oldPassword) {
  }

  @Override
  public boolean isValid(final String oldPassword, final ConstraintValidatorContext context) {
    if (oldPassword == null) {
      return true;
    }

    String email = SecurityContextHolder.getContext().getAuthentication().getName();
    Optional<User> user = this.userRepository.getByEmail(email);

    return this.passwordEncoder.matches(oldPassword, user.get().getPassword());
  }
}