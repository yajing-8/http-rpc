package com.ren.rpc.transport;

import com.ren.rpc.protocol.Point;

import java.io.InputStream;

/**
 * @program: http-rpc
 * @Date: 2020/7/20 17:33
 * @Author: Mrs.Ren
 * @Description: 客户端传输接口
 * ① 创建连接
 * ② 发送数据，并且等待响应
 * ③ 关闭连接
 */
public interface ClientTransport {

    /**
     * 创建连接
     *
     * @param point
     */
    void connect(Point point);

    /**
     * 发送数据并等待响应
     *
     * @param data
     * @return
     */
    InputStream send(InputStream data);

    /**
     * 关闭连接
     */
    void close();
}
