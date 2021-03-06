package com.yioks.springboot.common.base.service;

import com.yioks.springboot.common.base.model.Model;
import com.yioks.springboot.common.jpa.filter.IFilter;
import com.yioks.springboot.common.jpa.specification.AbstractSpecification;
import com.yioks.springboot.common.base.repository.BaseJpaRepository;
import com.yioks.springboot.common.utils.SpringBeanUtilsExt;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import javax.transaction.Transactional;
import java.util.*;

public abstract class BaseJpaService<T extends Model<ID>, ID> implements Service<T, ID> {
  protected abstract BaseJpaRepository<T, ID> getRepository();

  protected ID generateId() {
    return null;
  }

  @Override
  public T create(T entity) {
    initCreateDomain(entity);
    getRepository().save(entity);
    return entity;
  }

  @Override
  public T update(T entity) {
    initUpdateDomain(entity);
    getRepository().save(entity);
    return entity;
  }

  @Transactional
  @Override
  public List<T> create(Iterable<T> entities) {
    for (T t : entities) {
      initCreateDomain(t);
    }
    return getRepository().saveAll(entities);
  }

  @Transactional
  @Override
  public List<T> update(Iterable<T> entities) {
    for (T t : entities) {
      initUpdateDomain(t);
    }
    return getRepository().saveAll(entities);
  }

  @Transactional
  @Override
  public T updateIgnoreNull(T entity) {
    T target = getById(entity.getId());
    SpringBeanUtilsExt.copyPropertiesIgnoreNull(entity, target);
    return update(target);
  }

  @Transactional
  @Override
  public T updateIgnoreEmpty(T entity) {
    T target = getById(entity.getId());
    SpringBeanUtilsExt.copyPropertiesIgnoreEmpty(entity, target);
    return update(target);
  }

  @Override
  public void removeById(ID id) {
    getRepository().deleteById(id);
  }

  @Override
  public void remove(Iterable<T> entities) {
    getRepository().deleteInBatch(entities);
  }

  @Override
  public void remove(T entity) {
    getRepository().delete(entity);
  }

  @Override
  public long countAll() {
    return getRepository().count();
  }

  @Override
  public long count(List<? extends IFilter> filters) {
    return getRepository().count(getSpecification(filters));
  }

  @Override
  public long count(IFilter filter) {
    return getRepository().count(getSpecification(filter));
  }

  @Override
  public T getById(ID id) {
    return getRepository().findById(id).orElse(null);
  }

  @Override
  public List<T> getByIds(Iterable<ID> ids) {
    return getRepository().findAllById(ids);
  }

  @Override
  public List<T> getAll() {
    return getRepository().findAll();
  }

  @Override
  public Page<T> getPage(Pageable pageable) {
    return getRepository().findAll(pageable);
  }

  @Override
  public Page<T> getPage(List<? extends IFilter> filters, Pageable pageable) {
    return getRepository().findAll(getSpecification(filters), pageable);
  }

  @Override
  public List<T> getList(List<? extends IFilter> filters) {
    return getRepository().findAll(getSpecification(filters));
  }

  @Override
  public Page<T> getPage(IFilter filter, Pageable pageable) {
    return getRepository().findAll(getSpecification(filter), pageable);
  }

  @Override
  public List<T> getList(IFilter filter) {
    return getRepository().findAll(getSpecification(filter));
  }

  protected void initCreateDomain(T entity) {
    initCommonDomain(entity);
    long time = System.currentTimeMillis();
    entity.setCreatedAt(time);
    entity.setId(generateId());
  }

  protected void initUpdateDomain(T entity) {
    initCommonDomain(entity);
  }

  protected void initCommonDomain(T entity) {
    long time = System.currentTimeMillis();
    entity.setUpdatedAt(time);
  }

  public static <T> Specification<T> getSpecification(IFilter filter) {
    if (filter == null || filter.getList().size() == 0) {
      return null;
    }
    return new AbstractSpecification<T>() {
      @Override
      public Predicate toPredicate(Root<T> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        return generatePredicate(filter, root, criteriaBuilder);
      }
    };
  }

  public static <T> Specification<T> getSpecification(List<? extends IFilter> filters) {
    return getSpecification(filters, true);
  }

  public static <T> Specification<T> getSpecification(List<? extends IFilter> filters, boolean and) {
    if (filters == null || filters.size() == 0) {
      return null;
    }
    return new AbstractSpecification<T>() {
      @Override
      public Predicate toPredicate(Root<T> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        return generatePredicate(filters, root, criteriaBuilder, and);
      }
    };
  }

}
