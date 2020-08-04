package com.ren.rpc.utils;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: http-rpc
 * @Date: 2020/7/20 15:57
 * @Author: Mrs.Ren
 * @Description: 反射的工具类
 */
public class ReflectionUtils {

    /**
     * 根据Class创建对象
     *
     * @param clazz 待创建对象的类
     * @param <T>   对象类型
     * @return 创建好的对象
     */
    public static <T> T newInstance(Class<T> clazz) {
        try {
            return clazz.newInstance();
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    /**
     * 获取某个类的所有public方法
     *
     * @param clazz
     * @return
     */
    public static Method[] getPublicMethods(Class clazz) {
        List<Method> methodList = new ArrayList<>();
        //获取类中所有权限类型的方法（不包括父类）
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            //获取所有public方法
            if (Modifier.isPublic(method.getModifiers())) {
                methodList.add(method);
            }
        }
        return methodList.toArray(new Method[0]);
    }

    /**
     * 调用某个对象的某个方法
     *
     * @param instance 被调用方法的所属对象（如果是静态方法属于类，则传null）
     * @param method   被调用的方法
     * @param args     方法的参数
     * @return 返回的结果
     */
    public static Object invoke(Object instance, Method method, Object... args) {
        try {
            return method.invoke(instance, args);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }
}
