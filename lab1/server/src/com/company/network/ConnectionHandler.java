package com.company.network;

import java.io.IOException;
import java.net.Socket;

public class ConnectionHandler implements Runnable{

    private Socket clientSocket;

    @Override
    public void run() {
        try {
            System.out.printf("Client connected from %s:%s\n", clientSocket.getInetAddress(), clientSocket.getPort());
            clientSocket.getOutputStream().write(200);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ConnectionHandler(Socket clientSocket){
        this.clientSocket = clientSocket;
    }
}
