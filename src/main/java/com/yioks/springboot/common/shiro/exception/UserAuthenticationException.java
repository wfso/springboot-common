package com.yioks.springboot.common.shiro.exception;

import org.apache.shiro.authc.AuthenticationException;

public class UserAuthenticationException extends AuthenticationException {
  public UserAuthenticationException() {
  }

  public UserAuthenticationException(String message) {
    super(message);
  }

  public UserAuthenticationException(Throwable cause) {
    super(cause);
  }

  public UserAuthenticationException(String message, Throwable cause) {
    super(message, cause);
  }
}
