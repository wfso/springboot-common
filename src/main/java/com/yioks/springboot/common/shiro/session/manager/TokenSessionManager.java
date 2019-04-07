package com.yioks.springboot.common.shiro.session.manager;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.web.servlet.Cookie;
import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.Serializable;

public class TokenSessionManager extends DefaultWebSessionManager {

  public String getTokenIdentification() {
    return tokenIdentification;
  }

  public void setTokenIdentification(String tokenIdentification) {
    this.tokenIdentification = tokenIdentification;
    Cookie cookie = new SimpleCookie(this.tokenIdentification);
    cookie.setHttpOnly(true);
    this.setSessionIdCookie(cookie);
  }

  private String tokenIdentification = "IBESTCODE-COMMON-SHIRO-TOKEN";

  public TokenSessionManager(String tokenIdentification) {
    super();
    this.tokenIdentification = tokenIdentification;
    Cookie cookie = new SimpleCookie(this.tokenIdentification);
    cookie.setHttpOnly(true);
    this.setSessionIdCookie(cookie);
  }

  public TokenSessionManager() {
    super();
  }


  @Override
  protected Serializable getSessionId(ServletRequest request, ServletResponse response) {
    String id = WebUtils.toHttp(request).getHeader(tokenIdentification);

    if (!StringUtils.isEmpty(id)) {
      request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_SOURCE, "Stateless request");
      request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID, id);
      request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_IS_VALID, Boolean.TRUE);
      return id;
    } else {
      //否则按默认规则从cookie取sessionId
      return super.getSessionId(request, response);
    }
  }

}