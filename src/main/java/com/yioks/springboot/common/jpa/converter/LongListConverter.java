package com.yioks.springboot.common.jpa.converter;

import javax.persistence.AttributeConverter;
import java.util.ArrayList;
import java.util.List;

public class LongListConverter implements AttributeConverter<List<Long>, String> {
  @Override
  public String convertToDatabaseColumn(List<Long> attribute) {
    if (attribute == null || attribute.size() == 0)
      return null;
    StringBuffer buffer = new StringBuffer();
    for (long l : attribute) {
      buffer.append(l).append(",");
    }
    return buffer.substring(0, buffer.length() - 1);
  }

  @Override
  public List<Long> convertToEntityAttribute(String dbData) {
    if (dbData == null || dbData.trim().length() == 0)
      return null;
    List<Long> result = new ArrayList<>();
    for (String str : dbData.split(",")) {
      result.add(Long.parseLong(str));
    }
    return result;
  }
}
