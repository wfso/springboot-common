package com.yioks.springboot.common.exceptionHandler;

import com.yioks.springboot.common.exception.CommonException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractCommonExceptionHandler {

  @Autowired(required = false)
  protected MessageSource messageSource;

  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  protected ResponseEntity<Object> commonExceptionHandler(CommonException exception, String codeName, String msgName) {
    Map<String, Object> result = new HashMap<>();
    logger.warn(exception.getClass().getName());
    logger.warn(exception.getMessage());
    String code = exception.getMessage();
    String msgKey = exception.getClass().getSimpleName() + "." + code;
    String msg;
    if (messageSource != null) {
      msg = messageSource.getMessage(msgKey, exception.getParams(), code, LocaleContextHolder.getLocale());
    } else {
      msg = code;
    }
    result.put(codeName, code);
    result.put(msgName, msg);
    return new ResponseEntity<>(result, HttpStatus.OK);
  }
}
