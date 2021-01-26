package com.company;

import java.util.*;
import static org.junit.Assert.*;
import org.junit.*;

class Graph {
    static class Vertex {
        private int id;
        private Set<Vertex> neighbours;

        public Vertex(int id) {
            this.id = id;
            neighbours = new HashSet<>();
        }

        public void addNeighbour(Vertex v) {
            neighbours.add(v);
        }

        private Collection<Vertex> getNeighbours() {
            return new ArrayList<>(this.neighbours);
        }

        public int getId() {
            return this.id;
        }
    }

    Map<Integer, Vertex> vertices;

    public Graph() {
        this.vertices = new HashMap<>();
    }

    public void addVertex(Vertex v) {
        this.vertices.put(v.getId(), v);
    }

    public Collection<Vertex> getAllVertices() {
        return new ArrayList<>(this.vertices.values());
    }

    public List<Vertex> getNeighbours(Vertex v) {
        List<Vertex> neigh = new ArrayList<>(((Vertex) v).getNeighbours());
        return neigh;
    }

    public void addEdge(Vertex v, Vertex w) {
        v.addNeighbour(w);
        w.addNeighbour(v);
    }
}

public class Main {

    @Test
    public void testNull() {
        Graph g = new Graph();
        Graph.Vertex v = null;
        assertEquals(0, countVertices(g, v));
        g = null;
        v = new Graph.Vertex(1);
        assertEquals(0, countVertices(g, v));
    }

    @Test
    public void testExample() {
        Graph g = new Graph();
        Graph.Vertex v = new Graph.Vertex(1);
        Graph.Vertex w = new Graph.Vertex(2);
        Graph.Vertex x = new Graph.Vertex(3);
        Graph.Vertex y = new Graph.Vertex(4);
        g.addVertex(v);
        g.addVertex(w);
        g.addVertex(x);
        g.addVertex(y);
        g.addEdge(v, w);
        g.addEdge(w, y);
        assertEquals(3, countVertices(g, v));
        assertEquals(3, countVertices(g, w));
        assertEquals(3, countVertices(g, y));
        assertEquals(1, countVertices(g, x));
    }

    /**
     * Counts the number of vertices in the same connected component as v in graph g.
     * This is done using breadth first search.
     *
     * Returns 0 if the graph or vertex is null
     *
     * @param g
     *     The graph to count vertices in.
     * @param v
     *     The vertex to start counting at.
     * @return the number of vertices in the same connected component.
     */
    public static int countVertices(Graph g, Graph.Vertex v) {
        if (g == null || v == null){
            return 0;
        }

        Queue<Graph.Vertex> queue = new LinkedList<>();
        List<Graph.Vertex> knownVertices = new LinkedList<>();
        int count = 1;
        knownVertices.add(v);
        queue.add(v);

        while (!queue.isEmpty()){
            Graph.Vertex select = queue.remove();
            Iterator<Graph.Vertex> graphIter = g.getNeighbours(select).iterator();
            while (graphIter.hasNext()){
                Graph.Vertex check = graphIter.next();
                if (!knownVertices.contains(check)){
                    count++;
                    knownVertices.add(check);
                    queue.add(check);
                }
            }
        }

        return count;

    }



}
