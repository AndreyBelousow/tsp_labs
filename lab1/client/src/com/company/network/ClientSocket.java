package com.company.network;

import com.company.model.Matrix;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class ClientSocket {

    private int serverPort;
    private InetAddress serverIp;

    private Socket socket;

    private ObjectOutputStream output;
    private ObjectInputStream input;

    public void send(Object o) throws IOException {
            output.writeObject(o);
            output.flush();
    };

    public Object recieve() throws ClassNotFoundException, IOException {
            Object responce = input.readObject();
            return responce;
    }

    //_Constructors________________________________

    public ClientSocket(int serverPort, String serverIp) throws IOException {
        this.serverIp = InetAddress.getByName(serverIp);
        this.serverPort = serverPort;
        socket = new Socket(serverIp, serverPort);
        input = new ObjectInputStream(socket.getInputStream());
        output = new ObjectOutputStream(socket.getOutputStream());
    }
}
