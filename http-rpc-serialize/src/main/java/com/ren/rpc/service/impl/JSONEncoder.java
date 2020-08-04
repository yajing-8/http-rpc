package com.ren.rpc.service.impl;

import com.alibaba.fastjson.JSON;
import com.ren.rpc.service.Encoder;

/**
 * @program: http-rpc
 * @Date: 2020/7/20 17:18
 * @Author: Mrs.Ren
 * @Description: 基于JSON的序列化：把对象转换成二进制数组
 */
public class JSONEncoder implements Encoder {
    /**
     * 将对象序列化成字节数组
     *
     * @param obj
     * @return
     */
    @Override
    public byte[] encode(Object obj) {
        return JSON.toJSONBytes(obj);
    }
}
