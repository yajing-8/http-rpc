package com.ren.rpc.transport;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * @program: http-rpc
 * @Date: 2020/7/20 17:52
 * @Author: Mrs.Ren
 * @Description: 处理网络请求的Handler
 */
public interface RequestHandler {
    /**
     * 接受请求并返回结果
     *
     * @param receive
     * @param response
     */
    void onRequest(InputStream receive, OutputStream response);
}
