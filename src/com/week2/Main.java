package com.week2;

import java.util.*;

class ArrayStack {
    private Object[] elements;
    private int size;
    private int capacity;

    /**
     * Creates an empty ArrayStack with capacity 1.
     */
    public ArrayStack() {
        capacity = 1;
        size = 0;
        elements = new Object[capacity];
    }

    /**
     * @return The size of this ArrayStack.
     */
    public int size() {
        return size;
    }

    /**
     * @return `true` iff this ArrayStack is empty, `false` otherwise.
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * @return `true` iff the size is equal to the capacity, `false` otherwise.
     */
    public boolean isFull() {
        return size == capacity;
    }

    /**
     * @return the top element of the stack without removing it
     */
    public Object peek() throws EmptyStackException {
        if (isEmpty()){
            throw new EmptyStackException();
        }
        return elements[0];
    }

    /**
     * Adds `o` to the stack.
     * If capacity of stack was too small, capacity is doubled and `o` is added.
     *
     * @param o
     *     the element to add to the stack.
     */
    public void push(Object o) {
        if(isFull()){
            Object [] backup = new Object[capacity];
            for(int i = 0; i < capacity; i++){
                backup[i] = elements[i];
            }
            elements = new Object[capacity*2];
            for(int i = 0; i < capacity; i++){
                elements[i] = backup[i];
            }
            capacity = capacity*2 ;
        }
        elements[size] = o;
        size++;
    }

    /**
     * Removes the top element from the stack.
     * If removing top would make the stack use less than 25% of its capacity,
     * then the capacity is halved.
     *
     * @return the element which was at the top of the stack.
     * @throws EmptyStackException
     *     iff the stack is empty
     */
    public Object pop() throws EmptyStackException {
        if(isEmpty()){
            throw new EmptyStackException();
        }

        Object res = peek();

        size--;

        for(int i = 0; i < size; i++){
            elements[i] = elements[i+1];
        }

        if(size < capacity/4){
            if(capacity/2 < 1){
                elements = new Object[1];
                capacity = 1;
            }else{
                Object [] backup = new Object[capacity/2];
                for(int i = 0; i < capacity/2; i++){
                    backup[i] = elements[i];
                }
                capacity = capacity/2;
                elements = new Object[capacity];
                for(int i = 0; i < capacity; i++){
                    elements[i] = backup[i];
                }
            }
        }

        return res;
    }

    /**
     * @return a String representation of the ArrayStack
     * Example output for ArrayStack with 2 elements and capacity 5:
     * <ArrayStack[1,2]>(Size=2, Cap=5)
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < size-1; i++){
            s.append(elements[i]).append(',');
        }
        s.append(elements[size - 1]);
        return  "<ArrayStack[" + s + "]>(Size=" + size + ", Cap=" + capacity + ")";
    }

    // For testing, do not remove or change.
    public Object[] getElements() {
        return elements;
    }
}

public class Main {
    public static void main(String[] args) {
        ArrayStack s = new ArrayStack();
        s.push("324");
    }
}
