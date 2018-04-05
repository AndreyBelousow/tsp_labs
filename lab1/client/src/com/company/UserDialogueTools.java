package com.company;

import com.company.model.Matrix;

import java.io.BufferedWriter;
import java.io.FileWriter;
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
                return m;
            } catch (IOException e) {
                System.err.println("File is not existing or corrupted!");
            }
        }
    }

    public static void askUserForOutputFile(Object result) {
        System.out.printf("Enter output file name:\n");
        Scanner in = new Scanner(System.in);
        while (true){
            String path = in.nextLine();
            try {
                try {
                    Matrix matrix = (Matrix) result;
                    Matrix.writeToFile(matrix, path);
                } catch (Exception e) {
                    BufferedWriter writer = new BufferedWriter(new FileWriter(path));
                    writer.write(result.toString());
                    writer.flush();
                    writer.close();
                }
                System.out.println("Writed successfuly\n");
                break;
            } catch (IOException e) {
                System.err.println("Directory not found!");
            }
        }
    }


}
