package com.week2;

public class Main {
    public static void main(String[] args) {
        double[][] array = new double[0][0];
        double[][] clone = clone(array);
    }

    static double[][] clone(double[][] a) {
        if(a.length == 0){
            return a;
        }
        double[][] b = new double[a.length][a[0].length];
        int iterator = 0;
        for (double[] row : a){
            for (int i = 0; i < row.length; i++){
                b[iterator][i] = row[i];
            }
            iterator++;
        }

        return b;
    }
}
