package com.ren.rpc.service.impl;

import org.junit.Test;

public class JSONDecoderTest {

    @Test
    public void decode() {
        TestBean bean = new TestBean();
        bean.setHobby("唱歌");
        bean.setName("张三");
        JSONEncoder encoder = new JSONEncoder();
        byte[] encode = encoder.encode(bean);

        JSONDecoder decoder = new JSONDecoder();
        TestBean testBean = decoder.decode(encode, TestBean.class);
        System.out.println(testBean);
    }
}