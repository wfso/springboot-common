package com.yioks.springboot.common.base.service;

import com.yioks.springboot.common.jpa.filter.FilterGenerator;
import com.yioks.springboot.common.jpa.filter.IFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface QueryService<T,ID> {

  T getById(ID id);

  List<T> getByIds(Iterable<ID> ids);

  long countAll();

  long count(List<? extends IFilter> filters);

  long count(IFilter filter);

  default long count(FilterGenerator generator) {
    return count(generator.generate());
  }

  List<T> getAll();

  Page<T> getPage(Pageable pageable);

  Page<T> getPage(List<? extends IFilter> filters, Pageable pageable);

  List<T> getList(List<? extends IFilter> filters);

  Page<T> getPage(IFilter filter, Pageable pageable);

  List<T> getList(IFilter filter);

  default Page<T> getPage(FilterGenerator generator, Pageable pageable) {
    return getPage(generator.generate(), pageable);
  }

  default List<T> getList(FilterGenerator generator) {
    return getList(generator.generate());
  }
}
