package com.company;

import com.company.model.Matrix;
import com.company.network.NetworkTools;

public class Main {

    public static final int serverPort = 8080;
    public static final String serverIp = "127.0.0.1";

    public static void main(String[] args) {

        System.out.println("Client is started\n__________________");

        NetworkTools.connectToServer(serverIp, serverPort);

        Matrix a = UserDialogueTools.askUserForInputFile("first matrix");
        Matrix b = UserDialogueTools.askUserForInputFile("second matrix");

        NetworkTools.sendMatrices(a, b);

        NetworkTools.recieveResult();
        if(NetworkTools.result!=null) {
            Matrix c = NetworkTools.result;
            System.out.println("Successfully receiving the result\n");
            UserDialogueTools.askUserForOutputFile(c);
        }
    }
}
