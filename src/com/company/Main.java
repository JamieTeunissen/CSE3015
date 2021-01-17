package com.company;

import java.util.*;

public class Main {

    interface Vertex extends Comparable<Vertex> {
        int getId();
    }

    /**
     * Interface for a generic graph. You may assume that our implementation is an
     * undirected graph.
     */
    interface Graph {
        /**
         * Returns the neighbours in a sorted collection by id
         *
         * @param v
         *     node to get the neighbours of.
         * @return sorted collection of neighbours.
         */
        List<Vertex> getNeighbours(Vertex v);

        /**
         * @return an unsorted collection of all vertices in the graph.
         */
        Collection<Vertex> getAllVertices();
    }

    static class VertexImpl implements Vertex {
        private int id;
        private Set<Vertex> neighbours;

        public VertexImpl(int id) {
            this.id = id;
            neighbours = new HashSet<>();
        }

        public void addNeighbour(Vertex v) {
            neighbours.add(v);
        }

        @Override
        public String toString() {
            return "<Node: " + id + ">";
        }

        @Override
        public int getId() {
            return id;
        }

        public Collection<Vertex> getNeighbours() {
            return new ArrayList<>(this.neighbours);
        }

        @Override
        public int compareTo(Vertex o) {
            return this.getId() - o.getId();
        }

        @Override
        public int hashCode() {
            return this.getId();
        }

        @Override
        public boolean equals(Object o) {
            return o instanceof Vertex && ((Vertex) o).getId() == this.getId();
        }
    }

    static class GraphImpl implements Graph {
        private Map<Integer, Vertex> vertices;

        public GraphImpl() {
            this.vertices = new HashMap<>();
        }

        public void addVertex(Vertex v) {
            this.vertices.put(v.getId(), v);
        }

        @Override
        public Collection<Vertex> getAllVertices() {
            return this.vertices.values();
        }

        @Override
        public List<Vertex> getNeighbours(Vertex v) {
            List<Vertex> neigh = new ArrayList<>(((VertexImpl) v).getNeighbours());
            Collections.sort(neigh);
            return neigh;
        }

        public void addEdge(Vertex v, Vertex w) {
            VertexImpl realV = (VertexImpl) v;
            VertexImpl realW = (VertexImpl) w;
            realV.addNeighbour(w);
            realW.addNeighbour(v);
        }
    }

    /**
     * Implements a BFS traversal of the Graph starting at a certain vertex v. It
     * should return nodes at most once.
     */
    static class GraphIterator implements Iterator<Vertex> {
        private Graph g;
        private Vertex v;
        private Queue<Vertex> queue;
        private Set<Integer> colored;

        public GraphIterator(Graph g, Vertex v) {
            this.g = g;
            this.v = v;
            this.reset();
        }

        public void reset() {
            this.queue = new LinkedList<>();
            this.colored = new HashSet<>();
            if (v != null && g != null) {
                this.queue.add(v);
                this.colored.add(v.getId());
            }
        }

        @Override
        public boolean hasNext() {
            return !queue.isEmpty();
        }

        @Override
        public Vertex next() {
            if (this.queue.isEmpty()) {
                return null;
            }
            Vertex u = queue.poll();
            for (Vertex n : g.getNeighbours(u)) {
                if (!colored.contains(n.getId())) {
                    colored.add(n.getId());
                    queue.add(n);
                }
            }
            return u;
        }

        @Override
        public void remove() {
            // Can be ignored
        }
    }


    public static void main(String[] args) {
        GraphImpl g = new GraphImpl();
        Vertex v = new VertexImpl(0);
        Vertex n1 = new VertexImpl(1);
        Vertex n2 = new VertexImpl(2);
        Vertex n3 = new VertexImpl(3);
        Vertex n4 = new VertexImpl(4);
        Vertex w = new VertexImpl(5);
        g.addVertex(v);
        g.addVertex(w);
        g.addVertex(n1);
        g.addVertex(n2);
        g.addVertex(n3);
        g.addVertex(n4);
        g.addEdge(v, w);
        g.addEdge(n1, n2);
        g.addEdge(n1, n3);
        g.addEdge(n3, n4);
//        g.addEdge(n4, w);
        int num = numberOfConnectedComponents(g);
    }

    public static int numberOfConnectedComponents(Graph g) {
        Collection<Vertex> unexplored = g.getAllVertices();
        Vertex startVertex = unexplored.iterator().next();
        Iterator<Vertex> graphIterator = new GraphIterator(g, startVertex);
//        Iterator<Vertex> neighboursIterator;
        int result = 1;

        while (graphIterator.hasNext()){
            startVertex = graphIterator.next();
//            if (g.getNeighbours(startVertex).size() == 0){
//                result++;
//            }
//
//            neighboursIterator = g.getNeighbours(startVertex).iterator();
//            while (neighboursIterator.hasNext()){
//                Vertex selectVertex = neighboursIterator.next();
//                if(unexplored.contains(selectVertex)){
//                    result++;
//                }
//            }
            unexplored.remove(startVertex);

            if (!graphIterator.hasNext() && unexplored.size() > 0){
                graphIterator = new GraphIterator(g, unexplored.iterator().next());
                result++;
            }
        }

        return result;
    }

    /**
     * Find the shortest path between v and u in the graph g.
     *
     * @param g
     *     graph to search in.
     * @param v
     *     node to start from.
     * @param u
     *     node to reach.
     * @return the nodes you that form the shortest path, including v and u. An
     * empty list iff there is no path between v and u.
     */
    public static List<Vertex> shortestPath(Graph g, Vertex v, Vertex u) {
        if(!g.getAllVertices().contains(v) && !g.getAllVertices().contains(u)){
            return new LinkedList<Vertex>();
        }else if(v == u){
            LinkedList<Vertex> result = new LinkedList<Vertex>();
            result.add(v);
            return result;
        }


        Iterator<Vertex> graphIterator = new GraphIterator(g, v);
        Map<Vertex, Vertex> predecessors = new TreeMap<>();
        Vertex selectVertex;

        while (graphIterator.hasNext()){
            selectVertex = graphIterator.next();
            if(selectVertex == u){
                break;
            }
            if(!predecessors.containsKey(selectVertex)){
                Iterator<Vertex> vertexIterator = g.getNeighbours(selectVertex).iterator();
                while (vertexIterator.hasNext()){
                    Vertex tmpSelect = vertexIterator.next();
                    if (!predecessors.containsKey(tmpSelect)){
                        predecessors.put(selectVertex, tmpSelect);
                    }
                }
            }
        }

        boolean stopFlag = false;
        LinkedList<Vertex> result = new LinkedList<Vertex>();
        result.add(v);
        selectVertex = v;

        while(!stopFlag){
            if(predecessors.get(selectVertex) == u){
                break;
            }
            result.add(predecessors.get(selectVertex));
            selectVertex = predecessors.get(selectVertex);
        }
        result.add(u);

        return result;
    }




}
