package com.company;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        MyQueue<Integer> queue1 = new MyQueue<>();
        queue1.enqueue(4);
        queue1.enqueue(2);
        queue1.enqueue(1);

        MyQueue<Integer> queue2 = new MyQueue<>();
        queue2.enqueue(6);
        queue2.enqueue(5);
        queue2.enqueue(3);
        queue2.enqueue(2);

        MyQueue<Integer> solution = merge(queue1, queue2);
    }

    public static MyQueue<Integer> merge(MyQueue<Integer> queue1, MyQueue<Integer> queue2) {
        MyQueue<Integer> result = new MyQueue<Integer>();
        MyQueue<Integer> q1 = queueCopy(queue1);
        MyQueue<Integer> q2 = queueCopy(queue2);
        int size = q1.size() + q2.size();

        for(int i = 0; i < size; i++){
            if(q1.front() != null && q2.front() != null){
                if(q1.front() > q2.front()){
                    result.enqueue(q1.dequeue());
                }else{
                    result.enqueue(q2.dequeue());
                }
            }else{
                break;
            }
        }

        while(q1.front() != null){
            result.enqueue(q1.dequeue());
        }

        while(q2.front() != null){
            result.enqueue(q2.dequeue());
        }
        return result;
    }

    private static MyQueue<Integer> queueCopy(MyQueue<Integer> queue){
        MyQueue<Integer> result = new MyQueue<Integer>();
        while (result.size() != queue.size()){
            int buffer = queue.dequeue();
            result.enqueue(buffer);
            queue.enqueue(buffer);
        }
        return result;
    }

    static class MyQueue<T> {

        private LinkedList<T> q;

        public MyQueue() {
            this.q = new LinkedList<>();
        }

        public void enqueue(T e) {
            q.add(e);
        }

        public T dequeue() {
            return q.poll();
        }

        public int size() {
            return q.size();
        }

        public boolean isEmpty() {
            return q.isEmpty();
        }

        public T front() {
            return q.peek();
        }
    }
}
