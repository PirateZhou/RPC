package com.zzx.client;

import com.zzx.common.RPCRequest;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ZTCPTransport {

    private String host;
    private int port;

    public ZTCPTransport(String host, int port) {
        this.host = host;
        this.port = port;
    }

    private Socket getSocket() throws IOException {
        Socket socket = new Socket(host,port);
        return socket;
    }

    public Object send(RPCRequest request) throws IOException {
        Socket socket = getSocket();
        ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
        outputStream.writeObject(request);
        outputStream.flush();
        ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
        try {
            return objectInputStream.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }finally {
            outputStream.close();
            objectInputStream.close();
            socket.close();
        }


    }
}
