package com.company;

import com.company.model.Matrix;

import java.io.IOException;

public class Main {

    public static final int serverPort = 1337;
    public static final String serverIp = "127.0.0.1";

    public static void main(String[] args) {

        System.out.println("Client is started\n__________________");

        Tools tools = new Tools();

        Matrix a = tools.askUserForInputFile("first matrix");
        Matrix b = tools.askUserForInputFile("second matrix");

        tools.connectToServer(serverIp, serverPort);
    }
}
