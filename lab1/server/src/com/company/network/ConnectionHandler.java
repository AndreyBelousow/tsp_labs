package com.company.network;

import com.company.model.Matrix;
import com.company.model.exceptions.IllegalMatrixDimensionsException;

import java.io.*;
import java.net.Socket;

public class ConnectionHandler implements Runnable{

    private Socket clientSocket;

    public static final String statusOk = "200 OK";
    public static final String statusError = "500 ERROR";

    @Override
    public void run() {
        try {
            System.out.printf("Client connected from %s:%s\n", clientSocket.getInetAddress(), clientSocket.getPort());
            ObjectOutputStream oos = new ObjectOutputStream(new DataOutputStream(clientSocket.getOutputStream()));
            ObjectInputStream ois = new ObjectInputStream(new DataInputStream(clientSocket.getInputStream()));

            oos.writeObject(statusOk);

            Matrix a = (Matrix) ois.readObject();
            oos.writeObject(statusOk);
            Matrix b = (Matrix) ois.readObject();
            oos.writeObject(statusOk);

            System.out.printf("Received two matrices from %s:%s\n", clientSocket.getInetAddress(), clientSocket.getPort());

            try {
                Matrix c = Matrix.multiply(a, b);
                oos.writeObject(c);
                System.out.printf("Sending the result to %s:%s\n", clientSocket.getInetAddress(), clientSocket.getPort());
            }
            catch (IllegalMatrixDimensionsException e){
                System.err.printf("Wrong matrix dimensions");
                oos.writeObject(statusError);
            }
            oos.flush();
            oos.close();
            ois.close();

            System.out.printf("Closing connection with %s:%s\n\n", clientSocket.getInetAddress(), clientSocket.getPort());
            clientSocket.close();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    //_Constructors________________________________

    public ConnectionHandler(Socket clientSocket){
        this.clientSocket = clientSocket;
    }
}
