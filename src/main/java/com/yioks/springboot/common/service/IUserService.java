package com.yioks.springboot.common.service;

import com.yioks.springboot.common.model.*;
import com.yioks.springboot.common.shiro.token.UserAuthenticationToken;
import org.apache.shiro.SecurityUtils;

import java.util.Collection;

public interface IUserService<T extends IUser<ID>,ID> {
  T getByIdentification(ID id);

  Collection<? extends IRole> getRolesByUser(T user);

  Collection<? extends IPermission> getUserPermission(T user);

  default Collection<? extends IRole> getRolesByUserIdentification(ID id) {
    return getRolesByUser(getByIdentification(id));
  }

  default T getLoginUser() {
    return (T) SecurityUtils.getSubject().getPrincipal();
  }

  default void login(UserAuthenticationToken token) {
    SecurityUtils.getSubject().login(token);
  }
}
