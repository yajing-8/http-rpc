package com.ren.rpc.protocol;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Objects;

/**
 * @program: http-rpc
 * @Date: 2020/7/20 15:23
 * @Author: Mrs.Ren
 * @Description: 表示服务（方法）
 */
@Data
@NoArgsConstructor
public class ServerDescriptor {
    //接口的 Class 对象
    private String className;
    //方法名
    private String methodName;
    //参数类型
    private String[] parameterTypes;
    //返回的类型
    private String returnType;


    /**
     * 获取服务描述类
     *
     * @param clazz
     * @param method
     * @return
     */
    public static ServerDescriptor from(Class clazz, Method method) {
        ServerDescriptor serverDescriptor = new ServerDescriptor();
        serverDescriptor.setClassName(clazz.getName());
        serverDescriptor.setMethodName(method.getName());
        serverDescriptor.setReturnType(method.getReturnType().getName());
        Class[] parameterClass = method.getParameterTypes();
        String[] parameterTypes = new String[parameterClass.length];
        for (int i = 0; i < parameterClass.length; i++) {
            parameterTypes[i] = parameterClass[i].getName();
        }
        serverDescriptor.setParameterTypes(parameterTypes);
        return serverDescriptor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ServerDescriptor that = (ServerDescriptor) o;
        return that.toString().equals(that.toString());
    }

    @Override
    public String toString() {
        return "ServerDescriptor{" +
                "className='" + className + '\'' +
                ", methodName='" + methodName + '\'' +
                ", parameterTypes=" + Arrays.toString(parameterTypes) +
                ", returnType='" + returnType + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(className, methodName, returnType);
        result = 31 * result + Arrays.hashCode(parameterTypes);
        return result;
    }
}
