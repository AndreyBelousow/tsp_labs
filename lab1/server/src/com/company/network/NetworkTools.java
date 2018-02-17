package com.company.network;

import java.io.IOException;

public class NetworkTools {

    private static MutiThreadedServer server;

    public static void startServer(int port, int maxThreadsNumber) {
        try {
            server = new MutiThreadedServer(port, maxThreadsNumber);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void startHandleConnections(){
        server.startHandleConnections();
    }
}
