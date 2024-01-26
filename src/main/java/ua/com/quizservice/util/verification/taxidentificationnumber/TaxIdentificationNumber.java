package ua.com.quizservice.util.verification.taxidentificationnumber;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = TaxIdentificationNumberValidator.class)
public @interface TaxIdentificationNumber {
  String message() default "Tax identification number must contain exactly 10 digits";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
