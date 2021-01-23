package com.rd.graph.tree.graph;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class Node {

    private final int vertexNumber;
    private final Set<Integer> adjacencySet;

    public Node(int vertexNumber) {
        this.vertexNumber = vertexNumber;
        this.adjacencySet = new HashSet<>();
    }

    public boolean addEdge(Integer vertexNumber) {
        return adjacencySet.add(vertexNumber);
    }


    public void removeEdge(Integer vertex) {
        this.adjacencySet.remove(vertex);
    }
}
