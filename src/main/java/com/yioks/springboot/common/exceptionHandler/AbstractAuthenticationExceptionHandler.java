package com.yioks.springboot.common.exceptionHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractAuthenticationExceptionHandler {

  @Autowired(required = false)
  protected MessageSource messageSource;

  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  protected ResponseEntity<Object> commonAuthenticationExceptionHandler(Throwable throwable, String codeName, String msgName) {
    Map<String, Object> result = new HashMap<>();
    logger.warn(throwable.getClass().getName());
    logger.warn(throwable.getMessage());
    String code = throwable.getMessage();
    String msgKey = throwable.getClass().getSimpleName() + "." + code;
    String msg;
    if (messageSource != null) {
      msg = messageSource.getMessage(msgKey, null, code, LocaleContextHolder.getLocale());
    } else {
      msg = code;
    }
    result.put(codeName, code);
    result.put(msgName, msg);
    return new ResponseEntity<>(result, HttpStatus.OK);
  }
}
