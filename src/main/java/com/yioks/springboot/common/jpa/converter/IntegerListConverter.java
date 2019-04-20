package com.yioks.springboot.common.jpa.converter;

import javax.persistence.AttributeConverter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class IntegerListConverter implements AttributeConverter<List<Integer>, String> {
  @Override
  public String convertToDatabaseColumn(List<Integer> attribute) {
    if (attribute == null || attribute.size() == 0)
      return null;
    StringBuffer buffer = new StringBuffer();
    for (int l : attribute) {
      buffer.append(l).append(",");
    }
    return buffer.substring(0, buffer.length() - 1);
  }

  @Override
  public List<Integer> convertToEntityAttribute(String dbData) {
    if (dbData == null || dbData.trim().length() == 0)
      return null;
    List<Integer> result = new ArrayList<>();
    for (String str : dbData.split(",")) {
      result.add(Integer.parseInt(str));
    }
    return result;
  }
}
