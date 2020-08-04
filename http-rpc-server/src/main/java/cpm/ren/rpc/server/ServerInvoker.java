package cpm.ren.rpc.server;

import com.ren.rpc.protocol.Request;
import com.ren.rpc.utils.ReflectionUtils;

/**
 * @program: http-rpc
 * @Date: 2020/7/24 16:06
 * @Author: Mrs.Ren
 * @Description: 调用具体的服务
 */
public class ServerInvoker {

    /**
     * 调用具体的服务方法
     * @param instance 具体的服务方法实例
     * @param request 该方法的请求
     * @return
     */
    public Object invoke(ServerInstance instance, Request request) {
        return ReflectionUtils.invoke(instance.getTargect(), instance.getMethod(), request.getParameters());
    }
}
