package com.company;

import java.lang.reflect.Array;

public class implementationWk1 {
    public static void main(String[] args){
        int[] arr1 = { 1, 2, 7, 8, 9, 10};
        int[] arr2 = { 2, 3, 5, 6 };
        merge(arr1, arr2);
    }
    /**
     * Checks whether the given integer value is a prime number.
     *
     * @param n integer value to be checked if it is a prime number or not
     * @return returns true if n is prime, false otherwise
     */

    public static boolean isPrime(int n) {
        if (n > 1){
            for (int i = 2; i < n; i++){
                double check = (double)n/(double)i;
                if (check % 1  == 0){
                    return false;
                }
            }
            return true;
        }else{
            return false;
        }
    }

    /**
     * Counts and returns the number of prime numbers that are less or equal
     * than the given integer value.
     *
     * @param n integer value defining an upper bound on the set of prime number to count
     * @return returns the number of prime numbers that are less or equal than n
     */
    public static int numPrimes(int n) {
        int resultCount = 0;
        for (int j = 2; j < n+1; j++){
            if (isPrime(j)){
                resultCount++;
            }
        }
        return resultCount;
    }

    /**
     * Returns the sum of all elements in the array up to (and including) the `n`th element
     *
     * @param arr the array of integers that needs to be summed
     * @param n the index of the last item to consider
     */
    public static int sumElementsUpTo(int[] arr, int n) {
        int resultSum = 0;
        for (int i = 0; i <= n; i++){
            resultSum += arr[i];
        }
        return resultSum;
    }

    /**
     * Returns the sum of all elements in the array up to (and including) the `n`th element
     *
     * @param arr the array of integers that needs to be summed
     * @param n the index of the last item to consider
     */
    public static int sumRecElementsUpTo(int[] arr, int n) {
        if (n < 0){
            return 0;
        }
        return arr[n] + sumRecElementsUpTo(arr, n-1);
    }

    /**
     * Reverses the order of the elements of the given array.
     *
     * @param arr
     * the array to reverse
     */
    public static void reverse(int[] arr) {
        int tmp = 0;
        if(!(arr == null)) {
            for (int i = 0; i < arr.length / 2; i++) {
                tmp = arr[i];
                arr[i] = arr[arr.length - (i + 1)];
                arr[arr.length - (i + 1)] = tmp;
            }
        }
    }

    static int multiply(int num1, int num2) {
        if (num2 == 0) {
            return 0;
        } else if (num2 < 0) {
            return -(num1 - multiply(num1, num2 + 1));
        } else{
            return num1 + multiply(num1, num2-1);
        }
    }

    /**
     * Computes the nth number in the Fibonacci sequence.
     * @param n - the index of the number in the Fibonacci sequence.
     * @return nth number in the Fibonacci sequence
     */
//    public static int fibonacci(int n) {
//        if (n == 0){
//            return 0;
//        }else if (n == 1){
//            return 1;
//        }else{
//            return fibonacci(n-1) + fibonacci(n-2);
//        }
//    }

    public static int fibonacci(int n) {
        return fibonacci_helper(n, 0, 1);
    }

    /**
     * Helper function for computing the nth number in the Fibonacci sequence.
     * @param n - the index of the number in the Fibonacci sequence.
     * @param acc1 - accumulator for the previous number in the Fibonacci sequence.
     * @param acc2 -accumulator for the previous number in the Fibonacci sequence.
     * @return
     */
    public static int fibonacci_helper(int n, int acc1, int acc2) {
        if (n == 0)
            return acc1;
        if (n == 1)
            return acc2;
        return fibonacci_helper(n - 1, acc2, acc1 + acc2);
    }

    /**
     * Calculates the number of occurrences of integers from the range
     * {0, ..., r} within a given array of integer elements. Returns
     * the array of counts: a new array of integers of size r + 1, where the
     * element at index i denotes the number occurrences of elements equal
     * to i in the given input array (with i in {0, ..., r}).
     * If the input array is null or of length 0, this will return null.
     *
     * @param arr the array of integer elements to be counted.
     * @param r the integer indicating the last element of the range.
     * @return a new array containing the number of occurrences of each
     * integer {0, ..., r} in the input array (index i has the
     * count of elements equal to i in the input array, with i
     * in {0, ..., r})
     */
    public static int[] count(int[] arr, int r) {
        if (arr == null || arr.length == 0) {
            return null;
        }

        int[] result = new int[r+1];
        for (int element : arr){
            if (element <= r && element >= 0){
                result[element] += 1;
            }
        }
        return result;
    }

    /**
     * Merges two sorted arrays such that the resulting array has all elements
     * from both arrays and is also sorted. Assumes that the elements in the
     * given arrays are sorted in non-decreasing order. If there are duplicate
     * elements in the input arrays, these should also be present in the
     * resulting array. If both arrays are null the result should be null, or a
     * copy of the non-null array if only one is null.
     *
     * Efficiency requirements: merge the two arrays in a single pass.
     *
     * @param arr1 first sorted array to be merged
     * @param arr2 second sorted array to be merged
     * @return sorted array containing all elements from both arrays
     */
    public static int[] merge(int[] arr1, int[] arr2) {
        if (arr1 == null) {
            if (arr2 == null){
                return null;
            }
            return arr2;
        }else if(arr2 == null){
            return arr1;
        }


        int i = 0;
        int j = 0;
        int k = 0;
        int arr2tmp = arr2[i];
        int arr1tmp = arr1[j];

        int[] result = new int[arr1.length + arr2.length];
        while (k < result.length){
            if (arr1tmp < arr2tmp || j >= arr2.length){
                result[k] = arr1tmp;
                i++;
                if (i < arr1.length){
                    arr1tmp = arr1[i];
                }
            }else{
                result[k] = arr2tmp;
                j++;
                if (j < arr2.length){
                    arr2tmp = arr2[j];
                }
            }
            k++;
        }

        return result;
    }
}
