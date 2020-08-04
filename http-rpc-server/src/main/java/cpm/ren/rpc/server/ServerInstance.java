package cpm.ren.rpc.server;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.lang.reflect.Method;

/**
 * @program: http-rpc
 * @Date: 2020/7/24 13:57
 * @Author: Mrs.Ren
 * @Description: 表示暴露服务的一个具体实例
 */
@Data
@AllArgsConstructor
public class ServerInstance {
    //服务由哪个对象提供
    private Object targect;
    //对象的哪个方法
    private Method method;
}
