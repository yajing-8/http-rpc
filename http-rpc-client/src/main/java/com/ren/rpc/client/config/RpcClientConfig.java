package com.ren.rpc.client.config;

import com.ren.rpc.client.selector.RandomTransportSelector;
import com.ren.rpc.client.selector.TransportSelector;
import com.ren.rpc.protocol.Point;
import com.ren.rpc.service.Decoder;
import com.ren.rpc.service.Encoder;
import com.ren.rpc.service.impl.JSONDecoder;
import com.ren.rpc.service.impl.JSONEncoder;
import com.ren.rpc.transport.ClientTransport;
import com.ren.rpc.transport.impl.HttpClientTransport;
import lombok.Data;

import java.util.Arrays;
import java.util.List;

/**
 * @program: http-rpc
 * @Date: 2020/7/27 14:26
 * @Author: Mrs.Ren
 * @Description: RPC客户端配置
 */
@Data
public class RpcClientConfig {
    //客户端网络传输
    private Class<? extends ClientTransport> transportClass;
    //序列化和反序列化
    private Class<? extends Encoder> encoderClass;
    private Class<? extends Decoder> decoderClass;
    //客户端选择器
    private Class<? extends TransportSelector> transportSelectorClass;
    //客户端和服务端连接数量
    private int connectCount;
    //默认的连接信息
    private List<Point> points;

    /**
     * 默认配置
     */
    public RpcClientConfig() {
        this(HttpClientTransport.class, JSONEncoder.class, JSONDecoder.class,
                RandomTransportSelector.class, 1, Arrays.asList(
                        new Point("127.0.0.1", 3000)
                ));
    }

    public RpcClientConfig(Class<? extends ClientTransport> transportClass, Class<? extends Encoder> encoderClass, Class<? extends Decoder> decoderClass, Class<? extends TransportSelector> transportSelectorClass, int connectCount, List<Point> points) {
        this.transportClass = transportClass;
        this.encoderClass = encoderClass;
        this.decoderClass = decoderClass;
        this.transportSelectorClass = transportSelectorClass;
        this.connectCount = connectCount;
        this.points = points;
    }
}
