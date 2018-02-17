package com.company.network;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ConnectionHandler implements Runnable{

    private Socket clientSocket;

    @Override
    public void run() {
        try {
            System.out.printf("Client connected from %s:%s\n", clientSocket.getInetAddress(), clientSocket.getPort());
            ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream());
            oos.writeObject("kek");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //_Constructors________________________________

    public ConnectionHandler(Socket clientSocket){
        this.clientSocket = clientSocket;
    }
}
