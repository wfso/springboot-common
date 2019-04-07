package com.yioks.springboot.common.shiro.persistence;

import java.lang.reflect.Method;

public interface IShiroPermissionPersistenceService {
  void persistencePermission(Class controllerClass, Method method);
}
