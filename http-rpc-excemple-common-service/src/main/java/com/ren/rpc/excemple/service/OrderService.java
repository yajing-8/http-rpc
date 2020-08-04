package com.ren.rpc.excemple.service;


import com.ren.rpc.excemple.bean.UserAddress;

import java.util.List;

public interface OrderService {

    /**
     * 初始化订单
     *
     * @param userId
     */
    public List<UserAddress> initOrder(String userId);

}
