package com.yioks.springboot.common.exceptionHandler;

import com.yioks.springboot.common.shiro.model.IPermission;
import com.yioks.springboot.common.shiro.model.IRole;
import com.yioks.springboot.common.shiro.service.IPermissionService;
import com.yioks.springboot.common.shiro.service.IRoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractAuthorizationExceptionHandler {

  @Autowired(required = false)
  protected MessageSource messageSource;

  @Autowired(required = false)
  protected IPermissionService permissionService;

  @Autowired(required = false)
  protected IRoleService roleService;

  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  protected ResponseEntity<Object> commonAuthorizationExceptionHandler(Throwable throwable, String codeName, String msgName) {
    Map<String, Object> result = new HashMap<>();
    logger.warn(throwable.getClass().getName());
    logger.warn(throwable.getMessage(), throwable);
    String code = throwable.getMessage();
    String msg = null;

    // 无登录
    if (code.startsWith("This subject is anonymous")
      || code.startsWith("The current Subject is not authenticated")) {
      code = "not-logged-in";
      String msgKey = throwable.getClass().getSimpleName() + "." + code;
      if (messageSource != null) {
        msg = messageSource.getMessage(msgKey, null, code, LocaleContextHolder.getLocale());
      }
    }

    // 无权限
    else if (code.startsWith("Subject does not have permission")) {
      code = code.substring(34, code.length() - 1);
      if (messageSource != null) {
        String msgParam = "";
        if (permissionService != null) {
          IPermission permission = permissionService.getByCode(code);
          msgParam = (permission == null ? code : permission.getName());
        }
        String msgKey = throwable.getClass().getSimpleName() + ".subject-does-not-have-permission";
        code = "subject-does-not-have-permission[" + code + "]";
        msg = messageSource.getMessage(msgKey, new String[]{msgParam}, code, LocaleContextHolder.getLocale());
      }
    }

    // 无角色
    else if (code.startsWith("Subject does not have role")) {
      code = code.substring(28, code.length() - 1);
      if (messageSource != null) {
        String msgParam = "";
        if (roleService != null) {
          IRole role = roleService.getByCode(code);
          msgParam = (role == null ? code : role.getName());
        }
        String msgKey = throwable.getClass().getSimpleName() + ".subject-does-not-have-role";
        code = "subject-does-not-have-role[" + code + "]";
        msg = messageSource.getMessage(msgKey, new String[]{msgParam}, code, LocaleContextHolder.getLocale());
      }
    }

    // guest-only
    else if (code.startsWith("Attempting to perform a guest-only operation")) {
      code = "guest-only";
      String msgKey = throwable.getClass().getSimpleName() + "." + code;
      if (messageSource != null) {
        msg = messageSource.getMessage(msgKey, null, code, LocaleContextHolder.getLocale());
      }
    }

    // 自定义访问权限异常 SystemPermissionException
    else if (code.startsWith("SPE-")) {
      String msgKey = throwable.getClass().getSimpleName() + "." + code;
      if (messageSource != null) {
        msg = messageSource.getMessage(msgKey, null, code, LocaleContextHolder.getLocale());
      }
    }

    // 当 msg 没有被赋值时
    if (msg == null) {
      msg = code;
    }

    result.put(codeName, code);
    result.put(msgName, msg);
    return new ResponseEntity(result, HttpStatus.OK);
  }
}
