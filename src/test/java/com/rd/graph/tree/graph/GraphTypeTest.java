package com.rd.graph.tree.graph;

import org.junit.Test;

import java.util.List;

import static com.rd.graph.tree.graph.GraphType.DIRECTED;
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

        graph.addEdge(5, 6);


        list = GraphUtils.topologicalSort(graph);
        out.println(list);

        assertTrue(DIRECTED.detectCycle(graph));
        assertFalse(list.indexOf(6) < list.indexOf(2)); // at least one entry is wrong now
    }
}