package com.zzx.server;

import java.io.IOException;

public class Server {
    public static void main(String[] args) throws IOException {
        HelloService helloService = new HelloServiceImpl();
        ServicePublisher publisher = new ServicePublisher();
        publisher.publish(helloService,"sayHello",8888);
    }
}
