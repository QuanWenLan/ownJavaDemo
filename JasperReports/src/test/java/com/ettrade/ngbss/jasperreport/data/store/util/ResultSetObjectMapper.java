package com.ettrade.ngbss.jasperreport.data.store.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class ResultSetObjectMapper {
    private static Logger log = LoggerFactory.getLogger(ResultSetObjectMapper.class);

    public static List<Field> getAllFields(List<Field> fields, Class<?> type) {
        fields.addAll(Arrays.asList(type.getDeclaredFields()));

        if (type.getSuperclass() != null) {
            getAllFields(fields, type.getSuperclass());
        }

        return fields;
    }

    public static <T> T convert(ResultSet resultSet, Class<T> clazz) {
        log.info("convert starts");
        try {
            List<Field> fields = getAllFields(new ArrayList<>(), clazz);

            for (Field field : fields) {
                field.setAccessible(true); // [Q] why need to do that?
            }

            T obj = clazz.getConstructor().newInstance();

            for (Field field : fields) {
                try {
                    String fieldName = field.getName(); // camel case (e.g. clientId)
                    // need to convert to client_id
                    String dbFieldName = convertCamelCase2SnakeCase(fieldName);

                    Class fieldType = field.getType();
                    if (fieldType == String.class) {
                        field.set(obj, resultSet.getString(dbFieldName));
                    }
                    else if (fieldType == int.class) {
                        field.setInt(obj, resultSet.getInt(dbFieldName));
                    }
                    else if (fieldType == long.class) {
                        field.setLong(obj, resultSet.getLong(dbFieldName));
                    }
                    else if (fieldType == double.class) {
                        field.setDouble(obj, resultSet.getDouble(dbFieldName));
                    }
                    else if (fieldType == Date.class) { // Date
                        field.set(obj, resultSet.getDate(dbFieldName));
                    }
                    else if (fieldType == Timestamp.class) {
                        field.set(obj, resultSet.getTimestamp(dbFieldName));
                    }
                    else {
                        log.info("not set yet = " + dbFieldName);
                        throw new RuntimeException("Unhandled Exception from Our Code");
                    }
                }
                catch (SQLException e) {
                    log.info("catch exception: " + e.getMessage());
                }
            }
            log.info("done");
            return obj;
        }
        catch (Exception ex) {
            log.info("Exception throw: " + ex.getMessage());
        }
        return null;
    }

    public static String convertCamelCase2SnakeCase(String camelWords) {
        return camelWords.chars().mapToObj(c -> {
            char character = (char) c;
            return Character.isUpperCase(character)
                ? "_" + Character.toLowerCase(character)
                : String.valueOf(character);
        }).collect(StringBuilder::new, StringBuilder::append, StringBuilder::append).toString();
    }
}
