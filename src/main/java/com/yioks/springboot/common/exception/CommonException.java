package com.yioks.springboot.common.exception;

public class CommonException extends RuntimeException {

  private String[] params;

  public CommonException(String message, String[] params) {
    super(message);
    this.params = params;
  }

  public CommonException(String message, String[] params, Throwable cause) {
    super(message, cause);
    this.params = params;
  }

  public String[] getParams() {
    return params;
  }
}
