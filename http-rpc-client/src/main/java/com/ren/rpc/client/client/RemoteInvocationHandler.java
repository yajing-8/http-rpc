package com.ren.rpc.client.client;

import com.ren.rpc.client.selector.TransportSelector;
import com.ren.rpc.protocol.Request;
import com.ren.rpc.protocol.Response;
import com.ren.rpc.protocol.ServerDescriptor;
import com.ren.rpc.service.Decoder;
import com.ren.rpc.service.Encoder;
import com.ren.rpc.transport.ClientTransport;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @program: http-rpc
 * @Date: 2020/7/27 14:49
 * @Author: Mrs.Ren
 * @Description: 调用远程服务的代理类
 */
@Slf4j
public class RemoteInvocationHandler implements InvocationHandler {
    private TransportSelector transportSelector;
    private Encoder encoder;
    private Decoder decoder;
    private Class clazz;

    /**
     * 初始化远程服务
     *
     * @param clazz    远程服务Class对象
     * @param encoder  远程服务的序列化
     * @param decoder  远程服务的反序列化
     * @param selector 远程网络连接
     */
    public RemoteInvocationHandler(Class clazz, Encoder encoder, Decoder decoder, TransportSelector selector) {
        this.clazz = clazz;
        this.decoder = decoder;
        this.encoder = encoder;
        this.transportSelector = selector;
    }

    /**
     * @param proxy
     * @param method
     * @param args
     * @return
     * @throws Throwable ①首先构造一个请求
     *                   ②通过网络把请求对象发给server
     *                   ③等待server的响应，获取返回的结果
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //创建请求
        Request request = new Request();
        request.setServerDescriptor(ServerDescriptor.from(clazz, method));
        request.setParameters(args);
        //调用远程服务
        Response respData = invokeRemote(request);
        if (null == respData || respData.getCode() != 0) {
            //调用失败
            throw new IllegalStateException("fail to invoke remote");
        }
        return respData.getData();
    }

    /**
     * 调用远程服务
     *
     * @param request
     * @return
     */
    public Response invokeRemote(Request request) {
        ClientTransport clientTransport = null;
        Response response = null;
        try {
            clientTransport = transportSelector.selector();
            byte[] outBytes = encoder.encode(request);
            InputStream inputStream = clientTransport.send(new ByteArrayInputStream(outBytes));
            byte[] resuleBytes = IOUtils.readFully(inputStream, inputStream.available());
            response = decoder.decode(resuleBytes, Response.class);
        } catch (Exception e) {
            //出现异常
            log.warn(e.getMessage(), e);
            response = new Response();
            response.setCode(1);
            response.setMessage("server get error:" + e.getClass() + ":" + e.getMessage());
        } finally {
            if (null != clientTransport) {
                transportSelector.realease(clientTransport);
            }
        }
        return response;
    }
}
