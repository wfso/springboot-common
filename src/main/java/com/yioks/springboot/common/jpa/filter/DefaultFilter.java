package com.yioks.springboot.common.jpa.filter;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@ApiModel(description = "JPA搜索过滤器")
public class DefaultFilter implements IFilter {

  @ApiModelProperty("过滤规则，配合 value 完成过滤的约束条件，只有满足约束条件的对象才会被返回")
  private FilterType type;

  /**
   * 数据库字段
   */
  @ApiModelProperty("被过滤的属性名-说明通过对象的哪个属性进行过滤")
  private String key;

  /**
   * 值
   */
  @ApiModelProperty("被过滤的属性的约束值，配合 type 完成过滤的约束条件，只有满足约束条件的对象才会被返回")
  private String value;

  /**
   * 当 type 为 and 或 or 时使用
   */
  @ApiModelProperty("当 type 的值为 and 或 or 时，代替 value 作为属性的约束值，其类型为 List&lt;DefaultFilter&gt;")
  private List<? extends IFilter> list;

  /**
   * 当 type 为 in 时使用
   */
  @ApiModelProperty("当 type 的值为 in 时，代替 value 作为属性的约束值")
  private List<String> inList;


  /**
   * class 用于枚举类型
   */
  @JsonIgnore
  @ApiModelProperty("当被过滤的属性为“枚举类型”时，用于说明“枚举类型”的全类型名")
  private String clazzName;

  @JsonIgnore
  @ApiModelProperty("当前对象所对应的SQL条件")
  private String condition;

  /**
   * class 用于枚举类型
   */
  @ApiModelProperty("当被过滤的属性为“枚举类型”时，用于说明“枚举类型”的Class对象")
  private Class clazz;


  public DefaultFilter() {
  }


  public DefaultFilter(FilterType type, String key) {
    this.type = type;
    this.key = key;
  }

  public DefaultFilter(FilterType type, List<? extends IFilter> filters) {
    this.type = type;
    this.list = filters;
  }

  public DefaultFilter(FilterType type, String key, String value) {
    this.type = type;
    this.key = key;
    this.value = value;
  }

  public DefaultFilter(FilterType type, String key, String value, Class clazz) {
    this.type = type;
    this.key = key;
    this.value = value;
    this.clazz = clazz;
  }

  public DefaultFilter(FilterType type, String key, List<String> inList) {
    this.type = type;
    this.key = key;
    this.inList = inList;
  }


  public DefaultFilter(FilterType type, String key, List<String> inList, Class clazz) {
    this.type = type;
    this.key = key;
    this.inList = inList;
    this.clazz = clazz;
  }


  @Override
  public FilterType getType() {
    return type;
  }

  @Override
  public String getKey() {
    return key;
  }

  @Override
  public String getCondition() {
    if (condition == null) {
      StringBuffer sb = new StringBuffer();
      sb.append("`")
        .append(key.replace(".", "`.`"))
        .append("` ");
      switch (type) {
        case isNotNull: {
          sb.append("IS NOT NULL");
          break;
        }
        case isNull: {
          sb.append("IS NULL");
          break;
        }
        case eq:
        case equal: {
          sb.append("=");
          break;
        }
        case ge:
        case greaterEqual: {
          sb.append(">=");
          break;
        }
        case gt:
        case greaterThen: {
          sb.append(">");
          break;
        }
        case in: {
          sb.append("IN");
          break;
        }
        case le:
        case lessEqual: {
          sb.append("<=");
          break;
        }
        case lt:
        case lessThen: {
          sb.append("<");
          break;
        }
        case ne:
        case notEqual: {
          sb.append("<>");
          break;
        }
        case like: {
          sb.append("LIKE");
          break;
        }
        case notIn: {
          sb.append("NOT IN");
          break;
        }
        case notLike: {
          sb.append("NOT LIKE");
          break;
        }
      }
      condition = sb.toString();
    }
    return condition;
  }

  @Override
  public String getValue() {
    return value;
  }

  @Override
  public List<? extends IFilter> getList() {
    return list;
  }

  @Override
  public List<String> getInList() {
    return inList;
  }

  @Override
  public Class getClazz() {
    if (clazz == null && clazzName != null && clazzName.length() > 0) {
      try {
        clazz = Class.forName(clazzName);
      } catch (ClassNotFoundException e) {
        e.printStackTrace();
      }
    }
    return clazz;
  }

  @Override
  public boolean isNoValue() {
    return type == FilterType.isNotNull || type == FilterType.isNull;
  }

  @Override
  public boolean isAndComplex() {
    return type == FilterType.and;
  }

  @Override
  public boolean isOrComplex() {
    return type == FilterType.or;
  }

  @Override
  public boolean isIn() {
    return type == FilterType.in || type == FilterType.notIn;
  }

  @Override
  public boolean isComplex() {
    return isAndComplex() || isOrComplex();
  }
}
