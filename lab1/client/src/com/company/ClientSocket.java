package com.company;

import java.io.IOException;
import java.net.Socket;

public class ClientSocket {

    private int serverPort = 1337;
    private String serverIp = "127.0.0.1";

    private Socket socket;

    public void send(Object o){
        //TODO implementation
    }

    public Object recieve(){
        //TODO implementation
        return new Object();
    }

    //_Constructors________________________________

    public ClientSocket(int serverPort, String serverIp) throws IOException {
        this.serverIp = serverIp;
        this.serverPort = serverPort;
        try {
            socket = new Socket(serverIp, serverPort);
        } catch (IOException e) {
            //e.printStackTrace();
            throw e;
        }
    }
}
