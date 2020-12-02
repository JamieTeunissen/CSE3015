package com.company;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        int[] tasks = { 8, 1, 8, 5,1,1,6,1,1 };
        int res = completeTasks(tasks, 4);

    }

    public static int completeTasks(int[] durations, int n) {
        PriorityQueue<Integer> taQueue = new PriorityQueue<Integer>();
        int i = 0;
        while(i < durations.length && i < n){
            taQueue.add(durations[i++]);
        }
        while (i < durations.length){
            int lowTime = taQueue.poll();
            taQueue.add(lowTime + durations[i++]);
        }
        int result = 0;
        while (!taQueue.isEmpty()){
            int checkTime =  taQueue.poll();
            if (checkTime > result){
                result = checkTime;
            }
        }

        return result;
    }
}
