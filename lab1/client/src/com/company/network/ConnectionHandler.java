package com.company.network;

import com.company.UserDialogueTools;
import com.company.model.Matrix;

import java.io.IOException;
import java.util.Scanner;

public class ConnectionHandler {

    private ClientSocket client;

    public static final String statusOk = "200 OK";
    public static final String statusError = "500 ERROR";

    public void run(String serverIp, int serverPort, Matrix a, Matrix b){

            try {
                connectToServer(serverIp, serverPort);
                getServerConfirmation();
                sendMatrices(a, b);
                recieveResult();
            }catch (IOException e) {
                System.err.println("Can't handle connection!");
            } catch (ClassNotFoundException e) {
                System.err.println("Class not found!");
            } catch (ClassCastException e) {
                System.err.println("Can't parse data!");
            }
    }

    private void connectToServer(String serverIp, int serverPort){

        System.out.printf("Trying to connect %s:%s...\n", serverIp, serverPort);

        while(true) {
            try {
                client = new ClientSocket(serverPort, serverIp);
                System.out.printf("Connected to %s:%s\n", serverIp, serverPort);
                client.send(statusOk);
                break;
            } catch (IOException e) {
                System.out.printf("Looks like there is no server on %s:%s\n", serverIp, serverPort);
            }
            System.out.println("Enter anything to reconnect, Q to quit");

            Scanner in = new Scanner(System.in);
            String s = in.nextLine();
            if(s.equalsIgnoreCase("Q"))
                System.exit(0);
        }
    }

    private void getServerConfirmation() throws IOException, ClassNotFoundException {
        client.recieve();
    }

    private void sendMatrices(Matrix a, Matrix b) throws IOException, ClassNotFoundException {
        System.out.println("Sending...\n");
        client.send(a);
        System.out.printf(">> %s\n", client.recieve());
        System.out.println("Sending...\n");
        client.send(b);
        System.out.printf(">> %s\n", client.recieve());
    }

    private void recieveResult() throws IOException, ClassNotFoundException {
        String responce = (String) client.recieve();
        if(responce.equals(statusOk)){
            Matrix result = (Matrix) client.recieve();
            UserDialogueTools.askUserForOutputFile(result);
        } else {
            UserDialogueTools.askUserForOutputFile(responce);
        }
    }

}
