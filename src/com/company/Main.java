package com.company;

import java.util.*;

public class Main {

    static class BinaryTree {

        private int key;

        private BinaryTree left, right;

        /**
         * Simple constructor.
         *
         * @param key
         *     to set as key.
         */
        public BinaryTree(int key) {
            this.key = key;
        }

        /**
         * Extended constructor.
         *
         * @param key
         *     to set as key.
         * @param left
         *     to set as left child.
         * @param right
         *     to set as right child.
         */
        public BinaryTree(int key, BinaryTree left, BinaryTree right) {
            this.key = key;
            setLeft(left);
            setRight(right);
        }

        public int getKey() {
            return key;
        }

        /**
         * @return the left child.
         */
        public BinaryTree getLeft() {
            return left;
        }

        /**
         * @return the right child.
         */
        public BinaryTree getRight() {
            return right;
        }

        public boolean hasLeft() {
            return left != null;
        }

        public boolean hasRight() {
            return right != null;
        }

        /**
         * @param left
         *     to set
         */
        public void setLeft(BinaryTree left) {
            this.left = left;
        }

        /**
         * @param right
         *     to set
         */
        public void setRight(BinaryTree right) {
            this.right = right;
        }
    }

    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree(4, new BinaryTree(2, new BinaryTree(1), new BinaryTree(3)), new BinaryTree(6, new BinaryTree(5), new BinaryTree(7)));
        descendingOrder(tree);
    }

    /**
     * Return all elements in the given BST in descending order.
     * @param tree The BST to traverse.
     * @return A list of all elements in reverse order.
     */
    public static List<Integer> descendingOrder(BinaryTree tree) {
        List<Integer> result = new ArrayList<>();
        Deque<BinaryTree> stack = new ArrayDeque<BinaryTree>();
        BinaryTree current = tree;

        while(current.hasRight()){
            stack.push(current);
            current = current.getRight();
        }

        stack.push(current);

        while (!stack.isEmpty()){
            current = stack.pop();
            result.add(current.getKey());

            if (current.hasLeft()){
                current = current.getLeft();
                stack.push(current);

                while (current.hasRight()){
                    current = current.getRight();
                    stack.push(current);
                }
            }
        }

        return result;
    }

}
