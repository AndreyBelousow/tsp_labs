package com.company.network;

import com.company.model.Matrix;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class ClientSocket {

    private int serverPort;
    private InetAddress serverIp;

    private Socket socket;

    private ObjectOutputStream output;
    private ObjectInputStream input;

    public void send(Object o) throws IOException, ClassNotFoundException {
        try{

            output.writeObject(o);
            output.flush();

        } catch (IOException e) {
            throw e;
        }
    }

    public Object recieve() throws ClassNotFoundException, IOException {
        try{

            Object responce = input.readObject();

            try{
                Matrix k = (Matrix) responce;
            }
            catch(Exception e){

            }

            return responce;
        } catch (IOException e) {
            throw e;
        }
    }

    //_Constructors________________________________

    public ClientSocket(int serverPort, String serverIp) throws IOException {
        this.serverIp = InetAddress.getByName(serverIp);
        this.serverPort = serverPort;
        try {
            socket = new Socket(serverIp, serverPort);
            input = new ObjectInputStream(new DataInputStream(socket.getInputStream()));
            output = new ObjectOutputStream(new DataOutputStream(socket.getOutputStream()));
        } catch (IOException e) {
            throw e;
        }
    }
}
