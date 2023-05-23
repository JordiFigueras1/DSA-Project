package edu.upc.dsa.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.SQLException;

public class ObjectHelper {
    public static String[] getFields(Object entity) {

        Class theClass = entity.getClass();

        Field[] fields = theClass.getDeclaredFields();

        String[] sFields = new String[fields.length];
        int i=0;

        for (Field f: fields) sFields[i++]=f.getName();

        return sFields;

    }


    public static void setter(Object object, String property, Object value) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        // Method // invoke
        char[] arr = property.toCharArray();
        arr[0] = Character.toUpperCase(arr[0]);
        String newProperty = new String(arr);

        String method = "set" + newProperty;

        Class cls = object.getClass();

        try {

            Class[] carr = new Class[1];
            carr[0] = value.getClass();

            Method mth = cls.getMethod(method, carr);
            mth.invoke(object, value);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static Object getter(Object object, String property) throws NoSuchMethodException {
        try {
            char[] arr = property.toCharArray();
            arr[0] = Character.toUpperCase(arr[0]);
            String newProperty = new String(arr);

            String method = "get" + newProperty;

            Class cls = object.getClass();
            Method mth = cls.getMethod(method, null);

            return mth.invoke(object);

        }  catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

