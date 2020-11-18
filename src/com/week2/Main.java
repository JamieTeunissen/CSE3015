package com.week2;

import java.util.ArrayList;

import static java.util.Arrays.*;

public class Main {

    public static void main(String[] args) {
        ArrayList<Integer> input = new ArrayList<>(asList(1, 1, 3, 5, 7, 1, 5, 9, 1));
        removeAll(input);
        System.out.println("Fill");
    }

    /**
     * Removes all elements from the ArrayList, using the removeLastOccurrence method.
     *
     * @param list
     *     to remove the elements from.
     */
    public static void removeAll(ArrayList<Integer> list) {
        int cycleAmount = list.size();
        for (int i = 0; i < cycleAmount; i++){
            removeLastOccurrence(list.get(0), list);
        }
    }

    /**
     * Takes an ArrayList and removes last occurrence of x,
     * shifting the rest of the elements left.
     * I.e. [5, 1, 5, 9, 8], with x = 5
     * would result in: [5, 1, 9, 8].
     * Note that this method does not return a new list.
     * Instead, the list that is passed as a parameter is changed.
     *
     * @param list
     *     to remove an element from.
     * @param x
     *     element value to look for
     */
    public static void removeLastOccurrence(int x, ArrayList<Integer> list) {
        int positionLastOccurrence = -1;
        for (int i = 0; i < list.size(); i++){
            if (list.get(i) == x){
                positionLastOccurrence = i;
            }
        }
        if(positionLastOccurrence >= 0){
            list.remove(positionLastOccurrence);
        }
    }

}
