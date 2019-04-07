package com.yioks.springboot.common.shiro.service;

import com.yioks.springboot.common.shiro.model.IPermission;
import org.apache.commons.lang3.StringUtils;


public interface IPermissionService<T extends IPermission> {
  T getByCode(String code);

  default boolean isAvailable(T t) {
    return t != null && StringUtils.isNotEmpty(t.getCode());
  }
}
