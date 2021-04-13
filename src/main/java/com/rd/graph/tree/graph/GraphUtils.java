package com.rd.graph.tree.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GraphUtils {
    ///todo level wise

    /**
     * Connected component of a Graph with a cycle will have no vertex with in degree 0
     * Connected component of a Graph without a cycle will have at least one vertex with in-degree 0
     * <p>
     * This works only for the DIRECTED GRAPH
     *
     * @return
     */
    public static List<Integer> topologicalSort(Graph graph) {
        List<Integer> topologicalSort = new ArrayList<>();
        Boolean[] visited = new Boolean[graph.getNumOfVertices()];
        for (int i = 0; i < graph.getNumOfVertices(); i++) {
            graph.postOrderTraversal(topologicalSort::add, visited, i);
        }
        Collections.reverse(topologicalSort);
        return topologicalSort;
    }


}
