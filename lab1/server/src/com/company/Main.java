package com.company;

import com.company.network.NetworkTools;

public class Main {

    public static final int port = 1488;
    public static final int threads = 10;

    public static void main(String[] args) {

        System.out.println("Server is started\n__________________");

        NetworkTools.startServer(port, threads);
        NetworkTools.startHandleConnections();
    }
}
