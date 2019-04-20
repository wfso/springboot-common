package com.yioks.springboot.common.jpa.converter;

import javax.persistence.AttributeConverter;
import java.util.ArrayList;
import java.util.List;

public class DoubleListConverter implements AttributeConverter<List<Double>, String> {
  @Override
  public String convertToDatabaseColumn(List<Double> attribute) {
    if (attribute == null || attribute.size() == 0)
      return null;
    StringBuffer buffer = new StringBuffer();
    for (double l : attribute) {
      buffer.append(l).append(",");
    }
    return buffer.substring(0, buffer.length() - 1);
  }

  @Override
  public List<Double> convertToEntityAttribute(String dbData) {
    if (dbData == null || dbData.trim().length() == 0)
      return null;
    List<Double> result = new ArrayList<>();
    for (String str : dbData.split(",")) {
      result.add(Double.parseDouble(str));
    }
    return result;
  }
}
