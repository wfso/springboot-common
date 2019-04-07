package com.yioks.springboot.common.exceptionHandler;

import com.yioks.springboot.common.shiro.exception.StatelessAuthenticationException;
import com.yioks.springboot.common.shiro.token.StatelessAuthenticationToken;
import org.apache.shiro.SecurityUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

public class DefaultStatelessAuthenticationExceptionHandler extends AbstractAuthenticationExceptionHandler {

  protected String codeName;
  protected String msgName;

  public DefaultStatelessAuthenticationExceptionHandler(String codeName, String msgName) {
    this.codeName = codeName;
    this.msgName = msgName;
  }

  @ExceptionHandler(StatelessAuthenticationException.class)
  @ResponseBody
  ResponseEntity<Object> handleStatelessAuthenticationControllerException(Throwable throwable) {
    return commonAuthenticationExceptionHandler(throwable,codeName,msgName);
  }

  @ModelAttribute
  public void statelessLogin(HttpServletRequest request) {
    StatelessAuthenticationToken token = new StatelessAuthenticationToken();
    Enumeration<String> enumeration = request.getParameterNames();
    while (enumeration.hasMoreElements()) {
      String paramKey = enumeration.nextElement();
      token.addParam(paramKey, request.getParameter(paramKey));
    }
    SecurityUtils.getSubject().login(token);
  }

}
