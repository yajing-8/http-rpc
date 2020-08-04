package com.ren.rpc.service;

/**
 * @program: http-rpc
 * @Date: 2020/7/20 17:14
 * @Author: Mrs.Ren
 * @Description: 反序列化
 */
public interface Decoder {
    /**
     * 反序列化
     *
     * @param bytes
     * @param clazz
     * @param <T>
     * @return
     */
    <T> T decode(byte[] bytes, Class<T> clazz);
}
