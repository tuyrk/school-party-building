package com.cdutcm.party.utils;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * User: 涂元坤
 * Mail: 766564616@qq.com
 * Create: 2018/3/20 21:35 星期二
 * Description:
 * 获取文章内容简介
 */
public class SummaryUtils<T> {

    private Field[] fields;

    private static final int length = 20;

    public List<T> getSummary(List<T> objectList) {
        Class clazz = objectList.get(0).getClass();
        // 获取当前加载的实体类中所有属性（字段）
        fields = clazz.getDeclaredFields();

        String content;
        try {
            T obj;
            for (int i = 0; i < objectList.size(); i++) {
                obj = objectList.get(i);
                for (Field field : fields) {
                    if ("content".equals(field.getName()) || "description".equals(field.getName())) {
                        content = field.get(obj).toString();
                        if (content.length() > length) {
                            field.set(obj, content.substring(0, length));
                        }
                    }
                }
                objectList.set(i, obj);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return objectList;
    }
}
