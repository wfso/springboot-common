package com.yioks.springboot.common.shiro.session.utils;

public interface ISessionUtil {

  void setSession(String key, Object value);

  Object getSession(String key);

  Object removeSession(String key);

  String getSessionId();
}
