package com.yioks.springboot.common.jpa.filter;

import java.util.List;

public interface IFilter {

  FilterType getType();

  String getKey();

  String getCondition();

  String getValue();

  List<String> getInList();

  List<? extends IFilter> getList();

  Class getClazz();

  boolean isNoValue();

  boolean isAndComplex();

  boolean isOrComplex();

  boolean isIn();

  boolean isComplex();
}
