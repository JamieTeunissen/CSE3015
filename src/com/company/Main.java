package com.company;

import java.util.*;

public class Main {

    static class Vertex {

        private int id;

        private Set<Vertex> neighbours;

        public Vertex(int id) {
            this.id = id;
            neighbours = new HashSet<>();
        }

        public int getId() {
            return id;
        }

        public void addNeighbour(Vertex v) {
            neighbours.add(v);
        }

        @Override
        public String toString() {
            return "<vertex: " + id + ">";
        }

        public Collection<Vertex> getNeighbours() {
            return new ArrayList<>(this.neighbours);
        }

        @Override
        public boolean equals(Object o) {
            return o instanceof Vertex && ((Vertex) o).getId() == this.getId();
        }
    }

    static class Graph {

        private Map<Integer, Vertex> vertices = new HashMap<>();

        /**
         * Creates a new graph with n vertices.
         *
         * @param n Amount of vertices in the graph.
         */
        public Graph(int n) {
            for (int i = 0; i < n; i++) vertices.put(i, new Vertex(i));
        }

        /**
         * Returns the vertex with the given ID.
         *
         * @param id The ID for the vertex to get.
         * @return The vertex with the given ID.
         * @throws IllegalArgumentException if no vertex with the given ID is in the graph.
         */
        public Vertex getVertex(int id) throws IllegalArgumentException {
            if (!vertices.containsKey(id))
                throw new IllegalArgumentException("Vertex not in graph");
            return vertices.get(id);
        }

        public Collection<Vertex> getAllVertices() {
            return new ArrayList<>(this.vertices.values());
        }

        /**
         * Adds an edge between vertex u and v with the given weight.
         *
         * @param u      First vertex.
         * @param v      Second vertex.
         * @param weight Weight of the edge between a and b.
         */
        public void addEdge(Vertex u, Vertex v) {
            u.addNeighbour(v);
            v.addNeighbour(u);
        }

        /**
         * Adds an edge between the vertices with IDs u and v with the given weight.
         *
         * @param u      ID of the first vertex.
         * @param v      ID of the second vertex.
         * @param weight Weight of the edge between a and b.
         * @throws IllegalArgumentException if no vertex with the given ID is in the graph.
         */
        public void addEdge(int u, int v) throws IllegalArgumentException {
            addEdge(getVertex(u), getVertex(v));
        }
    }

    public static void main(String[] args) {
        Graph g = new Graph();
        Vertex v = new Vertex(0);
        Vertex w = new Vertex(1);
        g.addVertex(v);
        g.addVertex(w);
        g.addEdge(v, w);
        Iterator<Vertex> it = new GraphIterator(g, v);
    }

    public static boolean solve(Vertex a, Vertex b) {
        Queue<Vertex> queue = new LinkedList<Vertex>();
        List<Vertex> knownNodes = new LinkedList<Vertex>();
        Vertex checkVertex;
        Vertex nextVertex;
        Iterator<Vertex> iteratorVertices;
        queue.add(a);

        while(!queue.isEmpty()){
            checkVertex = queue.poll();
            if (checkVertex == b){
                return true;
            }
            knownNodes.add(checkVertex);
            iteratorVertices = checkVertex.getNeighbours().iterator();
            while(iteratorVertices.hasNext()){
                nextVertex = iteratorVertices.next();
                if(!knownNodes.contains(nextVertex)){
                    queue.add(nextVertex);
                }
            }
        }
        return false;
    }

   
}
