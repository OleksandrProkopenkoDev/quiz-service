package ua.com.quizservice.util.verification.taxidentificationnumber;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/** Replace this stub by correct Javadoc. */
public class TaxIdentificationNumberValidator
    implements ConstraintValidator<TaxIdentificationNumber, String> {
  public static final String TAX_IDENTIFICATION_NUMBER_PATTERN = "\\d{10}";

  @Override
  public void initialize(TaxIdentificationNumber constraintAnnotation) {
    ConstraintValidator.super.initialize(constraintAnnotation);
  }

  @Override
  public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
    return value != null && value.matches(TAX_IDENTIFICATION_NUMBER_PATTERN);
  }
}
