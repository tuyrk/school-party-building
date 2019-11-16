package com.cdutcm.party.utils;

import com.cdutcm.party.dataobject.User;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * User: 涂元坤
 * Mail: 766564616@qq.com
 * Create: 2018/3/28 17:48 星期三
 * Description:
 * 類的拷貝，如果屬性為空不拷貝該屬性，反之則反
 */
public class CopyClassUtils {
    public static Object copyFieldValues(Object target, Object source) {
        Field[] fields = source.getClass().getDeclaredFields();
        String[] fieldNames = getFieldNames(fields);
        for (int i = 0; i < fieldNames.length; i++) {
            Object value = getFieldValueByName(fieldNames[i], source);
            if (value != null) {
                setFieldValueByName(fieldNames[i], target, value,fields[i].getType());
            }
        }
        return target;
    }

    private static String[] getFieldNames(Field[] fields) {
        String[] fieldNames = new String[fields.length];
        for (int i = 0; i < fields.length; i++) {
            fieldNames[i] = fields[i].getName();
        }
        return fieldNames;
    }

    private static Object getFieldValueByName(String fieldName, Object o) {
        Object value = null;
        try {
            String getter = "get" + initStr(fieldName);
            Method method = o.getClass().getMethod(getter);
            value = method.invoke(o);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }

    private static void setFieldValueByName(String fieldName, Object o, Object value, Class<?> type) {
        try {
            String setter = "set" + initStr(fieldName);
            Method method = o.getClass().getMethod(setter, type);
            method.invoke(o, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String initStr(String fieldName) {
        return fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
    }
}
