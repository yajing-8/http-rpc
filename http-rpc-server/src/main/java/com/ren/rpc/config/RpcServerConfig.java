package com.ren.rpc.config;

import com.ren.rpc.service.Decoder;
import com.ren.rpc.service.Encoder;
import com.ren.rpc.service.impl.JSONDecoder;
import com.ren.rpc.service.impl.JSONEncoder;
import com.ren.rpc.transport.ServerTransport;
import com.ren.rpc.transport.impl.HttpServerTransport;
import lombok.Data;

/**
 * @program: http-rpc
 * @Date: 2020/7/24 13:45
 * @Author: Mrs.Ren
 * @Description: 服务端配置类
 * 配置：①使用哪个网络模块
 * ②使用哪个序列化实现
 * ③启动后监听哪个端口
 */
@Data
public class RpcServerConfig {
    /**
     * 配置用哪个网络模块
     */
    private Class<? extends ServerTransport> serverTransportClass;
    /**
     * 配置使用什么序列化
     */
    private Class<? extends Encoder> encoderClass;
    private Class<? extends Decoder> decoderClass;
    /**
     * 配置监听的端口
     */
    private int port;

    /**
     * 默认配置
     */
    public RpcServerConfig() {
        this(HttpServerTransport.class, JSONEncoder.class, JSONDecoder.class, 3000);
    }

    public RpcServerConfig(Class<? extends ServerTransport> serverTransportClass, Class<? extends Encoder> encoderClass, Class<? extends Decoder> decoderClass, int port) {
        this.serverTransportClass = serverTransportClass;
        this.encoderClass = encoderClass;
        this.decoderClass = decoderClass;
        this.port = port;
    }
}
