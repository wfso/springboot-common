package com.yioks.springboot.common.shiro.exception;

import org.apache.shiro.authc.AuthenticationException;

public class StatelessAuthenticationException extends AuthenticationException {
  public StatelessAuthenticationException() {
  }

  public StatelessAuthenticationException(String message) {
    super(message);
  }

  public StatelessAuthenticationException(Throwable cause) {
    super(cause);
  }

  public StatelessAuthenticationException(String message, Throwable cause) {
    super(message, cause);
  }
}
