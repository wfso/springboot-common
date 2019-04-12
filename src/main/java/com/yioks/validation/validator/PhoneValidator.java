package com.yioks.validation.validator;

import com.yioks.validation.constraints.Phone;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PhoneValidator implements ConstraintValidator<Phone, String> {
  private String pattern;

  @Override
  public void initialize(Phone constraintAnnotation) {
    pattern = constraintAnnotation.pattern();
  }

  @Override
  public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
    if (s == null) {
      return true;
    }
    return s.matches(pattern);
  }
}
