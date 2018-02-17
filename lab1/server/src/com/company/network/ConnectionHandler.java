package com.company.network;

import com.company.model.Matrix;
import com.company.model.exceptions.IllegalMatrixDimensionsException;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ConnectionHandler implements Runnable{

    private Socket clientSocket;

    @Override
    public void run() {
        try {
            System.out.printf("Client connected from %s:%s\n", clientSocket.getInetAddress(), clientSocket.getPort());
            ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream());

            Matrix a = (Matrix) ois.readObject();
            Matrix b = (Matrix) ois.readObject();
            try {
                Matrix c = Matrix.multiply(a, b);
                oos.writeObject(c);
            }
            catch (IllegalMatrixDimensionsException e){
                oos.writeObject("Wrong matrix dimensions!");
            }
            oos.flush();
            oos.close();
            ois.close();
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
