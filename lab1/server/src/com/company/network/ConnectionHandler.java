package com.company.network;

import com.company.model.Matrix;
import com.company.model.exceptions.IllegalMatrixDimensionsException;

import java.io.*;
import java.net.Socket;

public class ConnectionHandler implements Runnable{

    private Socket clientSocket;

    public static final String statusOk = "200 OK";
    public static final String statusError = "500 ERROR";

    private Matrix result;

    private ObjectOutputStream output;
    private ObjectInputStream input;

    @Override
    public void run() {
        try {
            output = new ObjectOutputStream(clientSocket.getOutputStream());
            input = new ObjectInputStream(clientSocket.getInputStream());

            sendConfirmation();
            getClientConfirmation();
            getAndProcessMatrices();
            sendResult();
        } catch (IOException e) {
            System.err.println("Can't handle connection!");
        } catch (ClassNotFoundException e) {
            System.err.println("Class not found!");
        } catch (ClassCastException e) {
            System.err.println("Can't parse data!");
        }
        closeConnection();
    }

    private void sendConfirmation() throws IOException {
        System.out.printf("Client connected from %s:%s\n",
                clientSocket.getInetAddress(), clientSocket.getPort());
        output.writeObject(statusOk);
    }

    private void getClientConfirmation() throws IOException, ClassNotFoundException {
        String response = (String) input.readObject();
    }

    private void getAndProcessMatrices() throws IOException, ClassNotFoundException {
        Matrix a = (Matrix) input.readObject();
        output.writeObject(statusOk);

        output.flush();

        Matrix b = (Matrix) input.readObject();
        output.writeObject(statusOk);

        output.flush();

        System.out.printf("Received two matrices from %s:%s\n",
                clientSocket.getInetAddress(), clientSocket.getPort());
        try {
            result = Matrix.multiply(a, b);
            output.writeObject(statusOk);
            output.flush();
        } catch (IllegalMatrixDimensionsException e) {
            System.err.printf("Wrong matrix dimensions\n");
            output.writeObject("Wrong matrix dimensions");
        }
    }

    private void sendResult() throws IOException {
        System.out.printf("Sending the result to %s:%s\n",
                clientSocket.getInetAddress(), clientSocket.getPort());
        output.writeObject(result);
        output.flush();
    }

    private void closeConnection(){
        try{
            System.out.printf("Closing connection with %s:%s\n\n",
                    clientSocket.getInetAddress(),
                    clientSocket.getPort());

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
