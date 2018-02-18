package com.company.network;

import com.company.model.Matrix;

import java.io.IOException;
import java.util.Scanner;

public class NetworkTools {

    private static ClientSocket client;

    public static Matrix result;

    public static final String statusOk = "200 OK";
    public static final String statusError = "500 ERROR";

    public static void connectToServer(String serverIp, int serverPort){

        System.out.printf("Trying to connect %s:%s...\n", serverIp, serverPort);

        while(true) {
            try {
                client = new ClientSocket(serverPort, serverIp);

                String res = getResponce();
                if(res.equalsIgnoreCase(statusOk)) {
                    System.out.printf(">> %s\n", res);
                    System.out.printf("Connected to %s:%s\n", serverIp, serverPort);
                    break;
                }
                else {
                    System.out.printf("Looks like there is something on %s:%s, but it is'nt our server\n", serverIp, serverPort);
                }

            } catch (IOException e) {
                System.out.printf("Looks like there is no server on %s:%s\n", serverIp, serverPort);
            }
            System.out.println("Enter anything to reconnect, q to quit");

            Scanner in = new Scanner(System.in);
            String s = in.nextLine();
            if(s.equalsIgnoreCase("Q"))
                System.exit(0);
        }
    }

    public static void sendMatrices(Matrix a, Matrix b){
        try {
            client.send(a);
            System.out.printf(">> %s\n", getResponce());
            client.send(b);
            System.out.printf(">> %s\n", getResponce());
        }
        catch (Exception e){
            System.err.println("Something bad with the connection...");
        }
    }

    public static Matrix recieveResult() {
        Matrix res = null;
        try {
            res = (Matrix) client.recieve();
            result = res;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }

    public static String getResponce() {
        String res = null;
        try {
            res = (String) client.recieve();
            return res;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }
}
