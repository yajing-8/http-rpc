package com.ren.rpc.transport.impl;

import com.ren.rpc.transport.RequestHandler;
import com.ren.rpc.transport.ServerTransport;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @program: http-rpc
 * @Date: 2020/7/24 11:20
 * @Author: Mrs.Ren
 * @Description: 基于HTTP实现服务端传输
 *
 * jetty 的执行流程：
 * ① 当Jetty 接受到一个请求时，Jetty 就把这个请求交给在 Server 中注册的代理 Handler 去执行，
 *   如何执行你注册的 Handler同样由你去规定，Jetty 要做的就是调用你注册的第一个 Handler 的
 *   handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) 方法，接下去要怎么做，完全由你决定。
 * ②创建一个 ServletContextHandler 并给这个 Handler 添加一个 Servlet，这里的 ServletHolder 是 Servlet 的一个装饰类，
 * 它十分类似于 Tomcat 中的 StandardWrapper。
 */
@Slf4j
public class HttpServerTransport implements ServerTransport {
    private RequestHandler requestHandler;
    //声明netty 服务
    private Server server;

    @Override
    public void init(int port, RequestHandler handler) {
        this.requestHandler = handler;
        this.server = new Server(port);

        //接受请求:ServletContextHandler作为一个半成熟的处理器，主要对外包装很多接口，例如添加Filter、添加Servlet、安全相关，.
        ServletContextHandler ctx = new ServletContextHandler();
        server.setHandler(ctx);

        //处理请求：ServletHolder是 Jetty java在处理请求时的一个抽象
        ServletHolder servletHolder = new ServletHolder(new RequestServlet());
        // "/*"：处理所有请求
        ctx.addServlet(servletHolder, "/*");
    }

    /**
     * 定义处理请求的Servlet
     */
    class RequestServlet extends HttpServlet {
        /**
         * 重写处理post请求
         *
         * @param req
         * @param resp
         * @throws ServletException
         * @throws IOException
         */
        @Override
        protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            log.info("client connect");
            //获取请求数据
            InputStream inputStream = req.getInputStream();
            //响应处理结果数据
            OutputStream outputStream = resp.getOutputStream();
            if (null != requestHandler) {
                requestHandler.onRequest(inputStream, outputStream);
            }
            outputStream.flush();
        }
    }

    /**
     * 启动
     */
    @Override
    public void start() {
        try {
            server.start();
            //启动后防止立马关闭
            server.join();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    /**
     * 关闭监听
     */
    @Override
    public void stop() {
        try {
            server.stop();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }
}
