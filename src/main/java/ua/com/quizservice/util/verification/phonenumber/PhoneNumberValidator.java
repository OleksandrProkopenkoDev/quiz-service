package ua.com.quizservice.util.verification.phonenumber;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/** Replace this stub by correct Javadoc. */
public class PhoneNumberValidator implements ConstraintValidator<PhoneNumber, String> {
  public static final String TAX_IDENTIFICATION_NUMBER_PATTERN = "\\d{10}";

  @Override
  public void initialize(PhoneNumber constraintAnnotation) {
    ConstraintValidator.super.initialize(constraintAnnotation);
  }

  @Override
  public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
    return value != null && value.matches(TAX_IDENTIFICATION_NUMBER_PATTERN);
  }
}
