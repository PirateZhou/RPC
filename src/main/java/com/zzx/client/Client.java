package com.zzx.client;

import com.zzx.server.HelloService;

public class Client {
    public static void main(String[] args) {
        System.out.println("111");
        HelloService helloService = ServiceFactory.getService(HelloService.class,"localhost",8888);
        System.out.println(helloService.sayHello("zhouzhixiong"));
    }
}
