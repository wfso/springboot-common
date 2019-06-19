package com.yioks.springboot.common.service;

import com.yioks.springboot.common.model.IPermission;

import java.util.List;


public interface IPermissionService<T extends IPermission<ID>,ID> {
  T getById(ID id);

  List<T> getById(Iterable<ID> ids);

  T getByCode(String code);
}
