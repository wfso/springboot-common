package com.yioks.springboot.common.shiro.exception;

import org.apache.shiro.authc.AuthenticationException;

public class AccessKeyAuthenticationException extends AuthenticationException {
  public AccessKeyAuthenticationException() {
  }

  public AccessKeyAuthenticationException(String message) {
    super(message);
  }

  public AccessKeyAuthenticationException(Throwable cause) {
    super(cause);
  }

  public AccessKeyAuthenticationException(String message, Throwable cause) {
    super(message, cause);
  }
}
