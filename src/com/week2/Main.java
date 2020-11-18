package com.week2;

import java.lang.reflect.Array;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        int[] input = new int[0];
        removeLastOccurrence(0, input);
    }

    /**
     * Takes the array and the last occurring element x,
     * shifting the rest of the elements left. I.e.
     * [1, 4, 7, 9], with x=7 would result in:
     * [1, 4, 9].
     *
     * @param x
     *     the entry to remove from the array
     * @param arr
     *     to remove an entry from
     * @return the updated array, without the last occurrence of x
     */
    public static int[] removeLastOccurrence(int x, int[] arr) {
        if (arr.length > 0){
            int[] resultArr = new int[arr.length - 1];
        }else{
            return arr;
        }

        int positionLastOccurrence = -1;
        for (int i = 0; i < arr.length; i++){
            if (arr[i] == x){
                positionLastOccurrence = i;
            }
        }
        if(positionLastOccurrence >= 0){
            if (arr.length > 0){
                int[] resultArr = new int[arr.length - 1];
                System.arraycopy(arr, 0, resultArr, 0, positionLastOccurrence);
                System.arraycopy(arr,positionLastOccurrence + 1, resultArr, positionLastOccurrence,(arr.length-1) - positionLastOccurrence);
                return resultArr;
            }else{
                return arr;
            }
        }else{
            return arr;
        }
    }

}
