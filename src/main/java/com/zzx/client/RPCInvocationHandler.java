package com.zzx.client;

import com.zzx.common.RPCRequest;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class RPCInvocationHandler implements InvocationHandler {

    private String hostname;
    private int port;

    public RPCInvocationHandler(String hostname, int port) {
        this.hostname = hostname;
        this.port = port;
    }

    /**
     * 先建立rpc对象
     * 然后通过socket传出去
     *
     * @param proxy
     * @param method
     * @param args
     * @return
     * @throws Throwable
     */
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        RPCRequest rpcRequest = new RPCRequest();
        rpcRequest.setClazzName(method.getDeclaringClass().getName());
        rpcRequest.setMethodName(method.getName());
        rpcRequest.setParameters(args);
        ZTCPTransport ztcpTransport = new ZTCPTransport(hostname,port);
        return ztcpTransport.send(rpcRequest);
    }
}
