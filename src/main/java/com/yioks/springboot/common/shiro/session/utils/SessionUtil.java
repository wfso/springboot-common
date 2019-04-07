package com.yioks.springboot.common.shiro.session.utils;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;

public class SessionUtil {

  public static void setSession(String key, Object value){
    getSession().setAttribute(key,value);
  }

  public static Object getSession(String key){
    return getSession().getAttribute(key);
  }

  public static Object removeSession(String key){
    return getSession().removeAttribute(key);
  }

  public static String getSessionId(){
    return getSession().getId().toString();
  }

  private static Session getSession(){
    return SecurityUtils.getSubject().getSession();
  }
}
