package com.ren.rpc.client.selector;

import com.ren.rpc.protocol.Point;
import com.ren.rpc.transport.ClientTransport;

import java.util.List;

/**
 * @program: http-rpc
 * @Date: 2020/7/27 13:48
 * @Author: Mrs.Ren
 * @Description: 表示选择哪个服务去连接（客户端和服务端可以建立多个连接）
 */
public interface TransportSelector {

    /**
     * 初始化selector
     * @param poits 可以连接的server的端点信息
     * @param count client与server建立了多少个连接
     * @param clazz client的实现Class
     */
    void init(List<Point> poits,int count,Class<? extends ClientTransport> clazz);

    /**
     * 选择一个Transport和Server做交互
     * @return 网络client
     */
    ClientTransport selector();

    /**
     * 释放用完的client
     * @param clientTransport
     */
    void realease(ClientTransport clientTransport);

    /**
     * 关闭所有连接
     */
    void close();
}
