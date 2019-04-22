package com.yioks.springboot.common.service;

import com.yioks.springboot.common.model.IPermission;
import com.yioks.springboot.common.model.IRole;

import java.util.Collection;
import java.util.List;

public interface IRoleService<T extends IRole> {
  T getById(long id);

  List<T> getById(Iterable<Long> ids);

  T getByCode(String code);

  Collection<? extends IPermission> getPermissionsByRole(T role);

  default Collection<? extends IPermission> getPermissionsByRoleCode(String code) {
    return getPermissionsByRole(getByCode(code));
  }
}
