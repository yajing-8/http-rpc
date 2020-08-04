package com.ren.rpc.excemple;

import com.ren.rpc.excemple.service.UserService;
import com.ren.rpc.excemple.service.impl.UserServiceImpl;
import cpm.ren.rpc.server.RPCServer;

/**
 * @program: http-rpc
 * @Date: 2020/7/27 15:30
 * @Author: Mrs.Ren
 * @Description: 服务端
 */
public class Server {

    public static void main(String[] args) {
        RPCServer rpcServer = new RPCServer();
        //注册用户服务
        rpcServer.register(UserService.class, new UserServiceImpl());
        rpcServer.start();
    }
}
