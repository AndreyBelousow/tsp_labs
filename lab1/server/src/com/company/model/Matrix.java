package com.company.model;

import com.company.model.exceptions.IllegalMatrixDimensionsException;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Matrix implements Serializable{

    /**
     * Just a MxN matrix of floating point values
     */
    private float[][] matrix;

    /**
     * Multiplies two Matrices
     * @param a MxN Matrix
     * @param b NxO Matrix
     * @return result of multiplication a*b
     * @throws IllegalMatrixDimensionsException
     * throwed if columns count of a doesn't corresponds to rows count of b
     */
    public static Matrix multiply(Matrix a, Matrix b) throws IllegalMatrixDimensionsException {

        if(a.getColumnsCount()!=b.getRowsCount())
            throw new IllegalMatrixDimensionsException();

        Matrix res = new Matrix(a.getRowsCount(), b.getColumnsCount());

        for (int i=0; i<res.getRowsCount(); i++){
            for (int j=0; j<res.getColumnsCount(); j++){
                float value = 0;
                for (int n=0; n<a.getColumnsCount(); n++){
                    value += a.getValue(i, n)*b.getValue(n, j);
                }
                res.setValue(i, j, value);
            }
        }

        return res;
    }

    /**
     * Writes Matrix to specified file
     * @param param Matrix
     * @param path file location
     */
    public static void writeToFile(Matrix param, String path) throws IOException {

        StringBuilder data = new StringBuilder();
        for (int i=0; i<param.getRowsCount();i++){
            for (int j= 0; j<param.getColumnsCount(); j++){
                data.append(param.getValue(i,j));
                data.append(" ");
            }
            data.append("\n");
        }

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(path));
            writer.write(data.toString());
            writer.flush();
            writer.close();
        }
        catch (IOException e){
            //e.printStackTrace();
            throw e;
        }
    }

    /**
     * Reads Matrix from a specified file
     * @param path file location
     * @return Matrix
     */
    public static Matrix readFromFile(String path) throws IOException {

        List<List<String>> lines = new ArrayList<List<String>>();
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {

            String data = null;
            while ((data = reader.readLine()) != null) {
                lines.add(Arrays.asList(data.split(" ")));
            }
        } catch (IOException e) {
            //e.printStackTrace();
            throw e;
        }

        Matrix res = new Matrix(lines.size(), lines.get(0).size());

        for (int i = 0; i < res.getRowsCount(); i++) {
            for (int j = 0; j < res.getColumnsCount(); j++) {
                res.setValue(i,j, Float.parseFloat(lines.get(i).get(j)));
            }
        }

        return res;
    }

    public static void writeToConsole(Matrix m){
        for (int i = 0; i< m.getRowsCount(); i++){
            for (int j = 0; j < m.getColumnsCount(); j++) {
                System.out.print(m.getValue(i,j) + " ");
            }
            System.out.print("\n");
        }
    }

    //_Constructors__________________________________________________

    public Matrix(int rowsCount, int columnsCount){
        matrix = new float[rowsCount][columnsCount];
    }

    //_Getters and Setters___________________________________________

    public int getRowsCount(){
        return matrix.length;
    }

    public int getColumnsCount(){
        return matrix[0].length;
    }

    public float getValue(int rowIndex, int columnIndex){
        return matrix[rowIndex][columnIndex];
    }

    public void setValue(int rowIndex, int columnIndex, float value){
        matrix[rowIndex][columnIndex] = value;
    }
}
