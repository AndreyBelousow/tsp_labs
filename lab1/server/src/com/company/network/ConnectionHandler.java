package com.company.network;

import com.company.model.Matrix;
import com.company.model.exceptions.IllegalMatrixDimensionsException;

import java.io.*;
import java.net.Socket;

public class ConnectionHandler implements Runnable{

    private Socket clientSocket;

    public static final String statusOk = "200 OK";
    public static final String statusError = "500 ERROR";

    private enum ServerState {
        initialized,
        waitingForClientConfirmation,
        waitingForMatrices,
        sendingResult,
        exiting,
        error
    }

    private ServerState state = ServerState.initialized;
    private Matrix result;

    private ObjectOutputStream output;
    private ObjectInputStream input;

    @Override
    public void run() {

        try {
            output = new ObjectOutputStream(clientSocket.getOutputStream());
            input = new ObjectInputStream(clientSocket.getInputStream());
        }catch (IOException e) {
            System.err.println("Can't handle connection!");
            state = ServerState.error;
        }

        while (true) {
            try {
                switch (state) {
                    case initialized:
                        sendConfirmation();
                        break;
                    case waitingForClientConfirmation:
                        getClientConfirmation();
                        break;
                    case waitingForMatrices:
                        getAndProcessMatrices();
                        break;
                    case sendingResult:
                        sendResult();
                        break;
                }
            } catch (IOException e) {
                System.err.println("Can't handle connection!");
                state = ServerState.error;
            } catch (ClassNotFoundException e) {
                System.err.println("Class not found!");
                state = ServerState.error;
            } catch (ClassCastException e) {
                System.err.println("Can't parse data!");
                state = ServerState.error;
            }

            if (state == ServerState.error || state == ServerState.exiting)
                break;
        }
        closeConnection();
    }

    private void sendConfirmation() throws IOException {
        System.out.printf("Client connected from %s:%s\n",
                clientSocket.getInetAddress(), clientSocket.getPort());
        output.writeObject(statusOk);
        state = ServerState.waitingForClientConfirmation;
    }

    private void getClientConfirmation() throws IOException, ClassNotFoundException {
        String response = (String) input.readObject();
        state = ServerState.waitingForMatrices;
    }

    private void getAndProcessMatrices() throws IOException, ClassNotFoundException {
        Matrix a = (Matrix) input.readObject();
        output.writeObject(statusOk);
        Matrix b = (Matrix) input.readObject();
        output.writeObject(statusOk);

        System.out.printf("Received two matrices from %s:%s\n",
                clientSocket.getInetAddress(), clientSocket.getPort());
        try {
            result = Matrix.multiply(a, b);
            state = ServerState.sendingResult;
            output.writeObject(statusOk);
        } catch (IllegalMatrixDimensionsException e) {
            System.err.printf("Wrong matrix dimensions\n");
            output.writeObject(statusError);
            state = ServerState.exiting;
        }
    }

    private void sendResult() throws IOException {
        System.out.printf("Sending the result to %s:%s\n",
                clientSocket.getInetAddress(), clientSocket.getPort());
        output.writeObject(result);
        state = ServerState.exiting;
    }

    private void closeConnection(){
        try{
            System.out.printf("Closing connection with %s:%s\n\n",
                    clientSocket.getInetAddress(),
                    clientSocket.getPort());

            switch (state){
                case error:
                    output.writeObject(statusError);
                    break;
                case exiting:
                    output.writeObject(statusOk);
                    break;
            }

            output.flush();
            output.close();
            input.close();
            clientSocket.close();
        }
        catch (IOException e) {
            System.err.println("Something very bad happens");
        }
    }

    //_Constructors________________________________

    public ConnectionHandler(Socket clientSocket){
        this.clientSocket = clientSocket;
    }
}
