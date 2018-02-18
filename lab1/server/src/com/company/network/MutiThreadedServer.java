package com.company.network;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MutiThreadedServer {

    private ServerSocket server;

    private static ExecutorService executeIt;

    public MutiThreadedServer(int port, int maxThreadsNumber) throws IOException {

        try {
            server = new ServerSocket(port);
            executeIt = Executors.newFixedThreadPool(maxThreadsNumber);
        } catch (IOException e) {
            throw e;
        }
    }

    public void startHandleConnections(){
        while (!server.isClosed()){
            try {
                Socket client = server.accept();
                executeIt.execute(new ConnectionHandler(client));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
