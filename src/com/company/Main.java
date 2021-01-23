package com.company;

import java.lang.reflect.Array;
import java.util.*;
import static org.junit.Assert.*;
import org.junit.*;


public class Main {

    public static void main(String[] args) {
        int[] input = {4, 2, 5, 1, 3};
        mergeSort(input);
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, input);
    }

    public static void mergeSort(int[] arr) {
        if(arr == null || arr.length < 2){
            return;
        }

        int split = arr.length/2;
        int[] s1 = Arrays.copyOfRange(arr, 0, split);
        int[] s2 = Arrays.copyOfRange(arr, split, arr.length);

        mergeSort(s1);
        mergeSort(s2);

        merge(s1, s2, arr);

    }

    public static void merge(int[] s1, int[] s2, int[] arr){
        int i = 0;
        int j = 0;

        while (i+j< arr.length){
            if (j == s2.length || (i < s1.length && s1[i] < s2[j])){
                arr[i+j] = s1[i];
                i++;
            }else{
                arr[i+j] = s2[j];
                j++;
            }
        }
    }



}
