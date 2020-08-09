package com.zzx.server;

import com.zzx.common.RPCRequest;
import com.zzx.common.RPCResponse;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;

public class ProcessHandler implements  Runnable {

    private Object service;
    private Socket socket;
    public ProcessHandler(Object service, Socket socket) {
        this.service=service;
        this.socket=socket;
    }

    /**
     * 接受消息
     * 反射计算
     * 返回消息
     */
    public void run() {
        try {
            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
            RPCRequest rpcRequest = (RPCRequest) inputStream.readObject();
            Object result =invoke(rpcRequest);
            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
            outputStream.writeObject(result);
            outputStream.flush();
            outputStream.close();
            inputStream.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    private Object invoke(RPCRequest rpcRequest) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Object[] parameters=rpcRequest.getParameters();
        Class[] types = new Class[parameters.length];
        for(int i=0;i<parameters.length;i++){
            types[i]=parameters[i].getClass();
        }
        Method method = service.getClass().getMethod(rpcRequest.getMethodName(),types);
        return method.invoke(service,parameters);
    }


}
