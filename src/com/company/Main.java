package com.company;

import java.util.*;
import static org.junit.Assert.*;
import org.junit.*;

class Entry {
    private String key;
    private String value;

    public Entry(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
}

class SolutionHashTable {
    public LinkedList<Entry>[] table;
    public int capacity;

    /**
     * Constructs a new HashTable.
     *
     * Capacity of the hash table can not be 0 or negative.
     *
     * @param capacity
     *     to be used as capacity of the table.
     * @throws IllegalArgumentException
     *     if the input capacity is invalid.
     */
    @SuppressWarnings("unchecked")
    public SolutionHashTable(int capacity) {
        if(capacity <= 0){
            throw new IllegalArgumentException();
        }

        this.table = new LinkedList[capacity];
        this.capacity = capacity;
    }

    /**
     * Add a new Entry to the hash table,
     * uses separate chaining to deal with collisions.
     *
     * Returns false, if the key is null.
     *
     * @param key
     *     String representing the key of the entry.
     * @param value
     *     String representing the value of the entry.
     * @return true iff entry has been added successfully, else false.
     */
    public boolean put(String key, String value) {
        if(key == null){
            return false;
        }
        Entry input = new Entry(key, value);

        if(table[hash(key)] == null){
            table[hash(key)] = new LinkedList<Entry>();
            table[hash(key)].add(input);
            return true;
        }

        ListIterator<Entry> iterator = table[hash(key)].listIterator(0);

        while(iterator.hasNext()){
            Entry check = iterator.next();
            if(check.getKey() == key){
                table[hash(key)].remove(check);
            }
        }

        table[hash(key)].add(input);
        return true;
    }

    /**
     * Retrieve the value of the entry associated with this key.
     *
     * Returns null, if the key is null.
     *
     * @param key
     *     String representing the key of the entry to look for.
     * @return value of the entry as String iff the entry with this key is found in the hash table, else null.
     */
    public String get(String key) {
        if(key == null){
            return null;
        }

        if(table[hash(key)] == null){
            return null;
        }

        if(table[hash(key)].size() == 1){
            if(table[hash(key)].peek().getKey() == key){
                return table[hash(key)].peek().getValue();
            }
            return null;
        }

        Iterator<Entry> iterator = table[hash(key)].descendingIterator();

        while(iterator.hasNext()){
            Entry check = iterator.next();
            if(check.getKey() == key){
                return check.getValue();
            }
        }

        return null;
    }

    /**
     * Remove the entry associated with this key from the hash table.
     *
     * Returns false, if the key is null.
     *
     * @param key
     *     String representing the key of the entry to remove.
     * @return true iff the entry has been successfully removed, else false.
     */
    public boolean remove(String key) {
        if(key == null){
            return false;
        }

        if(table[hash(key)] == null){
            return false;
        }

        if(table[hash(key)].size() == 1){
            if(table[hash(key)].peek().getKey() == key){
                table[hash(key)].remove();
                return true;
            }
            return false;
        }

        if(table[hash(key)].size() == 1){
            table[hash(key)].remove();
            return true;
        }

        Iterator<Entry> iterator = table[hash(key)].descendingIterator();

        while(iterator.hasNext()){
            Entry check = iterator.next();
            if(check.getKey() == key){
                table[hash(key)].remove(check);
                return true;
            }
        }
        return false;
    }

    /**
     * Hashes a string representing a key
     *
     * @param key
     *     String that needs to be hashed.
     * @return the hashcode of the string, modulo the capacity of the HashTable.
     */
    public int hash(String key) {
        return Math.abs(key.hashCode()) % capacity;
    }
}


public class Main {

    public static void main(String[] args) {
        SolutionHashTable tab = new SolutionHashTable(1);
        assertTrue(tab.put("apple", "juice"));
        assertTrue(tab.put("peer", "sap"));
        assertEquals("juice", tab.get("apple"));
        assertEquals(true, tab.remove("apple"));
        assertEquals(null, tab.get("apple"));
    }

}
