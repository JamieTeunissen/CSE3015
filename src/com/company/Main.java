package com.company;

import java.util.*;
import static org.junit.Assert.*;
import org.junit.*;

class Heap {
    private int size = 0;
    private Heap.Node root;

    /**
     * Initializes a Heap with one Node.
     *
     * @param rootKey
     *     the key given to the root Node of the Heap.
     */
    public Heap(int rootKey) {
        root = new Heap.Node(rootKey);
        size++;
    }

    /**
     * @return the root Node of this Heap.
     */
    public Heap.Node getRoot() {
        return root;
    }

    /**
     * @param n
     *     The Node to get the left child from.
     * @return the left child of n.
     */
    public Heap.Node getLeft(Heap.Node n) {
        return n.left;
    }

    /**
     * @param n
     *     The Node to get the right child from.
     * @return the right child of n.
     */
    public Heap.Node getRight(Heap.Node n) {
        return n.right;
    }

    /**
     * @param n
     *     The Node to check the left child from.
     * @return true iff Node n has a left child, false otherwise.
     */
    public boolean hasLeft(Heap.Node n) {
        return n.left != null;
    }

    /**
     * @param n
     *     The Node to check the right child from.
     * @return true iff Node n has a right child, false otherwise.
     */
    public boolean hasRight(Heap.Node n) {
        return n.right != null;
    }

    /**
     * This method creates a new left child of n if it does not yet have a left child.
     *
     * @param n
     *     The Node to set the left child from.
     * @param leftKey
     *     The key to set in the left child of Node n.
     */
    public void setLeft(Heap.Node n, int leftKey) {
        if (n.left == null) {
            n.left = new Heap.Node(leftKey);
            size++;
        } else {
            n.left.key = leftKey;
        }
    }

    /**
     * This method creates a new right child of n if it does not yet have a right child.
     *
     * @param n
     *     The Node to set the right child from.
     * @param rightKey
     *     The key to set in the right child of Node n.
     */
    public void setRight(Heap.Node n, int rightKey) {
        if (n.right == null) {
            n.right = new Heap.Node(rightKey);
            size++;
        } else {
            n.right.key = rightKey;
        }
    }

    /**
     * @return The size of this Heap, i.e. the amount of Nodes.
     */
    public int size() {
        return size;
    }

    class Node {
        private int key;
        private Heap.Node left, right;

        /**
         * Simple constructor.
         *
         * @param key
         *     to set as key.
         */
        public Node(int key) {
            this.key = key;
        }

        /**
         * @return The integer key of this Node.
         */
        public int getKey() {
            return key;
        }

        @Override
        public String toString() {
            return key + "(" + (left == null ? " " : left) + ',' + (right == null ? " " : right) + ')';
        }
    }
}

public class Main {

    public static void main(String[] args) {
        Heap heap = new Heap(1);
        heap.setLeft(heap.getRoot(), 2);
        heap.setRight(heap.getRoot(), 3);
        heap.setLeft(heap.getLeft(heap.getRoot()), 4);
        heap.setRight(heap.getLeft(heap.getRoot()), 5);
        heap.setLeft(heap.getRight(heap.getRoot()), 6);
        heap.setRight(heap.getRight(heap.getRoot()), 7);
        assertEquals(7, findLastPosition(heap).getKey());
    }

    /**
     * @param heap
     *     the Heap to check, can be null. If not null, this heap will always contain at least one Node.
     * @return the Node corresponding to the last position in the Heap, or null if the Heap is null.
     */
    public static Heap.Node findLastPosition(Heap heap) {
        int nNodes = heap.size();
        int mask = 1;
        Heap.Node node = heap.getRoot();


        while(nNodes != 1){
            int check = mask & nNodes;
            if(check == 1){
                node = heap.getRight(node);
            }else{
                node = heap.getLeft(node);
            }
            nNodes = nNodes >> 1;
        }

        return node;
    }



}
