package com.company;

import java.util.*;
import static org.junit.Assert.*;
import org.junit.*;

class MultiMap {

    private Map<Integer, List<Integer>> map;

    /**
     * Creates a new MultiMap.
     */
    public MultiMap() {
        this.map = new HashMap<Integer, List<Integer>>();
    }

    /**
     * @return The number of (key, value) pairs in the MultiMap.
     */
    public int size() {
        Iterator<List<Integer>> iterator = this.map.values().iterator();
        int count = 0;
        while (iterator.hasNext()){
            List<Integer> select = iterator.next();
            count += select.size();
        }
        return count;
    }

    /**
     * @return True if the MultiMap is empty, false otherwise.
     */
    public boolean isEmpty() {
        if (size() > 0){
            return false;
        }
        return true;
    }

    /**
     * Adds the given (key, value) pair to the MultiMap.
     *
     * @param key Key for the new item.
     * @param value New item to add to the MultiMap.
     */
    public void put(int key, int value) {
        if (map.get(key) == null) {
            map.put(key, new LinkedList<Integer>());
        }
        map.get(key).add(value);
    }

    /**
     * Returns all values in the MultiMap for the given key.
     *
     * @param key Key to return all entries for.
     * @return A list of all entries for the given key.
     *         If the key is not in the map, return an empty list.
     */
    public List<Integer> get(int key) {
        if (map.get(key) != null) {
            return map.get(key);
        }
        return new LinkedList<Integer>();
    }

    /**
     * Removes the given (key, value) pair from the MultiMap.
     *
     * @param key Key for the value that should be removed.
     * @param value Value to remove.
     * @return True if removal was successful, false otherwise.
     */
    public boolean remove(int key, int value) {
        if (map.get(key).contains(value)) {
            map.get(key).remove(map.get(key).indexOf(value));
            return true;
        }
        return false;
    }
}




public class Main {

    public static void main(String[] args) {
        MultiMap map = new MultiMap();
        map.put(1, 2);
        map.put(1, 2);
        assertFalse(map.isEmpty());
        assertEquals(2, map.size());
        List<Integer> result = map.get(1);
//        assertEquals(Collections.singletonList(2), map.get(1));
//        assertFalse(map.remove(1, 2));
        assertTrue(map.remove(1, 2));
        assertEquals(0, map.size());
    }

}
