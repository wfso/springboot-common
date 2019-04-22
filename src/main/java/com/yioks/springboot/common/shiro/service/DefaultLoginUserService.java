package com.yioks.springboot.common.shiro.service;

import com.yioks.springboot.common.shiro.authorization.IAuthorizationService;
import com.yioks.springboot.common.shiro.model.IUser;
import com.yioks.springboot.common.shiro.model.ShiroPrincipal;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import java.util.ArrayList;
import java.util.List;

public class DefaultLoginUserService implements ILoginUserService, BeanPostProcessor {

  List<IAuthorizationService> authorizationServices = new ArrayList<>();

  @Override
  public IUser getLoginUser() {
    ShiroPrincipal principal = (ShiroPrincipal) SecurityUtils.getSubject().getPrincipal();
    for (IAuthorizationService authorizationService : authorizationServices) {
      if (authorizationService.supports(principal)) {
        return authorizationService.getUser(principal);
      }
    }
    return null;
  }

  @Override
  public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
    if (bean instanceof IAuthorizationService) {
      authorizationServices.add((IAuthorizationService) bean);
    }
    return bean;
  }
}
