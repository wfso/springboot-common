package com.yioks.springboot.common.base.mapper;

import com.yioks.springboot.common.jpa.filter.IFilter;
import org.apache.ibatis.annotations.Param;

import javax.annotation.Nullable;
import java.util.List;

public interface IMapper<T> {

  long count(@Nullable @Param("filter") IFilter filter);

  List<T> search(
    @Nullable @Param("filter") IFilter filter,
    @Param("offset") long offset,
    @Param("size") long size,
    @Nullable @Param("orderBy") String orderBy
  );

}
