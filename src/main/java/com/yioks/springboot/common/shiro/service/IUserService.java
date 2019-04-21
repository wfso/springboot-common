package com.yioks.springboot.common.shiro.service;

import com.yioks.springboot.common.shiro.model.*;

import java.util.Collection;

public interface IUserService<T extends IUser> {
  T getByIdentification(Long id);

  Collection<? extends IRole> getRolesByUser(T user);

  Collection<? extends IPermission> getUserPermission(T user);

  default Collection<? extends IRole> getRolesByUserIdentification(Long id) {
    return getRolesByUser(getByIdentification(id));
  }
}
