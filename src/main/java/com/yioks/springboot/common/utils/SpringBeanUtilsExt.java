package com.yioks.springboot.common.utils;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.BeansException;
import org.springframework.util.ObjectUtils;

import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.Set;

public class SpringBeanUtilsExt extends BeanUtils {

  // 获取值为null的属性名
  public static String[] getNullPropertyNames(Object source) {
    final BeanWrapper sourceWrapper = new BeanWrapperImpl(source);
    PropertyDescriptor[] pds = sourceWrapper.getPropertyDescriptors();

    Set<String> noValuePropertySet = new HashSet<>();
    for (PropertyDescriptor pd : pds) {
      Object propertyValue = sourceWrapper.getPropertyValue(pd.getName());
      if (propertyValue == null) noValuePropertySet.add(pd.getName());
    }
    String[] result = new String[noValuePropertySet.size()];
    return noValuePropertySet.toArray(result);
  }

  // 获取值为“空”（null，空字符串，空集合等）的属性名
  public static String[] getEmptyPropertyNames(Object source) {
    final BeanWrapper sourceWrapper = new BeanWrapperImpl(source);
    PropertyDescriptor[] pds = sourceWrapper.getPropertyDescriptors();

    Set<String> noValuePropertySet = new HashSet<>();
    for (PropertyDescriptor pd : pds) {
      Object propertyValue = sourceWrapper.getPropertyValue(pd.getName());
      if (ObjectUtils.isEmpty(propertyValue)) noValuePropertySet.add(pd.getName());
    }
    String[] result = new String[noValuePropertySet.size()];
    return noValuePropertySet.toArray(result);
  }

  // 忽略 null 进行 copy
  public static void copyPropertiesIgnoreNull(Object source, Object target) throws BeansException {
    copyProperties(source, target, getNullPropertyNames(source));
  }


  // 忽略 “空”（null，空字符串，空集合等） 进行 copy
  public static void copyPropertiesIgnoreEmpty(Object source, Object target) throws BeansException {
    copyProperties(source, target, getEmptyPropertyNames(source));
  }
}
