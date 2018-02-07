package com.company;

import com.company.model.Matrix;

import java.io.IOException;
import java.util.Scanner;

public class Tools {

    private ClientSocket socket;

    private Scanner input;

    public Tools(){
        input = new Scanner(System.in);
    }

    public Matrix askUserForInputFile(String fileType) {
        System.out.printf("Enter %s input file name\n", fileType);
        while (true) {

            String path = input.nextLine();

            try {
                Matrix m = Matrix.readFromFile(path);
                System.out.println("Read successful\n");
                System.out.println();
                return m;
            } catch (IOException e) {
                System.err.println("File is not existing or corrupted!");
            }
        }
    }

    public void askUserForOutputFile(Matrix m) {
    }

    public void connectToServer(String serverIp, int serverPort){
        System.out.printf("Trying to connect %s:%s...\n", serverIp, serverPort);

        while(true) {
            try {
                socket = new ClientSocket(serverPort, serverIp);
                System.out.printf("Connected to %s:%s\n", serverIp, serverPort);
                break;
            } catch (IOException e) {
                System.out.printf("Looks like there is no server on %s:%s\n", serverIp, serverPort);
                System.out.println("Press any key to reconnect");
                input.nextLine();
            }
        }
    }
}
