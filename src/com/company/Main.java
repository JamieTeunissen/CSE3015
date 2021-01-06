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
        BinaryTree tree = null;
        boolean ans = isTreeAVL(tree);
        if(ans){
            int breakpoint;
        }
    }

    public static boolean isTreeAVL(BinaryTree tree) {
        if (isTreeBST(tree) && isTreeBalanced(tree)){
            return true;
        }
        return false;
    }


    public static boolean isTreeBalanced(BinaryTree tree) {
        if (tree == null){
            return true;
        }

        int hLeft = height(tree.getLeft());
        int hRight = height(tree.getRight());

        if (Math.abs(hLeft - hRight) > 1){
            return false;
        }

        return isTreeBalanced(tree.getLeft()) && isTreeBalanced(tree.getRight());
    }

    public static int height(BinaryTree tree){
        if(tree == null){
            return 0;
        }

        int left = height(tree.getLeft());
        int right = height(tree.getRight());
        int res;

        if (left > right){
            res = left;
        }else{
            res = right;
        }

        return 1 + res;
    }


    /**
     * Computes whether the BinaryTree is a binary search tree.
     *
     * @param tree
     *     the BinaryTree to check.
     * @return true iff the BinaryTree is a binary search tree, else false.
     */

    public static boolean isTreeBST(BinaryTree tree) {
        Deque<BinaryTree> stack = new ArrayDeque<BinaryTree>();
        BinaryTree current = tree;


        while(current.hasLeft()){
            stack.push(current);
            current = current.getLeft();
        }
        stack.push(current);

        int prev = current.getKey();

        while (!stack.isEmpty()){
            current = stack.pop();

            if (current.getKey() < prev){
                return false;
            }

            prev = current.getKey();

            if (current.hasRight()){
                current = current.getRight();
                stack.push(current);

                while (current.hasLeft()){
                    current = current.getLeft();
                    stack.push(current);
                }
            }
        }

        return true;
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
