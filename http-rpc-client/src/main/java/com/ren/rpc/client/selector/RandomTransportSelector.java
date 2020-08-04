package com.ren.rpc.client.selector;

import com.ren.rpc.protocol.Point;
import com.ren.rpc.transport.ClientTransport;
import com.ren.rpc.transport.impl.HttpClientTransport;
import com.ren.rpc.utils.ReflectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @program: http-rpc
 * @Date: 2020/7/27 14:01
 * @Author: Mrs.Ren
 * @Description: 随机获取方式：从客户端和服务端的所有连接中随机获取一个
 */
public class RandomTransportSelector implements TransportSelector {
    //已连接好的客户端
    private List<ClientTransport> clients;

    public RandomTransportSelector() {
        clients = new ArrayList<>();
    }

    /**
     * 初始化selector
     *
     * @param poits 可以连接的server的端点信息
     * @param count client与server建立了多少个连接
     * @param clazz client的实现Class
     */
    @Override
    public synchronized void init(List<Point> poits, int count, Class<? extends ClientTransport> clazz) {
        //count > = 1
        count = Math.max(count, 1);
        for (Point point : poits) {
            for (int i = 0; i < count; i++) {
                System.out.println(point.getHost() + ":" + point.getProt());
                ClientTransport clientTransport = ReflectionUtils.newInstance(HttpClientTransport.class);
                //客户端与服务端建立好连接
                clientTransport.connect(point);
                clients.add(clientTransport);
            }
        }
    }

    /**
     * 随机从已建立好的客户端和服务端的所有连接中选择一个
     *
     * @return
     */
    @Override
    public synchronized ClientTransport selector() {
        int i = new Random().nextInt(clients.size());
        return clients.remove(i);
    }

    /**
     * 释放
     *
     * @param clientTransport
     */
    @Override
    public synchronized void realease(ClientTransport clientTransport) {
        clients.add(clientTransport);
    }

    /**
     * 关闭
     */
    @Override
    public synchronized void close() {
       /* for (ClientTransport clientTransport : clients) {
            clientTransport.close();
        }*/
        clients.clear();
    }
}
