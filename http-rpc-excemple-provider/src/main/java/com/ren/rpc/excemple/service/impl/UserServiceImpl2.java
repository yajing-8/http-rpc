package com.ren.rpc.excemple.service.impl;



import com.ren.rpc.excemple.bean.UserAddress;
import com.ren.rpc.excemple.service.UserService;

import java.util.Arrays;
import java.util.List;


/**
 * 服务提供者
 */
public class UserServiceImpl2 implements UserService {

    /**
     * 通过用户ID返回所有的收获地址
     *
     * @param userId
     * @return
     */
    @Override
    public List<UserAddress> getUserAddressList(String userId) {
        System.out.println("请求UserServiceImpl服务。。。new");
       /* try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        UserAddress address1 = new UserAddress(1, "北京市昌平区宏福科技园综合楼3层", "1", "李老师", "010-56253825", "Y");
        UserAddress address2 = new UserAddress(2, "深圳市宝安区西部硅谷大厦B座3层（深圳分校）", "1", "王老师", "010-56253825", "N");
        return Arrays.asList(address1, address2);
    }

}
