package com.ren.rpc.client.client;

import com.ren.rpc.client.config.RpcClientConfig;
import com.ren.rpc.client.selector.TransportSelector;
import com.ren.rpc.service.Decoder;
import com.ren.rpc.service.Encoder;
import com.ren.rpc.utils.ReflectionUtils;

import java.lang.reflect.Proxy;

/**
 * @program: http-rpc
 * @Date: 2020/7/27 14:36
 * @Author: Mrs.Ren
 * @Description:RPC client
 */
public class RpcClient {
    private RpcClientConfig rpcClientConfig;
    private TransportSelector transportSelector;
    private Encoder encoder;
    private Decoder decoder;

 /*   public RpcClient() {
        this(new RpcClientConfig());
    }*/

    public RpcClient() {
        this.rpcClientConfig = new RpcClientConfig();
        this.encoder = ReflectionUtils.newInstance(this.rpcClientConfig.getEncoderClass());
        this.decoder = ReflectionUtils.newInstance(this.rpcClientConfig.getDecoderClass());
        this.transportSelector = ReflectionUtils.newInstance(this.rpcClientConfig.getTransportSelectorClass());
        this.transportSelector.init(
                this.rpcClientConfig.getPoints(),
                this.rpcClientConfig.getConnectCount(),
                this.rpcClientConfig.getTransportClass()
        );
    }

    public <T> T getProxy(Class<T> clazz) {
        return (T) Proxy.newProxyInstance(this.getClass().getClassLoader(), new Class[]{clazz}, new RemoteInvocationHandler(
                clazz,
                this.encoder,
                this.decoder,
                this.transportSelector
        ));
    }
}
