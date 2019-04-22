package com.yioks.springboot.common.service;

import com.yioks.springboot.common.model.IPermission;

import java.util.List;


public interface IPermissionService<T extends IPermission> {
  T getById(long id);

  List<T> getById(Iterable<Long> ids);

  T getByCode(String code);
}
