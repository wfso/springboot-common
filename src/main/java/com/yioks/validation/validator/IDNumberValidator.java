package com.yioks.validation.validator;

import com.yioks.validation.constraints.IDNumber;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;

public class IDNumberValidator implements ConstraintValidator<IDNumber, String> {

  private static final List<Integer> integers = Arrays.asList(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2);
  private static final List<Character> characters = Arrays.asList('1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2');

  @Override
  public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
    if (s == null) {
      return true;
    }
    if (s.length() == 18) {
      char[] ids = s.toUpperCase().toCharArray();
      int sum = 0;
      for (int i = 0; i < 17; i++) {
        if (ids[i] > '9' || ids[i] < '0') {
          return false;
        }
        sum += Integer.parseInt(Character.toString(ids[i])) * integers.get(i);
      }
      sum = sum % 11;
      return (characters.get(sum) == ids[17]);
    }
    return false;
  }
}
