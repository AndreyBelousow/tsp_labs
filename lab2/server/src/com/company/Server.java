package com.company;

import com.company.rmi.ImageHandlerImpl;
import com.company.rmi.IRemoteImageHandler;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.ExportException;
import java.rmi.server.UnicastRemoteObject;

public class Server {

    public static void main(String[] args) throws RemoteException, MalformedURLException {

        System.out.println("\nServer started\n");

        IRemoteImageHandler imageHandler = new ImageHandlerImpl();

        Registry registry = LocateRegistry.createRegistry(1099);
        try {
            registry.bind(IRemoteImageHandler.class.getSimpleName(), imageHandler);
        } catch (AlreadyBoundException e) {
            System.out.println("Naming is already bind!");
        } catch (ExportException e){
            System.out.println("Object is already exported!");
        }
        System.out.println("RMI is ready.");
    }
}
