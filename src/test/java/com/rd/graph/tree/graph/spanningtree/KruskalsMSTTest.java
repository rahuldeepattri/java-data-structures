package com.rd.graph.tree.graph.spanningtree;

import com.rd.graph.tree.graph.AdjacencyMatrixGraph;
import com.rd.graph.tree.graph.Edge;
import org.junit.Test;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Random;
import java.util.Set;

import static com.rd.graph.tree.graph.GraphType.UNDIRECTED;
import static org.junit.Assert.assertEquals;

public class KruskalsMSTTest {
    KruskalsMST kruskalsMST = new KruskalsMST();
    PrimsMST primsMST = new PrimsMST();


    @Test
    public void generateMST_unweighted_connected() {
        //Connected Graph
        AdjacencyMatrixGraph graph = new AdjacencyMatrixGraph(8, UNDIRECTED);
        graph.addEdge(2, 7);
        graph.addEdge(3, 0, 2); // now this path wont be taken
        graph.addEdge(0, 4);
        graph.addEdge(0, 1);
        graph.addEdge(2, 1);
        graph.addEdge(1, 3);
        graph.addEdge(3, 5);
        graph.addEdge(6, 3);
        graph.addEdge(4, 7);

        Set<Edge<Integer>> expected = primsMST.generateMST(graph, 0);
        Set<Edge<Integer>> mst = kruskalsMST.generateMST(graph);

        assertEquals(graph.getNumOfVertices() - 1, mst.size());

        for (Edge edge :
                mst) {
            System.out.println(edge);
        }

        double expectedSum = expected.stream().map(Edge::getWeight).mapToDouble(Double::doubleValue).sum();
        double actualSum = mst.stream().map(Edge::getWeight).mapToDouble(Double::doubleValue).sum();
        assertEquals(0, Double.compare(expectedSum, actualSum));
    }


    @Test
    public void generateMST_negative_DIRECTED_negative_weights() {
        AdjacencyMatrixGraph graph = new AdjacencyMatrixGraph(3, UNDIRECTED);

        /**
         *     a----2----b
         *     |       /
         *     4    -3
         *     |  /
         *     c
         */
        graph.addEdge(0, 1, 2);
        graph.addEdge(0, 2, 4);
        graph.addEdge(2, 1, -3000);

        Set<Edge<Integer>> expected = primsMST.generateMST(graph, 0);
        Set<Edge<Integer>> mst = kruskalsMST.generateMST(graph);

        assertEquals(graph.getNumOfVertices() - 1, mst.size());

        for (Edge edge :
                mst) {
            System.out.println(edge);
        }

        double expectedSum = expected.stream().map(Edge::getWeight).mapToDouble(Double::doubleValue).sum();
        double actualSum = mst.stream().map(Edge::getWeight).mapToDouble(Double::doubleValue).sum();
        assertEquals(0, Double.compare(expectedSum, actualSum));
    }


    @Test
    public void generateMST_negative_DIRECTED_fail_negative_weights() {
        AdjacencyMatrixGraph graph = new AdjacencyMatrixGraph(4, UNDIRECTED);


        /*
                   A ----2---- B
                   |           |
                   |           |
                   2           1
                   |           |
                   |           |
                   C ----2---- D
                   Since we relax edges even if we visit AB first
                   or AC first
             */
        graph.addEdge(0, 1, 2);
        graph.addEdge(2, 3, 2);
        graph.addEdge(0, 2, 2);
        graph.addEdge(3, 1, 1);

        Set<Edge<Integer>> expected = primsMST.generateMST(graph, 0);

        Set<Edge<Integer>> mst = kruskalsMST.generateMST(graph);

        assertEquals(graph.getNumOfVertices() - 1, mst.size());

        for (Edge edge :
                mst) {
            System.out.println(edge);
        }

        double expectedSum = expected.stream().map(Edge::getWeight).mapToDouble(Double::doubleValue).sum();
        double actualSum = mst.stream().map(Edge::getWeight).mapToDouble(Double::doubleValue).sum();
        assertEquals(0, Double.compare(expectedSum, actualSum));
    }

    @Test
    public void generateMST_dense() {
        AdjacencyMatrixGraph graph = new AdjacencyMatrixGraph(100, UNDIRECTED);


        Random random = new Random();
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < 10000; i++) {
            int weight = random.nextInt(50);
            map.putIfAbsent(weight, 0);
            map.compute(weight, (k, v) -> v + 1);
            graph.addEdge(random.nextInt(100), random.nextInt(100), random.nextInt(50));
        }

        Set<Edge<Integer>> expected = primsMST.generateMST(graph, 0);

        Set<Edge<Integer>> mst = kruskalsMST.generateMST(graph);

        assertEquals(graph.getNumOfVertices() - 1, mst.size());

        for (Edge edge :
                mst) {
            System.out.println(edge);
        }

        double expectedSum = expected.stream().map(Edge::getWeight).mapToDouble(Double::doubleValue).sum();
        double actualSum = mst.stream().map(Edge::getWeight).mapToDouble(Double::doubleValue).sum();
        assertEquals(0, Double.compare(expectedSum, actualSum));
        System.out.println(map);
    }

}