package com.yioks.springboot.common.jpa.filter;

public enum  FilterType {
  /*
   * 数据库 and 运算
   */
  and,

  /*
   * 数据库 or 运算
   */
  or,

  /*
   * 数据库 in 运算
   */
  in,

  /*
   * 数据库 notIn 运算
   */
  notIn,

  /*
   * 数据库 数字相等 运算 =,==
   */
  eq,

  /*
   * 数据库 数字大于等于 运算 >=
   */
  ge,

  /*
   * 数据库 数字小于等于 运算 <=
   */
  le,

  /*
   * 数据库 数字大于 运算 >
   */
  gt,

  /*
   * 数据库 数字小于 运算 <
   */
  lt,

  /*
   * 数据库 数字不等于 运算 <>,!=
   */
  ne,

  /*
   * 数据库 字符串相等 运算 =,==
   */
  equal,

  /*
   * 数据库 字符串大于等于 运算 >=
   */
  greaterEqual,

  /*
   * 数据库 字符串小于等于 运算 <=
   */
  lessEqual,

  /*
   * 数据库 字符串大于 运算 >
   */
  greaterThen,

  /*
   * 数据库 字符串小于 运算 <
   */
  lessThen,

  /*
   * 数据库 字符串不等于 运算 <>,!=
   */
  notEqual,

  /*
   * 数据库 模糊匹配 运算 like
   */
  like,

  /*
   * 数据库 模糊匹配 运算 notLike
   */
  notLike,

  /*
   * 数据库 判断为空 运算 is null
   */
  isNull,

  /*
   * 数据库 判断非空 运算 is not null
   */
  isNotNull;

}
