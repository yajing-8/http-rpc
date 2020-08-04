package com.ren.rpc.service.impl;

import com.alibaba.fastjson.JSON;
import com.ren.rpc.service.Decoder;

/**
 * @program: http-rpc
 * @Date: 2020/7/20 17:19
 * @Author: Mrs.Ren
 * @Description: 基于JSON的反序列化:把二进制数组转换成对象
 */
public class JSONDecoder implements Decoder {

    @Override
    public <T> T decode(byte[] bytes, Class<T> clazz) {
        return JSON.parseObject(bytes,clazz);
    }
}
