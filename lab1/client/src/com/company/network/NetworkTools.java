package com.company.network;

import com.company.model.Matrix;

import java.io.IOException;
import java.util.Scanner;

public class NetworkTools {

    private static ClientSocket socket;

    public static Matrix result;

    public static void connectToServer(String serverIp, int serverPort){

        System.out.printf("Trying to connect %s:%s...\n", serverIp, serverPort);

        while(true) {
            try {
                socket = new ClientSocket(serverPort, serverIp);
                System.out.printf("Connected to %s:%s\n", serverIp, serverPort);
                break;
            } catch (IOException e) {
                System.out.printf("Looks like there is no server on %s:%s\n", serverIp, serverPort);
                System.out.println("Enter anything to reconnect, q to quit");

                Scanner in = new Scanner(System.in);
                String s = in.nextLine();
                if(s.equalsIgnoreCase("Q"))
                    System.exit(0);
            }
        }
    }

    public static void sendMatrices(Matrix a, Matrix b){
        try {
            socket.send(a);
            socket.send(b);
        }
        catch (Exception e){
            System.out.println("Something bad happens");
        }
    }

    public static void recieveResult() {
        try {
            System.out.println(socket.recieve());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
