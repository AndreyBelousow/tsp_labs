package com.company.network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class ClientSocket {

    private int serverPort;
    private InetAddress serverIp;

    private Socket socket;

    public void send(Object o) throws IOException, ClassNotFoundException {
        try{
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(o);
            oos.flush();
            oos.close();

            int responce = (int) recieve();
            System.out.println(responce);

        } catch (IOException e) {
            throw e;
        }
    }

    public Object recieve() throws ClassNotFoundException, IOException {
        try{
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            Object responce = ois.readObject();
            return responce;
        } catch (IOException e) {
            throw e;
        }
    }

    //_Constructors________________________________

    public ClientSocket(int serverPort, String serverIp) throws IOException {
        this.serverIp = InetAddress.getByName(serverIp);
        this.serverPort = serverPort;
        try {
            socket = new Socket(serverIp, serverPort);
        } catch (IOException e) {
            throw e;
        }
    }
}
