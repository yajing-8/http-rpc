package com.ren.rpc.protocol;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @program: http-rpc
 * @Date: 2020/7/20 15:20
 * @Author: Mrs.Ren
 * @Description: 表示网络传输的端点
 */
@Data
@AllArgsConstructor
public class Point {
    //ip地址
    private String host;
    //端口号
    private int prot;
}
