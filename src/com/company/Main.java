package com.company;

import java.util.*;
import static org.junit.Assert.*;
import org.junit.*;

public class Main {


    class LibraryTree {
        private int key;
        private List<LibraryTree> children;

        public LibraryTree(int key) {
            this.key = key;
            children = new ArrayList<>();
        }

        public LibraryTree(int key, List<LibraryTree> children) {
            this.key = key;
            this.children = children;
        }

        public int getKey() {
            return this.key;
        }

        public List<LibraryTree> getChildren() {
            return this.children;
        }
    }

    @Test
    public void testNull() {
        LibraryTree tree = null;
        assertEquals(0, countNodesEvenChildren(tree));
    }

    @Test
    public void testRoot() {
        LibraryTree tree = new LibraryTree(0);
        assertEquals(1, countNodesEvenChildren(tree));
    }

    @Test
    public void testSmall() {
        LibraryTree tree = new LibraryTree(0, Arrays.asList(new LibraryTree(1), new LibraryTree(2)));
        assertEquals(3, countNodesEvenChildren(tree));
    }

    public static void main(String[] args) {

    }

    /**
     * Counts the number of nodes with an event number of children.
     *
     * @param tree
     *     The tree to count nodes with an even number of children in.
     * @return the number of nodes with an even number of children, or 0 if tree is null.
     */
    public static int countNodesEvenChildren(LibraryTree tree) {
        if(tree == null){
            return 0;
        }

        int count = 0;
        Iterator<LibraryTree> childrenIter = tree.getChildren().iterator();

        while(childrenIter.hasNext()){
            count += countNodesEvenChildren(childrenIter.next());
        }

        if((tree.getChildren().size() % 2) == 0){
            return 1 + count;
        }

        return count;
    }



}
