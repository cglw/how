package com.hornen.storage;

/**
 * Created by Hornen on 15/9/29.
 */
public class Toolkit {

    public static boolean isBasicValueObject(Object obj) {
        return (obj instanceof Short) || (obj instanceof Integer) || (obj instanceof Long) || (obj instanceof Float) || (obj instanceof Double) || (obj instanceof Boolean) || (obj instanceof Byte);
    }

    public static <X> boolean isBasicValueClass(Class<X> clazz) {
        return clazz == Short.class || clazz == Short.TYPE || clazz == Integer.class || clazz == Integer.TYPE || clazz == Long.class || clazz == Long.TYPE || clazz == Float.class || clazz == Float.TYPE || clazz == Double.class || clazz == Double.TYPE || clazz == Boolean.class || clazz == Boolean.TYPE || clazz == Byte.class || clazz == Byte.TYPE;
    }
}
