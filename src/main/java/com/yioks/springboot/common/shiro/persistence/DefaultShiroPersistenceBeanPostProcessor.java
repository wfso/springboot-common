package com.yioks.springboot.common.shiro.persistence;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Method;

public class DefaultShiroPersistenceBeanPostProcessor implements BeanPostProcessor {

  Logger logger = LoggerFactory.getLogger(this.getClass());

  @Autowired(required = false)
  IShiroPermissionPersistenceService permissionPersistenceService;

  @Autowired(required = false)
  IShiroRolePersistenceService rolePersistenceService;

  public Object postProcessAfterInitialization(Object bean, String beanName) {
    if (permissionPersistenceService != null || rolePersistenceService != null) {
      Class beanClass = bean.getClass();
      if (beanClass.getName().contains("$$EnhancerBySpringCGLIB$$")) {
        beanClass = beanClass.getSuperclass();
      }
      String beanClassName = beanClass.getName();
      if (beanClass.getDeclaredAnnotation(Controller.class) != null
        || beanClass.getDeclaredAnnotation(RestController.class) != null) {
        logger.info("Shiro Persistence process Controller：" + beanClassName);
        for (Method method : beanClass.getDeclaredMethods()) {
          if (method.getDeclaredAnnotation(RequiresPermissions.class) != null) {
            if (permissionPersistenceService != null) {
              logger.info("Shiro Persistence Permission process Method：" + beanClassName + "." + method.getName());
              permissionPersistenceService.persistencePermission(beanClass, method);
            }
          } else if (method.getDeclaredAnnotation(RequiresRoles.class) != null) {
            if (rolePersistenceService != null) {
              logger.info("Shiro Persistence Role process Method：" + beanClassName + "." + method.getName());
              rolePersistenceService.persistenceRole(beanClass, method);
            }
          }
        }
      }
    } else {
      logger.warn("Please implement [IShiroPermissionPersistenceService] or [IShiroRolePersistenceService]");
    }
    return bean;
  }
}
