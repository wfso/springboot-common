package com.yioks.springboot.common.service;

import com.yioks.springboot.common.model.*;
import org.apache.shiro.SecurityUtils;

import java.util.Collection;

public interface IUserService<T extends IUser> {
  T getByIdentification(Long id);

  Collection<? extends IRole> getRolesByUser(T user);

  Collection<? extends IPermission> getUserPermission(T user);

  default Collection<? extends IRole> getRolesByUserIdentification(Long id) {
    return getRolesByUser(getByIdentification(id));
  }

  default T getLoginUser() {
    return (T) SecurityUtils.getSubject().getPrincipal();
  }
}
