package com.ren.rpc.protocol;

import lombok.Data;

/**
 * @program: http-rpc
 * @Date: 2020/7/20 15:31
 * @Author: Mrs.Ren
 * @Description: 表示RPC的一个请求
 */
@Data
public class Request {
    //请求的服务
    private ServerDescriptor serverDescriptor;
    //请求的参数
    private Object[] parameters;
}
