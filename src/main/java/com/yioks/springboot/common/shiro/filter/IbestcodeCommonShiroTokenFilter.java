package com.yioks.springboot.common.shiro.filter;

import com.yioks.springboot.common.shiro.session.utils.SessionUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;

import org.apache.shiro.web.servlet.AbstractFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class IbestcodeCommonShiroTokenFilter extends AbstractFilter {

  private String tokenIdentification;

  public IbestcodeCommonShiroTokenFilter(String tokenIdentification) {
    this.tokenIdentification = tokenIdentification;
  }

  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
    log.debug("exec IbestcodeCommonShiroTokenFilter");
    HttpServletRequest request = (HttpServletRequest) servletRequest;
    HttpServletResponse response = (HttpServletResponse) servletResponse;
    String token = request.getHeader(tokenIdentification);
    String sessionId = SessionUtil.getSessionId();
    if (StringUtils.isEmpty(token) || StringUtils.isBlank(token) || !token.equalsIgnoreCase(sessionId)) {
      log.debug("IbestcodeCommonShiroTokenFilter - set response header "
        + tokenIdentification + ":" + sessionId);
      response.setHeader(tokenIdentification, sessionId);
    }
    filterChain.doFilter(servletRequest, servletResponse);
  }
}
