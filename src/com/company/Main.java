package com.company;

import java.util.*;

public class Main {


    static class Node {

        List<Node> outgoingEdges;

        int id;

        public Node(int id) {
            this.outgoingEdges = new ArrayList<>();
            this.id = id;
        }

        public List<Node> getOutgoingEdges() {
            return outgoingEdges;
        }

        public int getId() {
            return id;
        }

        public String toString() {
            return Integer.toString(id);
        }

        @Override
        public int hashCode() {
            return id;
        }
    }

    public static void main(String[] args) {
        Node[] nodes = new Node[4];
        for (int i = 0; i < nodes.length; i++)
            nodes[i] = new Node(i);
        nodes[0].outgoingEdges.add(nodes[1]);
        nodes[0].outgoingEdges.add(nodes[2]);
        nodes[2].outgoingEdges.add(nodes[3]);
        nodes[3].outgoingEdges.add(nodes[1]);

        boolean result = detectCycle(nodes[0], Arrays.asList(nodes.clone()));
    }

    public static boolean detectCycle(Node s, List<Node> nodes) {
        Iterator<Node> nodeIterator = s.getOutgoingEdges().iterator();

        while(nodeIterator.hasNext()) {
            Node nextNode = nodeIterator.next();
            List<Node> l = new ArrayList<Node>();
            l.add(s);
            if (detectCycleHelper(nextNode, l)) {
                return true;
            }
        }
        return false;
    }

    public static boolean detectCycleHelper(Node s, List<Node> knownNodes) {
        Iterator<Node> nodeIterator = s.getOutgoingEdges().iterator();
        while(nodeIterator.hasNext()){
            Node nextNode = nodeIterator.next();
            if (knownNodes.contains(nextNode)){
                return true;
            }
            knownNodes.add(nextNode);
            return detectCycleHelper(nextNode, knownNodes);
        }
        return false;
    }
}
