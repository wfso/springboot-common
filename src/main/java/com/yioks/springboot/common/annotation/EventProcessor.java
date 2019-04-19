package com.yioks.springboot.common.annotation;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface EventProcessor {
  @AliasFor(
    annotation = Component.class
  )
  String value() default "";

  EventProcessorType type() default EventProcessorType.BOTH;
}
