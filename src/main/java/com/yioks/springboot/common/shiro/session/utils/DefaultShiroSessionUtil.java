package com.yioks.springboot.common.shiro.session.utils;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;

public class DefaultShiroSessionUtil extends AbstractSessionUtil {
  @Override
  protected Session getSessionObject() {
    return SecurityUtils.getSubject().getSession();
  }
}
