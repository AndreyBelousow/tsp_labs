package com.company.network;

import com.company.model.Matrix;

import java.io.IOException;
import java.util.Scanner;

public class ConnectionHandler {

    private ClientSocket client;

    public static final String statusOk = "200 OK";
    public static final String statusError = "500 ERROR";
    public static final String statusWrongMatrices = "WRONG_MATRICES";

    public static Matrix result;

    private enum ClientState {
        initialized,
        waitingForServerConfirmation,
        sendingMatrices,
        recievingResult,
        exiting,
        wrongMatrices,
        error
    }

    private ClientState state  = ClientState.initialized;

    public String run(String serverIp, int serverPort, Matrix a, Matrix b){

        while (true){

            try {
                switch (state) {
                    case initialized:
                        connectToServer(serverIp, serverPort);
                        break;
                    case waitingForServerConfirmation:
                        getServerConfirmation();
                        break;
                    case sendingMatrices:
                        sendMatrices(a, b);
                        break;
                    case recievingResult:
                        recieveResult();
                }
            } catch (IOException e) {
                System.err.println("Can't handle connection!");
                state = ClientState.error;
            } catch (ClassNotFoundException e) {
                System.err.println("Class not found!");
                state = ClientState.error;
            } catch (ClassCastException e){
                System.err.println("Can't parse data!");
                state = ClientState.error;
            }

            if(state == ClientState.error)
                return statusError;
            if(state == ClientState.exiting)
                return statusOk;
            if(state == ClientState.wrongMatrices)
                return statusWrongMatrices;
        }
    }

    private void connectToServer(String serverIp, int serverPort){

        System.out.printf("Trying to connect %s:%s...\n", serverIp, serverPort);

        while(true) {
            try {
                client = new ClientSocket(serverPort, serverIp);
                System.out.printf("Connected to %s:%s\n", serverIp, serverPort);
                client.send(statusOk);
                state = ClientState.waitingForServerConfirmation;
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
        state = ClientState.sendingMatrices;
    }

    private void sendMatrices(Matrix a, Matrix b) throws IOException, ClassNotFoundException {
        System.out.println("Sending...\n");
        client.send(a);
        System.out.printf(">> %s\n", client.recieve());
        System.out.println("Sending...\n");
        client.send(b);
        System.out.printf(">> %s\n", client.recieve());
        state = ClientState.recievingResult;
    }

    private void recieveResult() throws IOException, ClassNotFoundException {
        String responce = (String) client.recieve();
        if(responce.equals(statusOk)){
            result = (Matrix) client.recieve();
            state = ClientState.exiting;
        } else if (responce.equals(statusWrongMatrices)){
            state = ClientState.wrongMatrices;
        }
    }

}
