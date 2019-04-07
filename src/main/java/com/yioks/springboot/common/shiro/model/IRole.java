package com.yioks.springboot.common.shiro.model;

import java.util.Collection;

public interface IRole {
  String getCode();

  String getName();

  default Collection<? extends IPermission> getPermissions() {
    return null;
  }
}
