package com.yioks.springboot.common.base.controller;


public interface Controller<T, ID> {
  T add(T t);

  T edit(T t);

  T remove(ID id);

  T info(ID id);
}
