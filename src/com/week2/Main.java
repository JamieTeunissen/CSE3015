package com.week2;
import java.util.LinkedList;
import java.util.List;

class Queue<T> {
    private List<T> list = new LinkedList<T>();

    public Queue() {

    }

    /**
     * @return true iff the queue contains no elements.
     */
    public boolean isEmpty() {
        if(list.size() == 0){
            return true;
        }
        return false;
    }

    /**
     * Adds an element to the front of the queue.
     *
     * @param element
     *     to add.
     */
    public void enqueue(T element) {
        list.add(element);
    }

    /**
     * Removes and returns the element at the front of the queue.
     *
     * @return the element at the front of the dequeue
     * @throws EmptyQueueException
     *     iff the queue is empty
     */
    public T dequeue() {
        if (isEmpty()){
            return null;
        }else{
            T element = list.get(0);
            list.remove(0);
            return element;
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Queue<Integer> queue = new Queue<>();
        queue.isEmpty();
        queue.enqueue(5);
        queue.isEmpty();
        System.out.println(queue.dequeue());
    }
}
