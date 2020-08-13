# http-rpc
基于HTTP实现的RPC框架


### 项目介绍

http-rpc 是基于http协议实现的一个简单的RPC框架，主要分协议模块、序列化模块、网络传输模块、客户端模块、服务端模块，各模块职责及依赖关系
详见[这里](https://mp.weixin.qq.com/s/DPxnVCQNwtWwNcbY9R2c8Q)。


### 开发环境配置

1. 下载代码: `git clone https://github.com/ZhiyanRen/http-rpc`
2. 构建

    > - `mvn clean package`  
3. 运行     
* 运行`http-rpc-excemple-provider Server`
   `http-rpc-excemple-provider`是一个标准的Java项目, 可以在任何java IDE中运行它
* 运行`http-rpc-excemple-consumer OrderClient`
  `http-rpc-excemple-consumer`是一个标准的Java项目, 可以在任何java IDE中运行它
