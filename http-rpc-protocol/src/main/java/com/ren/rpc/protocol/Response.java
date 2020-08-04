package com.ren.rpc.protocol;

import lombok.Data;

/**
 * @program: http-rpc
 * @Date: 2020/7/20 15:34
 * @Author: Mrs.Ren
 * @Description: 表示RPC的返回
 */
@Data
public class Response {
    //服务端返回码：0-成功 非0失败
    private int code = 0;
    //具体的响应消息
    private String message = "ok";
    //返回的数据
    private Object data;
}
