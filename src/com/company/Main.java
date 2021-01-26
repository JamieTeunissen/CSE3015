package com.company;

import java.util.*;
import static org.junit.Assert.*;
import org.junit.*;

public class Main {

    public static void main(String[] args) {
        String[] arr = {"f","g","h","a","b","a"};
        int[] res = indexSort(arr);
//        int[] expected = { 0, 2, 3, 1 };
//        assertArrayEquals(expected, res);
    }

    /**
     * Sorts the indices of the array based on the corresponding value in alphabetical order.
     * Returns null if the input array is null.
     *
     * Example: The array ["c","a","b"] will result in [1, 2, 0].
     *
     * @param arr array of Strings that stored the values
     * @return the indices in sorted order
     */
    public static int[] indexSort(String[] arr) {
        if (arr == null){
            return null;
        }

        int[] result = new int[arr.length];

        int j, k;
        for (j = 1; j < arr.length; j++){
            String temp = arr[j];
            int r = j;
            for(k = j - 1; k >= 0 && arr[k].compareTo(temp) > 0; k--){
                arr[k+1] = arr[k];
                result[k+1] = result[k];
            }

            arr[k+1] = temp;
            result[k+1] = j;
        }

        return result;

    }

}
