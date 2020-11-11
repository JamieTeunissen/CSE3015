package com.company;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        List<Integer> list = makeRndList(100);
        int maxDif = determineMaxDifference(list);
        System.out.println(maxDif);

    }

    private static List<Integer> makeRndList(int numberOfItems){
        Random rd = new Random(); // creating Random object
        List<Integer> resultList = new ArrayList<>();
        for (int i = 0; i < numberOfItems; i++){
            resultList.add(rd.nextInt(100));
        }

        return resultList;
    }

    private static Integer determineMaxDifference(List<Integer> inputList){
        int upperBound = 0;
        int lowerBound = 100;
        for (int i = 0; i < inputList.size(); i++){
            int check = inputList.get(i);
            if (check > upperBound){
                upperBound = check;
            }
            if (check < lowerBound){
                lowerBound = check;
            }
        }

        return upperBound - lowerBound;
    }
}
