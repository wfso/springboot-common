package com.yioks.springboot.common.shiro.realm;

import com.yioks.springboot.common.shiro.model.ShiroPrincipal;
import com.yioks.springboot.common.shiro.authentication.IAuthenticationService;
import com.yioks.springboot.common.shiro.authorization.IAuthorizationService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import java.util.ArrayList;
import java.util.List;

public class DefaultAuthorizationRealm extends AuthorizingRealm implements BeanPostProcessor {

  private Logger logger = LoggerFactory.getLogger(this.getClass());

  private List<IAuthenticationService> authenticationServices;
  private List<IAuthorizationService> authorizationServices;

  public DefaultAuthorizationRealm() {
    this.authenticationServices = new ArrayList<>();
    this.authorizationServices = new ArrayList<>();
  }

  @Override
  public boolean supports(AuthenticationToken token) {
    for (IAuthenticationService authorizationService : authenticationServices) {
      if (authorizationService.supports(token)) {
        return true;
      }
    }
    return false;
  }

  @Override
  protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) {
    for (IAuthenticationService authorizationService : authenticationServices) {
      if (authorizationService.supports(authenticationToken)) {
        return authorizationService.getAuthenticationInfo(authenticationToken, getName());
      }
    }
    logger.warn("No supported IAuthenticationService found");
    return null;
  }

  @Override
  protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
    Object principal = principals.getPrimaryPrincipal();
    if (principal instanceof ShiroPrincipal) {
      ShiroPrincipal shiroPrincipal = (ShiroPrincipal) principal;
      for (IAuthorizationService authorizationService : authorizationServices) {
        if (authorizationService.supports(shiroPrincipal)) {
          return authorizationService.getAuthorizationInfo(shiroPrincipal);
        }
      }
    }
    logger.warn("No supported IAuthorizationService found");
    return null;
  }

  @Override
  public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
    if (bean instanceof IAuthenticationService) {
      authenticationServices.add((IAuthenticationService) bean);
    }
    if (bean instanceof IAuthorizationService) {
      authorizationServices.add((IAuthorizationService) bean);
    }
    return bean;
  }
}
