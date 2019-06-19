package com.yioks.springboot.common.base.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseJpaRepository<T,ID> extends JpaRepository<T, ID>, JpaSpecificationExecutor<T> {
}
