package com.ren.rpc.transport;

/**
 * @program: http-rpc
 * @Date: 2020/7/20 17:47
 * @Author: Mrs.Ren
 * @Description: 服务端传输接口
 * ① 启动、监听
 * ② 接受请求，进行处理，返回结果
 * ③ 关闭监听
 */
public interface ServerTransport {
    /**
     * 初始化
     *
     * @param port
     * @param handler
     */
    void init(int port, RequestHandler handler);

    /**
     * 启动、监听
     */
    void start();

    /**
     * 关闭监听
     */
    void stop();
}
