package com.yioks.springboot.common.base.service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

public interface IService<T, ID> extends IQueryService<T, ID> {
  T create(T entity);

  T update(T entity);

  List<T> create(Iterable<T> entities);

  List<T> update(Iterable<T> entities);

  T updateIgnoreNull(T entity);

  T updateIgnoreEmpty(T entity);

  @Transactional
  default List<T> updateIgnoreNull(Iterable<T> entities) {
    List<T> list = new ArrayList();
    for (T entity : entities) {
      list.add(updateIgnoreNull(entity));
    }
    return list;
  }

  @Transactional
  default List<T> updateIgnoreEmpty(Iterable<T> entities) {
    List<T> list = new ArrayList();
    for (T entity : entities) {
      list.add(updateIgnoreEmpty(entity));
    }
    return list;
  }

  void remove(T entity);

  void remove(Iterable<T> entities);

  void removeById(ID id);


  void removeByUuid(String Uuid);

  @Transactional
  default void removeByIds(Iterable<ID> ids) {
    remove(getByIds(ids));
  }

}
