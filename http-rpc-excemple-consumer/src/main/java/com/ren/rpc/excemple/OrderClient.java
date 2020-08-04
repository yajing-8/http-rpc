package com.ren.rpc.excemple;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.ren.rpc.client.client.RpcClient;
import com.ren.rpc.excemple.bean.UserAddress;
import com.ren.rpc.excemple.service.UserService;

import java.util.List;

/**
 * @program: http-rpc
 * @Date: 2020/7/27 15:29
 * @Author: Mrs.Ren
 * @Description: 客户端
 */
public class OrderClient {

    public static void main(String[] args) {
        RpcClient rpcClient = new RpcClient();
        UserService userService = rpcClient.getProxy(UserService.class);
        //1、查询用户的收货地址
        List<UserAddress> addressList = JSONArray.parseArray(JSON.toJSONString(userService.getUserAddressList("1")), UserAddress.class);
        userService.getUserAddressList("1");
        System.out.println("获取的用户地址列表：" + addressList);
        for (UserAddress userAddress : addressList) {
            System.out.println(userAddress.getUserAddress());
        }
    }
}
