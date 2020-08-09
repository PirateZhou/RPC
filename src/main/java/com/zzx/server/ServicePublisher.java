package com.zzx.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServicePublisher {

    private static ExecutorService executorService = Executors.newFixedThreadPool(1);
    public void publish(Object service,String method,int port) throws IOException {
        ServerSocket serverSocket = new ServerSocket(port);
        while(true){
            Socket socket = serverSocket.accept();
            executorService.submit(new ProcessHandler(service,socket));
        }

    }
}
