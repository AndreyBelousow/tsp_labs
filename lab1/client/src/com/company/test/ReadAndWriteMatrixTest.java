package com.company.test;

import com.company.model.Matrix;

import java.io.IOException;
import java.util.Random;

public class ReadAndWriteMatrixTest {

    public void Run() throws IOException {

        System.out.println("ReadAndWriteMatrixTest running...\n");
        Matrix m = new Matrix(5,5);

        Random random = new Random();
        for (int i=0; i<m.getRowsCount(); i++){
            for (int j = 0; j<m.getColumnsCount(); j++){
                m.setValue(i,j, random.nextFloat()*10);
            }
        }

        System.out.println("Matrix created\n");

        Matrix.writeToConsole(m);

        Matrix.writeToFile(m, "test.txt");

        System.out.println("Matrix writed\n");

        m = Matrix.readFromFile("test.txt");

        System.out.println("Matrix read\n");

        Matrix.writeToConsole(m);
    }
}
