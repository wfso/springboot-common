package com.yioks.springboot.common.base.service;

import com.yioks.springboot.common.base.mapper.IMapper;
import com.yioks.springboot.common.jpa.builder.DefaultFiltersBuilder;
import com.yioks.springboot.common.jpa.filter.DefaultFilter;
import com.yioks.springboot.common.jpa.filter.FilterType;
import com.yioks.springboot.common.jpa.filter.IFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseMyBatisService<T> implements IQueryService<T, Long> {

  protected abstract IMapper<T> getMapper();

  protected abstract String getIdFieldName();
  protected abstract String getUuidFieldName();

  @Override
  public T getById(Long id) {
    IFilter filter = DefaultFiltersBuilder.getAndInstance()
      .andEqual(getIdFieldName(), id.toString())
      .build();
    List<T> ts = getMapper().search(filter, 0, 1, null);
    return ts != null && ts.size() > 0 ? ts.get(0) : null;
  }

  @Override
  public T getByUuid(String id) {
    IFilter filter = DefaultFiltersBuilder.getAndInstance()
      .andEqual(getUuidFieldName(), id)
      .build();
    List<T> ts = getMapper().search(filter, 0, 1, null);
    return ts != null && ts.size() > 0 ? ts.get(0) : null;
  }

  @Override
  public List<T> getByIds(Iterable<Long> ids) {
    List<String> strs = new ArrayList<>();
    for (Long id : ids) {
      strs.add(id.toString());
    }
    IFilter filter = DefaultFiltersBuilder.getAndInstance()
      .andIn(getIdFieldName(), strs)
      .build();
    return getMapper().search(filter, 0, strs.size(), null);
  }

  @Override
  public long countAll() {
    return getMapper().count(null);
  }

  @Override
  public long count(List<? extends IFilter> filters) {
    return count(new DefaultFilter(FilterType.and, filters));
  }

  @Override
  public long count(IFilter filter) {
    return getMapper().count(filter);
  }

  @Override
  public List<T> getAll() {
    return getMapper().search(null, 0, countAll(), null);
  }

  @Override
  public Page<T> getPage(Pageable pageable) {
    long total = countAll();
    List<T> ts = getMapper().search(null, pageable.getOffset(), pageable.getPageSize(), null);
    return new PageImpl<>(ts, pageable, total);
  }

  @Override
  public Page<T> getPage(List<? extends IFilter> filters, Pageable pageable) {
    return getPage(new DefaultFilter(FilterType.and, filters), pageable);
  }

  @Override
  public List<T> getList(List<? extends IFilter> filters) {
    return getList(new DefaultFilter(FilterType.and, filters));
  }

  @Override
  public Page<T> getPage(IFilter filter, Pageable pageable) {
    long total = count(filter);
    List<T> ts = getMapper().search(filter, pageable.getOffset(), pageable.getPageSize(), null);
    return new PageImpl<>(ts, pageable, total);
  }

  @Override
  public List<T> getList(IFilter filter) {
    long total = count(filter);
    return getMapper().search(filter, 0, total, null);
  }
}
