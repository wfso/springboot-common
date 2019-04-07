package com.yioks.springboot.common.shiro.service;

import com.yioks.springboot.common.shiro.model.IPermission;
import com.yioks.springboot.common.shiro.model.IRole;
import org.apache.commons.lang3.StringUtils;

import java.util.Collection;

public interface IRoleService<T extends IRole> {
  T getByCode(String code);

  default Collection<? extends IPermission> getPermissionsByRole(IRole role) {
    return role.getPermissions();
  }

  default Collection<? extends IPermission> getPermissionsByRoleCode(String code) {
    return getPermissionsByRole(getByCode(code));
  }

  default boolean isAvailable(T t) {
    return t != null && StringUtils.isNotEmpty(t.getCode());
  }
}
