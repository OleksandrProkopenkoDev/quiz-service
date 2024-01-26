package ua.com.quizservice.util.verification.phonenumber;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PhoneNumberValidator.class)
public @interface PhoneNumber {
  String message() default "Phone number must contain exactly 10 digits";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
