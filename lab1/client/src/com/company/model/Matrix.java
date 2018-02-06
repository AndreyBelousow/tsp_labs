package com.company.model;

import com.company.model.exceptions.IllegalMatrixDimensionsException;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

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
     * Serializes Matrix to specified file
     * @param param Matrix
     * @param path file location
     * @throws IOException
     */
    public static void writeToFile(Matrix param, String path) throws IOException{
        FileOutputStream fos = new FileOutputStream(path);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(param);
        oos.flush();
        oos.close();
    }

    /**
     * Deserializes Matrix from a specified file
     * @param path file location
     * @return Matrix
     * @throws IOException in case of file doesn't exist
     * @throws ClassNotFoundException in case of deserialization fail
     */
    public static Matrix readFromFile(String path) throws IOException, ClassNotFoundException{
        FileInputStream fis = new FileInputStream(path);
        ObjectInputStream oin = new ObjectInputStream(fis);
        Matrix res = (Matrix) oin.readObject();
        return res;
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
