package com.rd.graph.tree.graph.shortestpath;

import com.rd.graph.tree.graph.AdjacencyMatrixGraph;
import com.rd.graph.tree.graph.Graph;
import org.junit.Test;

import static com.rd.graph.tree.graph.GraphType.DIRECTED;
import static org.junit.Assert.assertEquals;

public class BFSTest {

    BFS bfs = new BFS();

    @Test
    public void shortestPath() {
        Graph graph1 = new AdjacencyMatrixGraph(8, DIRECTED);
        graph1.addEdge(2, 7);
        graph1.addEdge(3, 0);
        graph1.addEdge(0, 4);
        graph1.addEdge(0, 1);
        graph1.addEdge(2, 1);
        graph1.addEdge(1, 3);
        graph1.addEdge(3, 5);
        graph1.addEdge(6, 3);
        graph1.addEdge(4, 7);

        int i = bfs.shortestPath(graph1, 1, 7);
        assertEquals(4, i);

    }

    @Test
    public void shortestPath_postive() {
        Graph graph = new AdjacencyMatrixGraph(8, DIRECTED);

        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(3, 4);

        graph.addEdge(1, 5);
        graph.addEdge(4, 5);
        graph.addEdge(5, 6);

        graph.addEdge(4, 0);


        int i = bfs.shortestPath(graph, 4, 3);
        assertEquals(4, i);
    }

    @Test
    public void shortestPath_negative() {
        Graph graph = new AdjacencyMatrixGraph(8, DIRECTED);

        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(3, 4);

        graph.addEdge(1, 5);
        graph.addEdge(4, 5);
        graph.addEdge(5, 6);
        graph.addEdge(4, 6);
//        graph.addEdge(4, 7);

        int i = bfs.shortestPath(graph, 4, 1);
        assertEquals(-1, i);

    }
}