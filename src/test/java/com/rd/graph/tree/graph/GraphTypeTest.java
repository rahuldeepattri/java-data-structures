package com.rd.graph.tree.graph;

import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

import static com.rd.graph.tree.graph.GraphType.DIRECTED;
import static com.rd.graph.tree.graph.GraphType.UNDIRECTED;
import static java.lang.System.out;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class GraphTypeTest {

    @Test
    public void detectCycle_DIRECTED_Graph() {
        AdjacencyMatrixGraph graph = new AdjacencyMatrixGraph(7, DIRECTED);

        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(3, 4);
        graph.addEdge(4, 5);

        graph.addEdge(6, 2);

        List<Integer> list = GraphUtils.topologicalSort(graph);
        out.println(list);
        assertFalse(DIRECTED.detectCycle(graph));
        assertFalse(DIRECTED.detectCycleDFS(graph));

        graph.addEdge(5, 6);


        list = GraphUtils.topologicalSort(graph);
        out.println(list);

        assertTrue(DIRECTED.detectCycle(graph));
        assertTrue(DIRECTED.detectCycleDFS(graph));
        assertFalse(list.indexOf(6) < list.indexOf(2)); // at least one entry is wrong now
    }

    @Test
    public void detectCycle_UNDIRECTED_Graph() {
        AdjacencyMatrixGraph graph = new AdjacencyMatrixGraph(7, UNDIRECTED);

        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(3, 4);
        graph.addEdge(4, 5);

        graph.addEdge(6, 2);

        assertFalse(DIRECTED.detectCycleDFS(graph));

        graph.addEdge(5, 6);


        assertTrue(DIRECTED.detectCycleDFS(graph));
    }


    @Test
    public void detectCycle_dense() {
        AdjacencyMatrixGraph graph = new AdjacencyMatrixGraph(1000, UNDIRECTED);


        Random random = new Random();
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < 100; i++) {
            int weight = random.nextInt(50);
            map.putIfAbsent(weight, 0);
            map.compute(weight, (k, v) -> v + 1);
            graph.addEdge(random.nextInt(100), random.nextInt(100), random.nextInt(50));
        }

        assertTrue(UNDIRECTED.detectCycleDFS(graph));
    }


}