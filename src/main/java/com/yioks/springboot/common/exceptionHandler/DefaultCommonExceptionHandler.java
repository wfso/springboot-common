package com.yioks.springboot.common.exceptionHandler;

import com.yioks.springboot.common.exception.CommonException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

public class DefaultCommonExceptionHandler extends AbstractCommonExceptionHandler {

  protected String codeName;
  protected String msgName;

  public DefaultCommonExceptionHandler(String codeName, String msgName) {
    this.codeName = codeName;
    this.msgName = msgName;
  }

  @ExceptionHandler(CommonException.class)
  @ResponseBody
  ResponseEntity<Object> handleCommonControllerException(CommonException throwable) {
    return commonExceptionHandler(throwable, codeName, msgName);
  }
}
