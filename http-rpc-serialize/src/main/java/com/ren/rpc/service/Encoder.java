package com.ren.rpc.service;

/**
 * @program: http-rpc
 * @Date: 2020/7/20 17:12
 * @Author: Mrs.Ren
 * @Description: 序列化接口
 */
public interface Encoder {
    /**
     * 序列化：将对象转成二进制字节数组
     *
     * @param obj
     * @return
     */
    byte[] encode(Object obj);
}
