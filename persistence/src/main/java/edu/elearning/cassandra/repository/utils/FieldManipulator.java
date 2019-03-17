package edu.elearning.cassandra.repository.utils;

import edu.elearning.se.AsteriModel;

import java.lang.reflect.Field;

public class FieldManipulator {

    public static Object getFieldValue(AsteriModel model, String fieldName) {
        try {
            Field field = model.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            return field.get(model);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            return null;
        }

    }
}
