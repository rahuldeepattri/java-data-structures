package com.rd.graph.tree.graph;

import lombok.ToString;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.rd.graph.tree.graph.GraphType.DIRECTED;
import static com.rd.graph.tree.graph.GraphType.UNDIRECTED;

@ToString
public class AdjacencyMatrixGraph implements Graph {

    private final double[][] adjacencyMatrix;
    private final Integer numOfVertices;
    private GraphType graphType = DIRECTED;

    public AdjacencyMatrixGraph(int numOfVertices, GraphType graphType) {
        this.numOfVertices = numOfVertices;
        this.graphType = graphType;
        this.adjacencyMatrix = new double[numOfVertices][numOfVertices];

        initializeMatrix();
    }

    public AdjacencyMatrixGraph(int numOfVertices) {
        this.numOfVertices = numOfVertices;
        this.adjacencyMatrix = new double[numOfVertices][numOfVertices];

        initializeMatrix();
    }

    public static void main(String[] args) {
        AdjacencyMatrixGraph graph = new AdjacencyMatrixGraph(5, UNDIRECTED);

        System.out.println(Arrays.deepToString(graph.adjacencyMatrix));

        graph.addEdge(0, 1);
        graph.addEdge(2, 1);
        graph.addEdge(3, 1);
        graph.addEdge(6, 2);
        graph.addEdge(2, 2);
        graph.addEdge(3, 2);
        System.out.println(Arrays.deepToString(graph.adjacencyMatrix));
        System.out.println(graph.getAdjacentVertices(2));
        ArrayList<Integer> list = new ArrayList<>();

        graph.breadthFirstTraversal(list::add, 0);

        System.out.println(list);

        list = new ArrayList<>();


        graph.depthFirstTraversal(list::add, 0);

        System.out.println(list);

    }

    private void initializeMatrix() {
        for (double[] row : adjacencyMatrix) {
            Arrays.fill(row, Double.POSITIVE_INFINITY);
        }
    }

    @Override
    public void addEdge(Integer vertexA, Integer vertexB) {
        if (isInvalidIdx(vertexA) || isInvalidIdx(vertexB)) {
            return;
        }
        int v1 = vertexA;
        int v2 = vertexB;
        adjacencyMatrix[v1][v2] = 1;
        if (graphType == UNDIRECTED) {
            adjacencyMatrix[v2][v1] = 1;
        }
    }

    @Override
    public void removeEdge(Integer vertexA, Integer vertexB) {
        if (isInvalidIdx(vertexA) || isInvalidIdx(vertexB)) {
            return;
        }
        int v1 = vertexA;
        int v2 = vertexB;
        adjacencyMatrix[v1][v2] = Double.POSITIVE_INFINITY;
        if (graphType == UNDIRECTED) {
            adjacencyMatrix[v2][v1] = Double.POSITIVE_INFINITY;
        }
    }

    public void addEdge(Integer vertexA, Integer vertexB, double weight) {
        if (isInvalidIdx(vertexA) || isInvalidIdx(vertexB)) {
            return;
        }
        int v1 = vertexA;
        int v2 = vertexB;
        adjacencyMatrix[v1][v2] = weight;
        if (graphType == UNDIRECTED) {
            adjacencyMatrix[v2][v1] = weight;
        }
    }

    public void addEdge(Character vertexA, Character vertexB, double weight) {
        this.addEdge(getAsInt(vertexA), getAsInt(vertexB), weight);
    }

    private int getAsInt(char ch) {
        return ch - 'a';
    }

    @Override
    public List<Integer> getAdjacentVertices(Integer vertex) {
        if (isInvalidIdx(vertex)) return Collections.emptyList();


        // No need to sort the result
        return IntStream.range(0, numOfVertices)
                .filter(col -> adjacencyMatrix[vertex][col] != Double.POSITIVE_INFINITY)
//                .filter(col -> !adjacencyMatrix[vertex][col].equals(0))
                .boxed()
//                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());

    }

    @Override
    public int getNumOfVertices() {
        return numOfVertices;
    }

    public int getNumOfEdges() {
        int count = 0;

        for (double[] row :
                adjacencyMatrix) {
            for (double edge :
                    row) {
                if (edge != Double.POSITIVE_INFINITY) count++;
            }
        }
        return count;
    }

    @Override
    public double getWeight(int vertex, int neighbour) {
        return adjacencyMatrix[vertex][neighbour];
    }

    private boolean isInvalidIdx(Integer idx) {
        return idx == null || idx < 0 || idx >= this.numOfVertices;
    }


}





