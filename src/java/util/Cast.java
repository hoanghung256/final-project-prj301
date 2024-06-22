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
    
    private static final String modelModule = "model.";
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
    
    public static Object cast(String value) {
        String className = modelModule + value.split("\\{")[0];
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
        
        if (type instanceof Class<?>) {
            return (Class<?>) type;
        } else if (type instanceof ParameterizedType) {
            return (Class<?>) ((ParameterizedType) type).getRawType();
        } else if (type instanceof AnnotatedArrayType) {
            return (Class<?>) ((AnnotatedArrayType) type).getAnnotatedGenericComponentType().getType();
        } else if (type instanceof AnnotatedTypeVariable) {
            AnnotatedTypeVariable atv = (AnnotatedTypeVariable) type;
            return Object.class;
        } else if (type instanceof AnnotatedWildcardType) {
            AnnotatedWildcardType awt = (AnnotatedWildcardType) type;
            return Object.class;
        } else {
            throw new IllegalArgumentException("Unsupported AnnotatedType: " + annotatedType);
        }
    }
    
    private static Object castValue(Field field, String valueStr) {
        Class<?> fieldType = castAnnotatedTypeToClass(field.getAnnotatedType());
        
        if (fieldType == int.class || fieldType == Integer.class) {
            return Integer.parseInt(valueStr);
        } else if (fieldType == long.class || fieldType == Long.class) {
            return Long.parseLong(valueStr);
        } else if (fieldType == float.class || fieldType == Float.class) {
            return Float.parseFloat(valueStr);
        } else if (fieldType == double.class || fieldType == Double.class) {
            return Double.parseDouble(valueStr);
        } else if (fieldType == boolean.class || fieldType == Boolean.class) {
            return Boolean.parseBoolean(valueStr);
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
//    public static void main(String[] args) {
//        Object o = cast("User{id=1, username=hoang hung, password=123, email=hung@gmail.com, phone=0123456576, gender=MALE, dob=Sat Jun 22 16:53:30 ICT 2024, joinAt=Sat Jun 22 16:53:30 ICT 2024, avatarUrl=url/url/image/jsjs.png, address=My home address, role=CUSTOMER, balance=10000}");
////        User u = new User(1, "hoang hung", "123", "hung@gmail.com", "0123456576", Gender.MALE, new Date(), new Date(), "url/url/image/jsjs.png", "My home address", Role.CUSTOMER, 10000);
//        System.out.println(o.toString());
//    }
}
