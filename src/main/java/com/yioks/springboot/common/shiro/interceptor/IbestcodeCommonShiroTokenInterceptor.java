package com.yioks.springboot.common.shiro.interceptor;

import com.yioks.springboot.common.shiro.session.utils.SessionUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class IbestcodeCommonShiroTokenInterceptor extends HandlerInterceptorAdapter {

  private String tokenIdentification;

  public IbestcodeCommonShiroTokenInterceptor(String tokenIdentification) {
    this.tokenIdentification = tokenIdentification;
  }

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    String token = request.getHeader(tokenIdentification);
    return StringUtils.isEmpty(token) || StringUtils.isEmpty(token);
  }

  @Override
  public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    String token = request.getHeader(tokenIdentification);
    String sessionId = SessionUtil.getSessionId();
    if (StringUtils.isEmpty(token) || StringUtils.isEmpty(token) || !token.equalsIgnoreCase(sessionId)) {
      response.setHeader(tokenIdentification, sessionId);
    }
  }
}
