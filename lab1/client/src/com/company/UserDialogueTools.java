package com.company;

import com.company.model.Matrix;

import java.io.IOException;
import java.util.Scanner;

public class UserDialogueTools {

    public static Matrix askUserForInputFile(String fileType) {
        System.out.printf("Enter %s input file name:\n", fileType);
        Scanner in = new Scanner(System.in);
        while (true){
            String path = in.nextLine();

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

    public static void askUserForOutputFile(Matrix m) {
        System.out.printf("Enter output file name:\n");
        Scanner in = new Scanner(System.in);
        while (true){
            String path = in.nextLine();

            try {
                Matrix.writeToFile(m, path);
                System.out.println("Writed successfuly\n");
                System.out.println();
                break;
            } catch (IOException e) {
                System.err.println("Directory not found!");
            }
        }
    }


}
