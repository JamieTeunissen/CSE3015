package com.company;

import java.util.*;

public class Main {


    public static void main(String[] args) {
        String string = "5 3 6 + +";
        compute(string);
    }

    /**
     * Takes an array and sorts it in an non-decreasing order.
     * This has to be done by using coati sort.
     *
     * If the arr is null, the method should return null.
     *
     * @param arr Array that needs to be sorted.
     * @return The sorted array.
     */
    public static int compute(String expression) {
        Stack<Integer> stackInteger = new Stack<Integer>();
        Scanner sc = new Scanner(expression);

        int buffer = 0;
        boolean firstFlag = true;

        while(sc.hasNext()){
            if(sc.hasNextInt()){
                stackInteger.push(sc.nextInt());
            }else{
                if(firstFlag){
                    buffer = stackInteger.pop();
                    firstFlag = false;
                }
                String operation = sc.next();
                switch (operation){
                    case "+":
                        buffer = stackInteger.pop() + buffer;
                        break;
                    case "-":
                        buffer = buffer - stackInteger.pop() ;
                        break;
                    case "*":
                        buffer = stackInteger.pop() * buffer;
                        break;
                    case "/":
                        buffer = buffer / stackInteger.pop();
                    case "^":
                        buffer = buffer ^ stackInteger.pop() ;
                        break;
                    default:
                        throw new IllegalArgumentException();
                }
            }
        }
        sc.close();
        return buffer;
    }
}
