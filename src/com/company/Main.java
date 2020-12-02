package com.company;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        int[] data = {11, 90, 33, 71, 24, 50, 35, 30, 15, 21};
        int[] result = mergeSort(data);
        System.out.println(result);
    }

    public static int[] mergeSort(int[] elements) {
        //base case
        if(elements.length < 2){
            return elements;
        }

        //split into two lists
        int middle = elements.length/2;
        int[] left = Arrays.copyOfRange(elements, 0, middle);
        int[] right = Arrays.copyOfRange(elements, middle, elements.length);

        //recursion
        left = mergeSort(left);
        right = mergeSort(right);

        //merge
        return merger(left, right);
    }

    private static int[] merger(int[] left, int[] right){
        int i = 0;
        int j = 0;
        int x = 0;
        int[] res = new int [left.length + right.length];
        while(i < left.length && j < right.length){
            if(left[i] < right[j]){
                res[x] = left[i];
                i++;
            }else{
                res[x] = right[j];
                j++;
            }
            x++;
        }
        while(i < left.length){
            res[x] = left[i];
            i++;
            x++;
        }
        while(j < right.length){
            res[x] = right[j];
            j++;
            x++;
        }
        return res;
    }
}
