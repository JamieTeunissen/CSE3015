package com.company;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class videoExcersices {
    public static void main(String args[]){
        List<Integer> SRList = makeList(100);
        List<List<Integer>> answer = splitList(SRList);
        System.out.println(answer);
    }

    private static List<Integer> makeList(int numberOfItems){
        List<Integer> resultList = new ArrayList<>();
        for (int i = 0; i < numberOfItems; i++){
            resultList.add(i);
        }
        return resultList;
    }

    private static List<List<Integer>> splitList(List<Integer> sList){
        int listSize = sList.size();
        int splitValue = listSize / 2;

        List<Integer> list1 = sList.subList(0, splitValue);
        List<Integer> list2 = sList.subList(splitValue + 1, listSize);

        List<List<Integer>> resultList = new ArrayList<>(2);
        resultList.add(list1);
        resultList.add(list2);


        return resultList;
    }
}
