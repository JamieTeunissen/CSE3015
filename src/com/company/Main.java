package com.company;

import java.util.*;

import static org.junit.Assert.*;
import org.junit.*;

public class Main {

    class Edge implements Comparable<Edge> {
        private int from;
        private int to;
        private int cost;

        public Edge(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }

        public int getFrom() {
            return from;
        }

        public int getTo() {
            return to;
        }

        public int getCost() {
            return cost;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.cost, o.cost);
        }

        @Override
        public String toString() {
            return "Edge{" +
                    "from=" + from +
                    ", to=" + to +
                    ", cost=" + cost +
                    '}';
        }
    }

    static class UnionFind {

        private int[] parent;

        private int[] rank;

        // Union Find structure implemented with two arrays for Union by Rank
        public UnionFind(int size) {
            parent = new int[size];
            rank = new int[size];
            for (int i = 0; i < size; i++) parent[i] = i;
        }

        /**
         * Merge two clusters, if they are not already part of the same cluster.
         *
         * @param i a node in the first cluster
         * @param j a node in the second cluster
         * @return true iff i and j had different clusters.
         */
        boolean union(int i, int j) {
            int parent1 = find(i);
            int parent2 = find(j);
            if (parent2 == parent1)
                return false;
            if (rank[parent1] > rank[parent2]) {
                parent[parent2] = parent1;
            } else if (rank[parent2] > rank[parent1]) {
                parent[parent1] = parent2;
            } else {
                parent[parent2] = parent1;
                rank[parent1]++;
            }
            return true;
        }

        /**
         * NB: this function should also do path compression
         * @param i index of a node
         * @return the root of the subtree containg i.
         */
        int find(int i) {
            int parent = this.parent[i];
            if (i == parent) {
                return i;
            }
            return this.parent[i] = find(parent);
        }

        // Return the rank of the trees
        public int[] getRank() {
            return rank;
        }

        // Return the parent of the trees
        public int[] getParent() {
            return parent;
        }
    }

    /**
     * Tests if solution works for the given input.
     * Important because there might be different possible MST's for the same graph.
     *
     * @param mst_cost the real cost of a MST on that graph
     * @param edges the edges that comprise of the graph whose MST we are creating
     * @param n the amount of nodes in the graph that the MST should reach
     */
    void assertMST(int mst_cost, List<Edge> edges, int n) {
        // Run solution (& verify that input was not modified)
        List<Edge> original_edges = new ArrayList<>(edges);
        List<Edge> solution = buildMST(n, edges);
        assertEquals("You should not modify the original input!", original_edges, edges);

        // Test if it even is a spanning tree
        UnionFind uf = new UnionFind(n);
        // Do we have n - 1 edges?
        assertEquals("A spanning tree would have " + (n - 1) + " edges instead of " + solution.size() + "!"
                , n - 1, solution.size());
        // Are they all useful? (Do they connect different clusters?)
        for (Edge e : solution)
            assertTrue(uf.union(e.getFrom(), e.getTo()));

        // Test if it is a minimum spanning tree
        int result_cost = solution.stream().mapToInt(Edge::getCost).sum();
        assertEquals(mst_cost, result_cost);
    }

    List<Edge> buildEdges(int[] info, int m) {
        List<Edge> solution = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            int start = i * 3;
            solution.add(new Edge(info[start], info[start + 1], info[start + 2]));
        }

        return solution;
    }

    @Test(timeout = 100)
    public void emptyGraphTest() {
        List<Edge> solution = buildMST(0, new ArrayList<>());
        assertTrue("An empty graph should have an empty MST.", solution.isEmpty());
    }

    /**
     * Makes sure they don't just add the smallest cost edge.
     * Makes sure they don't skip necessary edges (most costly edge is necessary)
     *
     * Graph: https://i.imgur.com/BdvaXpV.png
     * MST: https://i.imgur.com/RS9poLa.png
     */
    @Test(timeout = 100)
    public void completeGraphTest() {
        int[] info = new int[] {
                0, 1, 1,
                0, 2, 1,
                1, 2, 2,
                1, 3, 3,
                3, 4, 2,
                3, 5, 1,
                4, 5, 1
        };

        List<Edge> input = buildEdges(info, 7);

        assertMST(7, input, 6);
    }

    public static void main(String[] args){
    }

    /**
     * Builds a Minimum Spanning Tree (MST) using
     * Kruskal's Algorithm (as learned in class).
     * Nodes are numbered from 0 to n - 1.
     *
     * @param n the amount of nodes in the graph
     * @param edges the edges that comprise the graph
     * @return the list of edges that should be included in the MST
     */
    public static List<Edge> buildMST(int n, List<Edge> edges) {
        PriorityQueue<Edge> prioQ = new PriorityQueue<Edge>();
        UnionFind uf = new UnionFind(n);
        List<Edge> result = new LinkedList<Edge>();
        prioQ.addAll(edges);

        while (!prioQ.isEmpty()){
            Edge selectedEdge = prioQ.poll();
            if(uf.union(selectedEdge.getFrom(), selectedEdge.getTo())){
                result.add(selectedEdge);
            }
        }

        return result;
    }

}
