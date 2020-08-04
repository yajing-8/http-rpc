package com.ren.rpc.utils;

import org.junit.Test;

import java.lang.reflect.Method;

import static org.junit.Assert.*;

public class ReflectionUtilsTest {

    @Test
    public void newInstance() {
        ReflectionUtilsTest reflectionUtilsTest = ReflectionUtils.newInstance(ReflectionUtilsTest.class);
        System.out.println(reflectionUtilsTest);
    }

    @Test
    public void getPublicMethods() {
        Method[] publicMethods = ReflectionUtils.getPublicMethods(ReflectionUtilsTest.class);
        for (Method m :publicMethods) {
            System.out.println(m.getName());
        }
    }

    @Test
    public void invoke() {
        ReflectionUtilsTest reflectionUtilsTest = ReflectionUtils.newInstance(ReflectionUtilsTest.class);
        Method[] publicMethods = ReflectionUtils.getPublicMethods(ReflectionUtilsTest.class);
        for (Method m :publicMethods) {
            if (m.getName().equalsIgnoreCase("getName")){
                ReflectionUtils.invoke(reflectionUtilsTest,m,new String[]{"张三"});
            }
        }
    }

    public String getName(String name) {
        return name;
    }
}