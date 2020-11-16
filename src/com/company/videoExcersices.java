package com.company;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class videoExcersices {
    public static void main(String args[]){
        List<Integer> SRList = makeRndOrderList(500);
        System.out.println(SRList);
        int splitPos = splitListPos(50, SRList.size()/2, SRList);
        System.out.println("Lower List: " + SRList.subList(0, splitPos));
        System.out.println("Upper List: " + SRList.subList(splitPos + 1, SRList.size()));


    }

    private static List<Integer> makeRndOrderList(int numberOfItems){
        List<Integer> resultList = new ArrayList<>();
        Random rd = new Random();
        for (int i = 0; i < numberOfItems; i++){
            resultList.add(rd.nextInt(100));
        }
        Collections.sort(resultList);
        return resultList;
    }

    private static int splitListPos(int splitValue, int splitPosition ,  List<Integer> sList){
        if (sList.get(splitPosition) >= splitValue){
            return splitListPos(splitValue, splitPosition-1, sList);
        }else{
            return splitPosition + 1;
        }
    }
}
