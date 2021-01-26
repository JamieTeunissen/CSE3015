package com.company;

import java.util.*;
import static java.util.Arrays.*;
import static org.junit.Assert.*;
import org.junit.*;



public class Main {
    @Test(timeout = 100)
    public void testEmpty() {
        assertEquals(new ArrayList<>(), radixSortLSD(new ArrayList<>()));
    }

    @Test(timeout = 100)
    public void testReversed() {
        List<String> data = asList("0687654321", "0612301345", "0612300123", "0612345678");
        List<String> data2 = asList("0612300123", "0612301345", "0612345678", "0687654321");
        assertEquals(data2, radixSortLSD(data));
    }

    /**
     * Sorts a list of Dutch mobile phone numbers using LSD radix sort.
     *
     * @param phoneNumbers
     *     The list of phone numbers to sort.
     * @return The sorted list of phone numbers.
     * @throws NullPointerException
     *     If `phoneNumbers` equals `null`.
     */
    static List<String> radixSortLSD(List<String> phoneNumbers) {
        if(phoneNumbers == null){
            throw new NullPointerException();
        }

        List<String> result = phoneNumbers;
        for (int i = 9; i > 1; i--){
            result = radixSortLSDHelper(result, i);
        }

        return result;
    }

    static List<String> radixSortLSDHelper(List<String> phoneNumbers, int pos) {
        Queue<String>[] buckets = new LinkedList[10];
        for (int i = 0; i < 10; i++) {
            buckets[i] = new LinkedList<String>();
        }

        List<String> result = new ArrayList<>(phoneNumbers.size());

        for (String phoneNumber : phoneNumbers){
            buckets[(Character.getNumericValue(phoneNumber.charAt(pos)))].add(phoneNumber);
        }

        for (int i = 0; i < 10; i++) {
            while (!buckets[i].isEmpty()){
                result.add(buckets[i].poll());
            }
        }

        return result;

    }

}
