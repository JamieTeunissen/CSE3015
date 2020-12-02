package com.company;

public class Main {

    public static void main(String[] args) {
        int[] data = {11, 90, 33, 71, 24, 50, 35, 30, 15, 21};
        selectionSort(data);
        System.out.println(data);

    }

    public static void selectionSort(int[] elements) {
        for (int i = 0; i < elements.length; i++){
            int lowest = elements[i];
            int position = i;
            for (int j = i; j < elements.length; j++){
                if (elements[j] < lowest){
                    lowest = elements[j];
                    position = j;
                }
            }
            int buffer = elements[i];
            elements[i] = lowest;
            elements[position] = buffer;
        }
    }
}
