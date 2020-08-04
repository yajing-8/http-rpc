package cpm.ren.rpc.server;


import com.ren.rpc.protocol.Request;
import com.ren.rpc.protocol.ServerDescriptor;
import com.ren.rpc.utils.ReflectionUtils;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @program: http-rpc
 * @Date: 2020/7/24 14:02
 * @Author: Mrs.Ren
 * @Description: 管理RPC暴露的服务
 */
@Slf4j
public class ServerManager {
    private Map<ServerDescriptor, ServerInstance> serversMap;

    public ServerManager() {
        this.serversMap = new ConcurrentHashMap<>();
    }

    /**
     * 注册服务：把interfaceClass的所有方法当成一个服务注册到serversMap中
     * 获取到接口的所有服务，然后和bean绑定，形成服务实例然后注册到serversMap中
     *
     * @param interfaceClass 服务接口类Class对象
     * @param bean           服务接口一个实现类实例（单例）
     */
    public <T> void register(Class<T> interfaceClass, T bean) {
        //获取该接口的所有public方法，然后和bean实例绑定注册到服务中心serversMap中
        Method[] methods = ReflectionUtils.getPublicMethods(interfaceClass);
        for (Method method : methods) {
            ServerInstance serverInstance = new ServerInstance(bean, method);
            ServerDescriptor serverDescriptor = ServerDescriptor.from(interfaceClass, method);
            serversMap.put(serverDescriptor, serverInstance);
            log.info("register service {}{} " + serverDescriptor.getClassName(), serverDescriptor.getMethodName());
        }
    }

    /**
     * 从注册中心获取服务
     *
     * @param request 自定义的RPC的一个请求
     * @return
     */
    public ServerInstance lookUp(Request request) {
        //h获取请求的服务key
        ServerDescriptor serverDescriptor = request.getServerDescriptor();
        ServerInstance serverInstance = serversMap.get(serverDescriptor);
        return serverInstance;
    }
}
