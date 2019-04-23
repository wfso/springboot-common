package com.yioks.springboot.common.exceptionHandler;

import com.yioks.springboot.common.shiro.exception.AccessKeyAuthenticationException;
import com.yioks.springboot.common.shiro.token.AccessKeyAuthenticationToken;
import org.apache.shiro.SecurityUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

public class DefaultAccessKeyAuthenticationExceptionHandler extends AbstractAuthenticationExceptionHandler {

  protected String codeName;
  protected String msgName;

  public DefaultAccessKeyAuthenticationExceptionHandler(String codeName, String msgName) {
    this.codeName = codeName;
    this.msgName = msgName;
  }

  @ExceptionHandler(AccessKeyAuthenticationException.class)
  @ResponseBody
  ResponseEntity<Object> handleStatelessAuthenticationControllerException(Throwable throwable) {
    return commonAuthenticationExceptionHandler(throwable,codeName,msgName);
  }

  @ModelAttribute
  public void accesskeyLogin(HttpServletRequest request) {
    AccessKeyAuthenticationToken token = new AccessKeyAuthenticationToken();
    Enumeration<String> enumeration = request.getParameterNames();
    while (enumeration.hasMoreElements()) {
      String paramKey = enumeration.nextElement();
      token.addParam(paramKey, request.getParameter(paramKey));
    }
    SecurityUtils.getSubject().login(token);
  }
}
