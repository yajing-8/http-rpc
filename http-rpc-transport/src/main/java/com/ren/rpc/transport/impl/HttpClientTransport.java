package com.ren.rpc.transport.impl;

import com.ren.rpc.protocol.Point;
import com.ren.rpc.transport.ClientTransport;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * @program: http-rpc
 * @Date: 2020/7/24 10:20
 * @Author: Mrs.Ren
 * @Description: 基于HTTP实现客户端传输
 */
public class HttpClientTransport implements ClientTransport {
    private String url;

    /**
     * 创建连接：是基于http短连接的所以不需要直接连接服务端
     *
     * @param point
     */
    @Override
    public void connect(Point point) {
        this.url = "http://" + point.getHost() + ":" + point.getProt();
    }

    /**
     * 发送请求，并且等待响应
     *
     * @param data
     * @return
     */
    @Override
    public InputStream send(InputStream data) {
        try {
            //创建HttpURLConnection：HttpURLconnection是同步的请求
            HttpURLConnection urlConnection = (HttpURLConnection) new URL(url).openConnection();
            //先设置请求头，需要在建立连接之前设置
            //设置允许写数据
            urlConnection.setDoOutput(true);
            //设置允许读数据
            urlConnection.setDoInput(true);
            //设置不用缓存
            urlConnection.setUseCaches(false);
            //设置请求方式：POST请求
            urlConnection.setRequestMethod("POST");
            //获取连接
            urlConnection.connect();
            //将data数据发送给server
            IOUtils.copy(data, urlConnection.getOutputStream());
            //获取响应码
            int responseCode = urlConnection.getResponseCode();
            //请求成功
            if (responseCode == HttpURLConnection.HTTP_OK) {
                return urlConnection.getInputStream();
            } else {
                return urlConnection.getErrorStream();
            }
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void close() {

    }
}
