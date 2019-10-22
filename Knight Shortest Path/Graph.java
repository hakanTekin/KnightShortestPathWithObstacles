
import java.util.LinkedList;
import java.util.NoSuchElementException;

import static sun.java2d.cmm.ColorTransform.In;

//-------------------------------------------------
// Title: pathfinder class
// Author: Hakan Ahmet Tekin 
// ID: 30212357722 
// Section: 1 
// Assignment: 1 
// Description: Graph class with bag objects changed to LinkedLists
//              (because bag was unavailable for some reason that i couldnt solve)
//------------------------------------------------- 

public class Graph {
    private final int V;
    private int E;
    private LinkedList<Integer>[] adj;

    public Graph(int V) {
        if (V < 0)
            throw new IllegalArgumentException("Number of vertices must be nonnegative");
        this.V = V;
        this.E = 0;
        adj = new LinkedList[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new LinkedList<Integer>();
        }
    }

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    // throw an IllegalArgumentException unless {@code 0 <= v < V}
    private void validateVertex(int v) {
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V - 1));
    }

    public void addEdge(int v, int w) {
        E++;
        adj[v].add(w);
        adj[w].add(v);
    }

    public Iterable<Integer> adj(int v) {
        return adj[v];
    }

    public int degree(int v) {
        return adj[v].size();
    }

}