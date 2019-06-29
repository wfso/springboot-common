package com.yioks.springboot.common.base.repository;

import com.yioks.springboot.common.base.model.IModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseJpaRepository<T extends IModel> extends JpaRepository<T, Long>, JpaSpecificationExecutor<T> {
  T getByUuid(String uuid);

  void deleteByUuid(String uuid);
}
