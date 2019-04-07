package com.yioks.springboot.common.jpa.builder;

import com.yioks.springboot.common.jpa.filter.IFilter;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

public interface FiltersBuilder {

  IFilter build();

  FiltersBuilder equal(String key, String value);

  FiltersBuilder equal(String key, String value, Class clazz);

  FiltersBuilder notEqual(String key, String value);

  FiltersBuilder notEqual(String key, String value, Class clazz);

  FiltersBuilder greaterEqual(String key, String value);

  FiltersBuilder lessEqual(String key, String value);

  FiltersBuilder greaterThen(String key, String value);

  FiltersBuilder lessThen(String key, String value);

  FiltersBuilder like(String key, String value);

  default FiltersBuilder startWith(String key, String value) {
    if (StringUtils.isNotEmpty(value)) {
      this.like(key, value + "%");
    }
    return this;
  }

  default FiltersBuilder endWith(String key, String value) {
    if (StringUtils.isNotEmpty(value)) {
      this.like(key, "%" + value);
    }
    return this;
  }

  default FiltersBuilder contain(String key, String value) {
    if (StringUtils.isNotEmpty(value)) {
      this.like(key, "%" + value + "%");
    }
    return this;
  }

  FiltersBuilder notLike(String key, String value);

  default FiltersBuilder notStartWith(String key, String value) {
    if (StringUtils.isNotEmpty(value)) {
      this.notLike(key, value + "%");
    }
    return this;
  }

  default FiltersBuilder notEndWith(String key, String value) {
    if (StringUtils.isNotEmpty(value)) {
      this.notLike(key, "%" + value);
    }
    return this;
  }

  default FiltersBuilder notContain(String key, String value) {
    if (StringUtils.isNotEmpty(value)) {
      this.notLike(key, "%" + value + "%");
    }
    return this;
  }

  FiltersBuilder in(String key, List<String> strings);

  FiltersBuilder notIn(String key, List<String> strings);

  FiltersBuilder in(String key, List<String> strings, Class clazz);

  FiltersBuilder notIn(String key, List<String> strings, Class clazz);

  FiltersBuilder isNull(String key);

  FiltersBuilder isNotNull(String key);

  FiltersBuilder isEmpty(String key);

  FiltersBuilder isNotEmpty(String key);

  FiltersBuilder eq(String key, String value);

  FiltersBuilder ne(String key, String value);

  FiltersBuilder ge(String key, String value);

  FiltersBuilder le(String key, String value);

  FiltersBuilder gt(String key, String value);

  FiltersBuilder lt(String key, String value);

  interface ConditionBuilder {
    ConditionBuilder equal(String key, String value);

    ConditionBuilder equal(String key, String value, Class clazz);

    ConditionBuilder notEqual(String key, String value);

    ConditionBuilder notEqual(String key, String value, Class clazz);

    ConditionBuilder greaterEqual(String key, String value);

    ConditionBuilder lessEqual(String key, String value);

    ConditionBuilder greaterThen(String key, String value);

    ConditionBuilder lessThen(String key, String value);

    ConditionBuilder like(String key, String value);

    default ConditionBuilder startWith(String key, String value) {
      if (StringUtils.isNotEmpty(value)) {
        this.like(key, value + "%");
      }
      return this;
    }

    default ConditionBuilder endWith(String key, String value) {
      if (StringUtils.isNotEmpty(value)) {
        this.like(key, "%" + value);
      }
      return this;
    }

    default ConditionBuilder contain(String key, String value) {
      if (StringUtils.isNotEmpty(value)) {
        this.like(key, "%" + value + "%");
      }
      return this;
    }

    ConditionBuilder notLike(String key, String value);

    default ConditionBuilder notStartWith(String key, String value) {
      if (StringUtils.isNotEmpty(value)) {
        this.notLike(key, value + "%");
      }
      return this;
    }

    default ConditionBuilder notEndWith(String key, String value) {
      if (StringUtils.isNotEmpty(value)) {
        this.notLike(key, "%" + value);
      }
      return this;
    }

    default ConditionBuilder notContain(String key, String value) {
      if (StringUtils.isNotEmpty(value)) {
        this.notLike(key, "%" + value + "%");
      }
      return this;
    }

    ConditionBuilder in(String key, List<String> strings);

    ConditionBuilder notIn(String key, List<String> strings);

    ConditionBuilder in(String key, List<String> strings, Class clazz);

    ConditionBuilder notIn(String key, List<String> strings, Class clazz);

    ConditionBuilder isNull(String key);

    ConditionBuilder isNotNull(String key);

    ConditionBuilder isEmpty(String key);

    ConditionBuilder isNotEmpty(String key);

    ConditionBuilder eq(String key, String value);

    ConditionBuilder ne(String key, String value);

    ConditionBuilder ge(String key, String value);

    ConditionBuilder le(String key, String value);

    ConditionBuilder gt(String key, String value);

    ConditionBuilder lt(String key, String value);
  }

}
