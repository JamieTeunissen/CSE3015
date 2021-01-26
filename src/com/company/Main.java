package com.company;

import java.util.*;
import static org.junit.Assert.*;
import org.junit.*;

public class Main {

    static class BinaryTree {

        private int value;

        private BinaryTree left, right;

        private boolean isRed;

        /**
         * Simple constructor.
         *
         * @param value Value for this tree set as value.
         * @param isRed True if this node is red, false otherwise.
         */
        public BinaryTree(int value, boolean isRed) {
            this.value = value;
            this.isRed = isRed;
        }

        /**
         * Extended constructor.
         *
         * @param value to set as value.
         * @param left to set as left child.
         * @param right to set as right child.
         */
        public BinaryTree(int value, boolean isRed, BinaryTree left, BinaryTree right) {
            this(value, isRed);
            setLeft(left);
            setRight(right);
        }

        /**
         * @return the value of this tree.
         */
        public int getValue() {
            return value;
        }

        /**
         * @param value the new value of this tree.
         */
        public void setValue(int value) {
            this.value = value;
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

        /**
         * @return true if this node is red, false otherwise.
         */
        public boolean isRed() {
            return isRed;
        }

        /**
         * @return true if this node is black, false otherwise.
         */
        public boolean isBlack() {
            return !isRed;
        }

        /**
         * @return True if the tree has a left child, false otherwise.
         */
        public boolean hasLeft() {
            return left != null;
        }

        /**
         * @return True if the tree has a right child, false otherwise.
         */
        public boolean hasRight() {
            return right != null;
        }

        /**
         * @param left Left subtree to set.
         */
        public void setLeft(BinaryTree left) {
            this.left = left;
        }

        /**
         * @param right Right subtree to set.
         */
        public void setRight(BinaryTree right) {
            this.right = right;
        }

        /**
         * @param red True if the new color is red, false otherwise.
         */
        public void setRed(boolean red) {
            isRed = red;
        }
    }


    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree(4, false);
        assertTrue(isRedBlackTree(tree));
        tree.setLeft(new BinaryTree(2, true));
        tree.setRight(new BinaryTree(6, false));
//        assertTrue(isRedBlackTree(tree));
        tree.getLeft().setLeft(new BinaryTree(1, false));
        tree.setRight(null);
        assertTrue(isRedBlackTree(tree));
    }

    /**
     * Checks whether the given BinaryTree is a Red Black Tree.
     * @param tree BinaryTree to check.
     * @return True if the given tree is a Red Black Tree, false otherwise.
     */
    public static boolean isRedBlackTree(BinaryTree tree) {
        if(tree.isRed()){
            return false;
        }

        if(!(isBST(tree.getLeft(), 0, tree.getValue()-1) && isBST(tree.getRight(), tree.getValue()+1, 100000))){
            return false;
        }

        if(!((checkBlackDepth(tree.getLeft()) == checkBlackDepth(tree.getRight())) && (checkBlackDepth(tree.getLeft()) >= 0 && checkBlackDepth(tree.getRight()) >= 0))){
            return false;
        }

        if(!checkLeafBlack(tree)){
            return false;
        }

        return true;
    }

    public static boolean isBST(BinaryTree tree, int min, int max){
        if(tree == null){
            return true;
        }
        if(tree.getValue() < min || tree.getValue() > max){
            return false;
        }
        return (isBST(tree.getLeft(), min, tree.getValue()-1) && isBST(tree.getRight(), tree.getValue()+1, max));
    }

    public static int checkBlackDepth(BinaryTree tree){
        if(tree == null){
            return 1;
        }
        int b = 0;
        if(tree.isBlack()){b=1;}

        int left = b + checkBlackDepth(tree.getLeft());
        int right = b + checkBlackDepth(tree.getRight());

        if (left == -1 || right == -1 || left != right){
            return -1;
        }

        return left;

    }

    public static boolean checkLeafBlack(BinaryTree tree){
        if(tree.getLeft() == null && tree.getRight() == null){
            if(tree.isRed()){
                return false;
            }
            return true;
        }else if(tree.getLeft() == null){
            return checkLeafBlack(tree.getRight());
        }else if(tree.getRight() == null){
            return checkLeafBlack(tree.getLeft());
        }

        return (checkLeafBlack(tree.getRight()) && checkLeafBlack(tree.getLeft()));
    }




}
