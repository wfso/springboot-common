package com.yioks.springboot.common.base.controller;

public interface IBaseController<T, ID> {
  T add(T t);

  T edit(T t);

  T remove(ID id);

  T info(ID id);
}
