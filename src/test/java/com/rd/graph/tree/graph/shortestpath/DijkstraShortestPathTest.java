package com.rd.graph.tree.graph.shortestpath;

import com.rd.graph.tree.graph.AdjacencyMatrixGraph;
import org.junit.Test;

import static com.rd.graph.tree.graph.GraphType.DIRECTED;
import static org.junit.Assert.assertEquals;

public class DijkstraShortestPathTest {


    DijkstraShortestPath dijkstraShortestPath = new DijkstraShortestPath();

    @Test
    public void shortestPath_unweighted() {
        AdjacencyMatrixGraph graph1 = new AdjacencyMatrixGraph(8, DIRECTED);
        graph1.addEdge(2, 7);
        graph1.addEdge(3, 0);
        graph1.addEdge(0, 4);
        graph1.addEdge(0, 1);
        graph1.addEdge(2, 1);
        graph1.addEdge(1, 3);
        graph1.addEdge(3, 5);
        graph1.addEdge(6, 3);
        graph1.addEdge(4, 7);

        double i = dijkstraShortestPath.shortestPath(graph1, 1, 7);
        assertEquals(0, Double.compare(4, i));


    }

    @Test
    public void shortestPath_weighted() {
        AdjacencyMatrixGraph graph1 = new AdjacencyMatrixGraph(8, DIRECTED);
        graph1.addEdge(2, 7, 2);
        graph1.addEdge(3, 0, 2);
        graph1.addEdge(0, 4, 2);
        graph1.addEdge(0, 1, 2);
        graph1.addEdge(2, 1, 2);
        graph1.addEdge(1, 3, 2);
        graph1.addEdge(3, 5, 2);
        graph1.addEdge(6, 3, 2);
        graph1.addEdge(4, 7, 2);

        double i = dijkstraShortestPath.shortestPath(graph1, 1, 7);
        assertEquals(0, Double.compare(8, i));


    }

    @Test
    public void shortestPath_positive_DIRECTED() {
        AdjacencyMatrixGraph graph = new AdjacencyMatrixGraph(8, DIRECTED);

        graph.addEdge(0, 1, 1);
        graph.addEdge(1, 2, 1);
        graph.addEdge(2, 3, 1);
        graph.addEdge(3, 4, 10);

        graph.addEdge(1, 5, 2);
        graph.addEdge(4, 5, 3);
        graph.addEdge(5, 6, 2);
        graph.addEdge(6, 3, 2);

        graph.addEdge(4, 0, 3);


        double i = dijkstraShortestPath.shortestPath(graph, 4, 3);
        assertEquals(0, Double.compare(6, i));
    }

    @Test
    public void shortestPath_negative_DIRECTED() {
        AdjacencyMatrixGraph graph = new AdjacencyMatrixGraph(8, DIRECTED);

        graph.addEdge(0, 1, 1);
        graph.addEdge(1, 2, 1);
        graph.addEdge(2, 3, 1);
        graph.addEdge(3, 4, 10);

        graph.addEdge(1, 5, 2);
        graph.addEdge(4, 5, 3);
        graph.addEdge(5, 6, 2);
//        graph.addEdge(6, 3,2);

//        graph.addEdge(4, 0,3);
        double i = dijkstraShortestPath.shortestPath(graph, 4, 3);
        System.out.println(i);
        assertEquals(0, Double.compare(Double.POSITIVE_INFINITY, i));

    }

    @Test
    public void shortestPath_positive_DIRECTED_negative_weights() {
        AdjacencyMatrixGraph graph = new AdjacencyMatrixGraph(8, DIRECTED);

        graph.addEdge(0, 1, 1);
        graph.addEdge(1, 2, 1);
        graph.addEdge(2, 3, 1);
        graph.addEdge(3, 4, 10);

        graph.addEdge(1, 5, 2);
        graph.addEdge(4, 5, -3000); // NOTE: having a negative weight is okay, but having a negative cycle will make the algo run in loops
        // increase the negative value such that there is a negative cycle
        /**
         *
         *
         */

        graph.addEdge(5, 6, 2);
        graph.addEdge(6, 3, 2);

        graph.addEdge(4, 0, 3);


        double i = dijkstraShortestPath.shortestPath(graph, 4, 3);
        assertEquals(0, Double.compare(1, i));
    }

    @Test
    public void shortestPath_negative_DIRECTED_fail_negative_weights() {
        AdjacencyMatrixGraph graph = new AdjacencyMatrixGraph(4, DIRECTED);

        /**
         *     a----2----b
         *     |       /
         *     4    -3
         *     |  /
         *     c
         */
        graph.addEdge(0, 1, 2);
        graph.addEdge(0, 2, 4);
        graph.addEdge(2, 1, -3);

        // add this edges for a negative cycle
//        graph.addEdge(1, 3, 5);
//        graph.addEdge(3, 2, 2);


        double i = dijkstraShortestPath.shortestPath(graph, 0, 1);
        assertEquals(0, Double.compare(2, i));
        //Shortest path should be 1 but we found 30;
        // if we remove early stopping then for negative cycle graphs we will be stuck in a loop
    }
}