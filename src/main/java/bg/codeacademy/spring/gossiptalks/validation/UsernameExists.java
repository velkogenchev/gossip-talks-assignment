package bg.codeacademy.spring.gossiptalks.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = { UsernameExistsValidator.class })
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface UsernameExists {
  String message() default "Username already exists!";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
