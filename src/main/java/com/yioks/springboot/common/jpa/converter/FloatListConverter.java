package com.yioks.springboot.common.jpa.converter;

import javax.persistence.AttributeConverter;
import java.util.ArrayList;
import java.util.List;

public class FloatListConverter implements AttributeConverter<List<Float>, String> {
  @Override
  public String convertToDatabaseColumn(List<Float> attribute) {
    if (attribute == null || attribute.size() == 0)
      return null;
    StringBuffer buffer = new StringBuffer();
    for (float l : attribute) {
      buffer.append(l).append(",");
    }
    return buffer.substring(0, buffer.length() - 1);
  }

  @Override
  public List<Float> convertToEntityAttribute(String dbData) {
    if (dbData == null || dbData.trim().length() == 0)
      return null;
    List<Float> result = new ArrayList<>();
    for (String str : dbData.split(",")) {
      result.add(Float.parseFloat(str));
    }
    return result;
  }
}
