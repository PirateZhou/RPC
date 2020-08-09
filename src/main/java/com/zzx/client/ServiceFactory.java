package com.zzx.client;

import java.lang.reflect.Proxy;

public class ServiceFactory {
    public  static <T> T getService(Class<T> service, String hostname, int port) {
        return (T) Proxy.newProxyInstance(service.getClassLoader(),new Class[]{service},new RPCInvocationHandler(hostname, port));
    }
}
