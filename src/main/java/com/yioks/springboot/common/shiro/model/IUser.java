package com.yioks.springboot.common.shiro.model;


import java.util.Collection;

public interface IUser {
  Object getIdentification();

  String getCredentials();

  default Collection<? extends IRole> getRoles() {
    return null;
  }

  default Collection<? extends IPermission> getPermissions() {
    return null;
  }
}
