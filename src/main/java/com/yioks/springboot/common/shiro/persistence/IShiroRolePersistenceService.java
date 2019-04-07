package com.yioks.springboot.common.shiro.persistence;

import java.lang.reflect.Method;

public interface IShiroRolePersistenceService {
  void persistenceRole(Class controllerClass, Method method);
}
