package com.yioks.springboot.common.exception;

public class CodecException extends RuntimeException{

  public CodecException() {
  }

  public CodecException(String message) {
    super(message);
  }

  public CodecException(Throwable cause) {
    super(cause);
  }

  public CodecException(String message, Throwable cause) {
    super(message, cause);
  }
}
