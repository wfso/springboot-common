package com.yioks.validation.constraints;



import com.yioks.validation.validator.PhoneValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(
  validatedBy = PhoneValidator.class
)
public @interface Phone {

  String pattern() default "^1[3456789]\\d{9}$";

  String message() default "{com.yioks.validation.constraints.Phone.message}";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
