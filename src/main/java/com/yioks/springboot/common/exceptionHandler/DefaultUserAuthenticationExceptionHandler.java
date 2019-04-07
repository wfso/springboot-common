package com.yioks.springboot.common.exceptionHandler;

import com.yioks.springboot.common.shiro.exception.UserAuthenticationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

public class DefaultUserAuthenticationExceptionHandler extends AbstractAuthenticationExceptionHandler {

  protected String codeName;
  protected String msgName;

  public DefaultUserAuthenticationExceptionHandler(String codeName, String msgName) {
    this.codeName = codeName;
    this.msgName = msgName;
  }

  @ExceptionHandler(UserAuthenticationException.class)
  @ResponseBody
  ResponseEntity<Object> handleUserAuthenticationControllerException(Throwable throwable) {
    return commonAuthenticationExceptionHandler(throwable, codeName, msgName);
  }
}
