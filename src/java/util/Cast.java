/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import enums.Gender;
import enums.Role;
import java.lang.reflect.AnnotatedArrayType;
import java.lang.reflect.AnnotatedType;
import java.lang.reflect.AnnotatedTypeVariable;
import java.lang.reflect.AnnotatedWildcardType;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hoang hung
 */
public class Cast {
    
    private static final String MODEL_MODULE = "model.";
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
    
    public static Object cast(String value) {
        String className = MODEL_MODULE + value.split("\\{")[0];
        Object o = null;
        
        try {
            Class<?> clazz = Class.forName(className);
            Field[] fields = clazz.getDeclaredFields();
            Object[] fieldsValue = getFieldsValue(fields, value);
            Class<?>[] parameterTypes = getParameterTypes(fields);
            
            o = clazz.getConstructor(parameterTypes).newInstance(fieldsValue);
        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | InvocationTargetException | IllegalAccessException | IllegalArgumentException  e) {
            Logger.getLogger(Cookiez.class.getName()).log(Level.SEVERE, null, e);
        }
        
        return o;
    }
    
    private static Class<?>[] getParameterTypes(Field[] fields) {
        Class<?>[] parameterTypes = new Class<?>[fields.length];
        
        for (int i = 0; i < fields.length; i++) {
            parameterTypes[i] = Cast.castAnnotatedTypeToClass(fields[i].getAnnotatedType());
        }
        
        return parameterTypes;
    }
    
    private static Object[] getFieldsValue(Field[] fields, String input) {
        Object[] fieldsValue = new Object[fields.length];
        String trimmedInput = input.substring(input.indexOf("{") + 1, input.indexOf("}"));
        String[] keyValuePairs = trimmedInput.split(", ");
        
        for (int i = 0; i < fields.length; i++) {
            String value = keyValuePairs[i].split("=")[1];
            fieldsValue[i] = castValue(fields[i], value);
        }
        
        return fieldsValue;
    }
    
    private static Class<?> castAnnotatedTypeToClass(AnnotatedType annotatedType) {
        Type type = annotatedType.getType();
        
        if (type instanceof Class<?> aClass) {
            return aClass;
        } else if (type instanceof ParameterizedType parameterizedType) {
            return (Class<?>) parameterizedType.getRawType();
        } else if (type instanceof AnnotatedArrayType annotatedArrayType) {
            return (Class<?>) annotatedArrayType.getAnnotatedGenericComponentType().getType();
        } else if (type instanceof AnnotatedTypeVariable atv) {
            return Object.class;
        } else if (type instanceof AnnotatedWildcardType awt) {
            return Object.class;
        } else {
            throw new IllegalArgumentException("Unsupported AnnotatedType: " + annotatedType);
        }
    }
    
    private static Object castValue(Field field, String valueStr) {
        Class<?> fieldType = castAnnotatedTypeToClass(field.getAnnotatedType());
        
        if (fieldType == int.class || fieldType == Integer.class) {
            return Integer.valueOf(valueStr);
        } else if (fieldType == long.class || fieldType == Long.class) {
            return Long.valueOf(valueStr);
        } else if (fieldType == float.class || fieldType == Float.class) {
            return Float.valueOf(valueStr);
        } else if (fieldType == double.class || fieldType == Double.class) {
            return Double.valueOf(valueStr);
        } else if (fieldType == boolean.class || fieldType == Boolean.class) {
            return Boolean.valueOf(valueStr);
        } else if (fieldType == Date.class) {
            try {
                return DATE_FORMAT.parse(valueStr);
            } catch (ParseException e) {
                Logger.getLogger(Cast.class.getName()).log(Level.SEVERE, null, e);
                return null;
            }
        } else if (fieldType == Gender.class) {
            return Gender.valueOf(valueStr);
        } else if (fieldType == Role.class) {
            return Role.valueOf(valueStr);
        } else {
            return valueStr;
        }
    }
}
