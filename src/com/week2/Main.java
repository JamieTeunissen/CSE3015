package com.week2;

import java.util.*;
/**
 * Iterates lazily over lists in reversed order. For instance, the list
 * [1,2,3,4] should be iterated as follows: 4 -> 3 -> 2 -> 1.
 */
class ReversedListIterator<V> implements Iterator<V> {

    private int iterator;
    private List<V> lst;
    /**
     * Constructor.
     * Should reset on a new List.
     *
     * @param list
     *     takes the list
     */
    public ReversedListIterator(List<V> list) {
        lst = list;
        iterator = lst.size();
    }

    /**
     * @return True if there is a next element in the iterator, else False
     */
    @Override
    public boolean hasNext() {
        return iterator != 0;
    }

    /**
     * Get the next element of the iterator and shift
     * iterator by one.
     *
     * @return current element value
     * @post iterator is moved to next element
     * @throws NoSuchElementException
     *      iff there is no next element
     */
    @Override
    public V next() throws NoSuchElementException {
        if (!hasNext()){
            throw new NoSuchElementException();
        }
        iterator--;
        V result = lst.get(iterator);
        return result;
    }

    /**
     * Skip a single element of the iterator.
     *
     * @post iterator is moved to next element
     * @throws NoSuchElementException
     *      iff there is no elemented to be removed
     */
    @Override
    public void remove() throws NoSuchElementException {
        if(!hasNext()){
            throw new NoSuchElementException();
        }
        iterator--;
    }
}


public class Main {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<Integer>();
        list.add(5);
        list.add(2);
        list.add(4);
        list.add(7);
        ReversedListIterator iterator = new ReversedListIterator(list);
        System.out.println(iterator.hasNext());
        System.out.println(iterator.next());
        System.out.println(iterator.next());
        System.out.println(iterator.next());
        System.out.println(iterator.next());
    }
}
