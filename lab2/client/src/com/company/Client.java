package com.company;

import com.company.images.BufferedImageDTO;
import com.company.rmi.IRemoteImageHandler;

import java.awt.image.BufferedImage;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {

        System.out.println("Client is started");

        IRemoteImageHandler stub = null;

        try{
        Registry registry = LocateRegistry.getRegistry("localhost", 1099);
        stub = (IRemoteImageHandler)registry.lookup(IRemoteImageHandler.class.getSimpleName());
        } catch (RemoteException e) {
            System.out.println("Can't connect to server");
        } catch (NotBoundException e) {
            System.out.println("Can't find a suitable remote method");
        }

        BufferedImage source = askUserForInputImage();
        BufferedImageDTO result = null;

        try {
            result = stub.processImage(new BufferedImageDTO(source));
        } catch (RemoteException e) {
            System.out.println("An error occurred while sending image");
        }

        askUserForOutputImage(result.getImage());
    }

    private static BufferedImage askUserForInputImage(){

        Scanner in = new Scanner(System.in);
        while (true){

            System.out.println("Please, enter an input file path:\n");
            String path = in.nextLine();

            BufferedImage sourceImage = null;
            try {
                sourceImage = ImageLoadingTools.loadImageFormFile(path);
            } catch (Exception e){
                System.out.println("Path or file is invalid!");
                continue;
            }
            System.out.println(String.format("Image loaded from %s", path));
            return sourceImage;
        }
    }

    private static void askUserForOutputImage(BufferedImage image){
        Scanner in = new Scanner(System.in);
        while (true){

            System.out.println("Please, enter an output file path:\n");
            String path = in.nextLine();

            try {
                ImageLoadingTools.saveImageToFile(image, path);
            } catch (Exception e){
                System.out.println("Path or file is invalid!");
                continue;
            }
            System.out.println(String.format("Image saved to %s", path));
        }
    }
}
