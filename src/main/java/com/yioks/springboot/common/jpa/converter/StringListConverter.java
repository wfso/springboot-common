package com.yioks.springboot.common.jpa.converter;

import javax.persistence.AttributeConverter;
import java.util.Arrays;
import java.util.List;

public class StringListConverter implements AttributeConverter<List<String>, String> {
    @Override
    public String convertToDatabaseColumn(List<String> attribute) {
        if (attribute == null || attribute.size() == 0)
            return null;
        return String.join(",", attribute);
    }

    @Override
    public List<String> convertToEntityAttribute(String dbData) {
        if (dbData == null || dbData.trim().length() == 0)
            return null;
        return Arrays.asList(dbData.split(","));
    }
}
