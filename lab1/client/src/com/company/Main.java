package com.company;

import com.company.model.Matrix;
import com.company.network.ConnectionHandler;

public class Main {

    public static final int serverPort = 8080;
    public static final String serverIp = "127.0.0.1";

    public static void main(String[] args) {

        System.out.println("Client is started\n__________________");

        Matrix a = UserDialogueTools.askUserForInputFile("first matrix");
        Matrix b = UserDialogueTools.askUserForInputFile("second matrix");

        ConnectionHandler connectionHandler = new ConnectionHandler();
        connectionHandler.run(serverIp, serverPort, a, b);
    }
}
