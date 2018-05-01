package com.company;

import test.Test;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class Server {

    public static void main(String[] args) throws RemoteException, MalformedURLException {

        System.out.println("\nServer started\n");

        Test.run();

        RemoteImageHandler imageHandler = new ImageHandlerImpl();

        LocateRegistry.createRegistry(1337);
        Naming.rebind("server.proceedImage", imageHandler);
        System.out.println("RMI is ready.");
    }
}
