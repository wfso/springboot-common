package com.yioks.springboot.common.exceptionHandler;

import org.apache.shiro.authz.AuthorizationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

public class DefaultAuthorizationExceptionHandler extends AbstractAuthorizationExceptionHandler {

  protected String codeName;
  protected String msgName;

  public DefaultAuthorizationExceptionHandler(String codeName, String msgName) {
    this.codeName = codeName;
    this.msgName = msgName;
  }

  @ExceptionHandler(AuthorizationException.class)
  @ResponseBody
  ResponseEntity<Object> handleStatelessAuthenticationControllerException(Throwable throwable) {
    return commonAuthorizationExceptionHandler(throwable,codeName,msgName);
  }
}
