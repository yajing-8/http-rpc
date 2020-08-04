package cpm.ren.rpc.server;

import com.ren.rpc.config.RpcServerConfig;
import com.ren.rpc.protocol.Request;
import com.ren.rpc.protocol.Response;
import com.ren.rpc.service.Decoder;
import com.ren.rpc.service.Encoder;
import com.ren.rpc.transport.RequestHandler;
import com.ren.rpc.transport.ServerTransport;
import com.ren.rpc.utils.ReflectionUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @program: http-rpc
 * @Date: 2020/7/27 11:25
 * @Author: Mrs.Ren
 * @Description: RPC服务
 */
@Slf4j
public class RPCServer {
    //服务配置
    private RpcServerConfig serverConfig;
    //网络模块
    private ServerTransport serverTransport;
    //序列化模块
    private Encoder encoder;
    private Decoder decoder;
    //服务管理
    private ServerManager serverManager;
    //服务调用
    private ServerInvoker serverInvoker;

    /**
     * 使用默认配置初始化
     */
    public RPCServer() {
        //使用服务端默认配置
        this.serverConfig = new RpcServerConfig();
        this.serverTransport = ReflectionUtils.newInstance(serverConfig.getServerTransportClass());
        //初始化服务端网络请求
        serverTransport.init(serverConfig.getPort(), this.requestHandler);
        //初始化序列化和反序列化
        this.encoder = ReflectionUtils.newInstance(serverConfig.getEncoderClass());
        this.decoder = ReflectionUtils.newInstance(serverConfig.getDecoderClass());
        //初始化服务管理
        this.serverManager = new ServerManager();
        //初始化服务调用
        this.serverInvoker = new ServerInvoker();
    }

    //请求 Handler
    private RequestHandler requestHandler = new RequestHandler() {
        @Override
        public void onRequest(InputStream reqData, OutputStream respData) {
            //TODO
            Response response = new Response();
            try {
                //先读取到请求的二进制流
                byte[] bytes = IOUtils.readFully(reqData, reqData.available());
                Request request = decoder.decode(bytes, Request.class);
                log.info("Request {}", request);
                //使用serverInvoker调用服务
                ServerInstance serverInstance = serverManager.lookUp(request);
                Object resultObj = serverInvoker.invoke(serverInstance, request);
                //通过respData将结果返回
                response.setData(resultObj);
            } catch (IOException e) {
                log.warn(e.getMessage(), e);
                response.setCode(1);
                response.setMessage("Resover Error" + e.getClass().getName());
            } finally {
                try {
                    byte[] bytes = encoder.encode(response);
                    respData.write(bytes);
                    log.info("response client");
                } catch (IOException e) {
                    e.printStackTrace();
                    log.warn(e.getMessage());
                }
            }
        }
    };

    /**
     * 注册服务
     *
     * @param interfaceClass 服务Class对象
     * @param bean           服务实例
     * @param <T>
     */
    public <T> void register(Class<T> interfaceClass, T bean) {
        serverManager.register(interfaceClass, bean);
    }

    /**
     * 启动服务
     */
    public void start() {
        this.serverTransport.start();
    }

    /**
     * 结束服务
     */
    public void stop() {
        this.serverTransport.stop();
    }


}
