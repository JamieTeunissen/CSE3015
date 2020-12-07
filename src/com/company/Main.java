package com.company;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        int[] data = {11, 90, 33, 71, 24, 50, 35, 30, 15, 21};
        quickSort(data);

    }

    public static void quickSort(int[] elements) {
        sort(elements, 0, elements.length - 1);
    }

    private static void sort(int[] elements, int selLinks, int selRechts){
        if(selLinks < selRechts){
            int partitionIndex = partition(elements, selLinks, selRechts);

            sort(elements, selLinks, partitionIndex - 1);
            sort(elements, partitionIndex + 1, selRechts);
        }

    }

    private static int partition(int[] elements, int selLinks, int selRechts){
        int l = selLinks;
        int pivot = elements[selRechts];

        for(int i = selLinks; i < selRechts; i++){
            if(elements[i] < pivot){
                int buffer = elements[i];
                elements[i] = elements[l];
                elements[l] = buffer;
                l++;
            }
        }

        int temp = elements[l];
        elements[l] = pivot;
        elements[selRechts] = temp;

        return l;
    }
}
