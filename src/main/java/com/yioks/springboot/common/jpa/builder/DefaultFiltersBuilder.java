package com.yioks.springboot.common.jpa.builder;

import com.yioks.springboot.common.jpa.filter.DefaultFilter;
import com.yioks.springboot.common.jpa.filter.FilterType;
import com.yioks.springboot.common.jpa.filter.IFilter;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class DefaultFiltersBuilder implements OrFiltersBuilder, AndFiltersBuilder {

  private FilterType type;

  private List<DefaultFilter> filters;

  private List<DefaultFilter> conditionFilters;

  public static AndFiltersBuilder getAndInstance() {
    return new DefaultFiltersBuilder(FilterType.and);
  }

  public static OrFiltersBuilder getOrInstance() {
    return new DefaultFiltersBuilder(FilterType.or);
  }

  private DefaultFiltersBuilder(FilterType type) {
    filters = new ArrayList<>();
    this.type = type;
  }

  @Override
  public IFilter build() {
    assureConditionFiltersIsProcessed();
    return new DefaultFilter(type, filters);
  }


  @Override
  public FiltersBuilder equal(String key, String value) {
    assureConditionFiltersIsProcessed();
    if (StringUtils.isNotEmpty(value)) {
      filters.add(new DefaultFilter(FilterType.equal, key, value));
    }
    return this;
  }

  @Override
  public FiltersBuilder equal(String key, String value, Class clazz) {
    assureConditionFiltersIsProcessed();
    if (StringUtils.isNotEmpty(value)) {
      filters.add(new DefaultFilter(FilterType.equal, key, value, clazz));
    }
    return this;
  }

  @Override
  public FiltersBuilder notEqual(String key, String value) {
    assureConditionFiltersIsProcessed();
    if (StringUtils.isNotEmpty(value)) {
      filters.add(new DefaultFilter(FilterType.notEqual, key, value));
    }
    return this;
  }

  @Override
  public FiltersBuilder notEqual(String key, String value, Class clazz) {
    assureConditionFiltersIsProcessed();
    if (StringUtils.isNotEmpty(value)) {
      filters.add(new DefaultFilter(FilterType.notEqual, key, value, clazz));
    }
    return this;
  }

  @Override
  public FiltersBuilder greaterEqual(String key, String value) {
    assureConditionFiltersIsProcessed();
    if (StringUtils.isNotEmpty(value)) {
      filters.add(new DefaultFilter(FilterType.greaterEqual, key, value));
    }
    return this;
  }

  @Override
  public FiltersBuilder lessEqual(String key, String value) {
    assureConditionFiltersIsProcessed();
    if (StringUtils.isNotEmpty(value)) {
      filters.add(new DefaultFilter(FilterType.lessEqual, key, value));
    }
    return this;
  }

  @Override
  public FiltersBuilder greaterThen(String key, String value) {
    assureConditionFiltersIsProcessed();
    if (StringUtils.isNotEmpty(value)) {
      filters.add(new DefaultFilter(FilterType.greaterThen, key, value));
    }
    return this;
  }

  @Override
  public FiltersBuilder lessThen(String key, String value) {
    assureConditionFiltersIsProcessed();
    if (StringUtils.isNotEmpty(value)) {
      filters.add(new DefaultFilter(FilterType.lessThen, key, value));
    }
    return this;
  }

  @Override
  public FiltersBuilder like(String key, String value) {
    assureConditionFiltersIsProcessed();
    if (StringUtils.isNotEmpty(value)) {
      filters.add(new DefaultFilter(FilterType.like, key, value));
    }
    return this;
  }

  @Override
  public FiltersBuilder notLike(String key, String value) {
    assureConditionFiltersIsProcessed();
    if (StringUtils.isNotEmpty(value)) {
      filters.add(new DefaultFilter(FilterType.notLike, key, value));
    }
    return this;
  }

  @Override
  public FiltersBuilder in(String key, List<String> strings) {
    assureConditionFiltersIsProcessed();
    if (strings != null && strings.size() > 0) {
      filters.add(new DefaultFilter(FilterType.in, key, strings));
    }
    return this;
  }

  @Override
  public FiltersBuilder notIn(String key, List<String> strings) {
    assureConditionFiltersIsProcessed();
    if (strings != null && strings.size() > 0) {
      filters.add(new DefaultFilter(FilterType.notIn, key, strings));
    }
    return this;
  }

  @Override
  public FiltersBuilder in(String key, List<String> strings, Class clazz) {
    assureConditionFiltersIsProcessed();
    if (strings != null && strings.size() > 0) {
      filters.add(new DefaultFilter(FilterType.in, key, strings, clazz));
    }
    return this;
  }

  @Override
  public FiltersBuilder notIn(String key, List<String> strings, Class clazz) {
    assureConditionFiltersIsProcessed();
    if (strings != null && strings.size() > 0) {
      filters.add(new DefaultFilter(FilterType.notIn, key, strings, clazz));
    }
    return this;
  }

  @Override
  public FiltersBuilder isNull(String key) {
    assureConditionFiltersIsProcessed();
    filters.add(new DefaultFilter(FilterType.isNull, key));
    return this;
  }

  @Override
  public FiltersBuilder isNotNull(String key) {
    assureConditionFiltersIsProcessed();
    filters.add(new DefaultFilter(FilterType.isNotNull, key));
    return this;
  }


  @Override
  public FiltersBuilder isEmpty(String key) {
    assureConditionFiltersIsProcessed();
    filters.add(new DefaultFilter(FilterType.equal, key, ""));
    return this;
  }

  @Override
  public FiltersBuilder isNotEmpty(String key) {
    assureConditionFiltersIsProcessed();
    filters.add(new DefaultFilter(FilterType.notEqual, key, ""));
    return this;
  }

  @Override
  public FiltersBuilder eq(String key, String value) {
    assureConditionFiltersIsProcessed();
    if (StringUtils.isNotEmpty(value)) {
      filters.add(new DefaultFilter(FilterType.eq, key, value));
    }
    return this;
  }

  @Override
  public FiltersBuilder ne(String key, String value) {
    assureConditionFiltersIsProcessed();
    if (StringUtils.isNotEmpty(value)) {
      filters.add(new DefaultFilter(FilterType.ne, key, value));
    }
    return this;
  }

  @Override
  public FiltersBuilder ge(String key, String value) {
    assureConditionFiltersIsProcessed();
    if (StringUtils.isNotEmpty(value)) {
      filters.add(new DefaultFilter(FilterType.ge, key, value));
    }
    return this;
  }

  @Override
  public FiltersBuilder le(String key, String value) {
    assureConditionFiltersIsProcessed();
    if (StringUtils.isNotEmpty(value)) {
      filters.add(new DefaultFilter(FilterType.le, key, value));
    }
    return this;
  }

  @Override
  public FiltersBuilder gt(String key, String value) {
    assureConditionFiltersIsProcessed();
    if (StringUtils.isNotEmpty(value)) {
      filters.add(new DefaultFilter(FilterType.gt, key, value));
    }
    return this;
  }

  @Override
  public FiltersBuilder lt(String key, String value) {
    assureConditionFiltersIsProcessed();
    if (StringUtils.isNotEmpty(value)) {
      filters.add(new DefaultFilter(FilterType.lt, key, value));
    }
    return this;
  }

  @Override
  public OrConditionBuilder createOrCondition() {
    assureConditionFiltersIsProcessed();
    return new DefaultConditionBuilder();
  }

  @Override
  public AndConditionBuilder createAndCondition() {
    assureConditionFiltersIsProcessed();
    return new DefaultConditionBuilder();
  }

  private void assureConditionFiltersIsProcessed() {
    if (conditionFilters != null && conditionFilters.size() > 0) {
      if (type == FilterType.and) {
        filters.add(new DefaultFilter(FilterType.or, conditionFilters));
      } else {
        filters.add(new DefaultFilter(FilterType.and, conditionFilters));
      }
      conditionFilters = null;
    }
  }

  class DefaultConditionBuilder implements OrConditionBuilder, AndConditionBuilder {

    @Override
    public ConditionBuilder equal(String key, String value) {
      assureConditionFiltersIsNotNull();
      if (StringUtils.isNotEmpty(value)) {
        conditionFilters.add(new DefaultFilter(FilterType.equal, key, value));
      }
      return this;
    }

    @Override
    public ConditionBuilder equal(String key, String value, Class clazz) {
      assureConditionFiltersIsNotNull();
      if (StringUtils.isNotEmpty(value)) {
        conditionFilters.add(new DefaultFilter(FilterType.equal, key, value, clazz));
      }
      return this;
    }

    @Override
    public ConditionBuilder notEqual(String key, String value) {
      assureConditionFiltersIsNotNull();
      if (StringUtils.isNotEmpty(value)) {
        conditionFilters.add(new DefaultFilter(FilterType.notEqual, key, value));
      }
      return this;
    }

    @Override
    public ConditionBuilder notEqual(String key, String value, Class clazz) {
      assureConditionFiltersIsNotNull();
      if (StringUtils.isNotEmpty(value)) {
        conditionFilters.add(new DefaultFilter(FilterType.notEqual, key, value, clazz));
      }
      return this;
    }

    @Override
    public ConditionBuilder greaterEqual(String key, String value) {
      assureConditionFiltersIsNotNull();
      if (StringUtils.isNotEmpty(value)) {
        conditionFilters.add(new DefaultFilter(FilterType.greaterEqual, key, value));
      }
      return this;
    }

    @Override
    public ConditionBuilder lessEqual(String key, String value) {
      assureConditionFiltersIsNotNull();
      if (StringUtils.isNotEmpty(value)) {
        conditionFilters.add(new DefaultFilter(FilterType.lessEqual, key, value));
      }
      return this;
    }

    @Override
    public ConditionBuilder greaterThen(String key, String value) {
      assureConditionFiltersIsNotNull();
      if (StringUtils.isNotEmpty(value)) {
        conditionFilters.add(new DefaultFilter(FilterType.greaterThen, key, value));
      }
      return this;
    }

    @Override
    public ConditionBuilder lessThen(String key, String value) {
      assureConditionFiltersIsNotNull();
      if (StringUtils.isNotEmpty(value)) {
        conditionFilters.add(new DefaultFilter(FilterType.lessThen, key, value));
      }
      return this;
    }

    @Override
    public ConditionBuilder like(String key, String value) {
      assureConditionFiltersIsNotNull();
      if (StringUtils.isNotEmpty(value)) {
        conditionFilters.add(new DefaultFilter(FilterType.like, key, value));
      }
      return this;
    }

    @Override
    public ConditionBuilder notLike(String key, String value) {
      assureConditionFiltersIsNotNull();
      if (StringUtils.isNotEmpty(value)) {
        conditionFilters.add(new DefaultFilter(FilterType.notLike, key, value));
      }
      return this;
    }

    @Override
    public ConditionBuilder in(String key, List<String> strings) {
      assureConditionFiltersIsNotNull();
      if (strings != null && strings.size() > 0) {
        conditionFilters.add(new DefaultFilter(FilterType.in, key, strings));
      }
      return this;
    }

    @Override
    public ConditionBuilder notIn(String key, List<String> strings) {
      assureConditionFiltersIsNotNull();
      if (strings != null && strings.size() > 0) {
        conditionFilters.add(new DefaultFilter(FilterType.notIn, key, strings));
      }
      return this;
    }

    @Override
    public ConditionBuilder in(String key, List<String> strings, Class clazz) {
      assureConditionFiltersIsNotNull();
      if (strings != null && strings.size() > 0) {
        conditionFilters.add(new DefaultFilter(FilterType.in, key, strings, clazz));
      }
      return this;
    }

    @Override
    public ConditionBuilder notIn(String key, List<String> strings, Class clazz) {
      assureConditionFiltersIsNotNull();
      if (strings != null && strings.size() > 0) {
        conditionFilters.add(new DefaultFilter(FilterType.notIn, key, strings, clazz));
      }
      return this;
    }

    @Override
    public ConditionBuilder isNull(String key) {
      assureConditionFiltersIsNotNull();
      conditionFilters.add(new DefaultFilter(FilterType.isNull, key));
      return this;
    }

    @Override
    public ConditionBuilder isNotNull(String key) {
      assureConditionFiltersIsNotNull();
      conditionFilters.add(new DefaultFilter(FilterType.isNotNull, key));
      return this;
    }

    @Override
    public ConditionBuilder isEmpty(String key) {
      assureConditionFiltersIsNotNull();
      conditionFilters.add(new DefaultFilter(FilterType.equal, key, ""));
      return this;
    }

    @Override
    public ConditionBuilder isNotEmpty(String key) {
      assureConditionFiltersIsNotNull();
      conditionFilters.add(new DefaultFilter(FilterType.notEqual, key, ""));
      return this;
    }

    @Override
    public ConditionBuilder eq(String key, String value) {
      assureConditionFiltersIsNotNull();
      if (StringUtils.isNotEmpty(value)) {
        conditionFilters.add(new DefaultFilter(FilterType.eq, key, value));
      }
      return this;
    }

    @Override
    public ConditionBuilder ne(String key, String value) {
      assureConditionFiltersIsNotNull();
      if (StringUtils.isNotEmpty(value)) {
        conditionFilters.add(new DefaultFilter(FilterType.ne, key, value));
      }
      return this;
    }

    @Override
    public ConditionBuilder ge(String key, String value) {
      assureConditionFiltersIsNotNull();
      if (StringUtils.isNotEmpty(value)) {
        conditionFilters.add(new DefaultFilter(FilterType.ge, key, value));
      }
      return this;
    }

    @Override
    public ConditionBuilder le(String key, String value) {
      assureConditionFiltersIsNotNull();
      if (StringUtils.isNotEmpty(value)) {
        conditionFilters.add(new DefaultFilter(FilterType.le, key, value));
      }
      return this;
    }

    @Override
    public ConditionBuilder gt(String key, String value) {
      assureConditionFiltersIsNotNull();
      if (StringUtils.isNotEmpty(value)) {
        conditionFilters.add(new DefaultFilter(FilterType.gt, key, value));
      }
      return this;
    }

    @Override
    public ConditionBuilder lt(String key, String value) {
      assureConditionFiltersIsNotNull();
      if (StringUtils.isNotEmpty(value)) {
        conditionFilters.add(new DefaultFilter(FilterType.lt, key, value));
      }
      return this;
    }


    @Override
    public OrFiltersBuilder endAnd() {
      return DefaultFiltersBuilder.this;
    }

    @Override
    public AndFiltersBuilder endOr() {
      return DefaultFiltersBuilder.this;
    }

    private void assureConditionFiltersIsNotNull() {
      if (conditionFilters == null) {
        conditionFilters = new ArrayList<>();
      }
    }
  }

}
