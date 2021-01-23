package com.rd.graph.tree.graph.spanningtree;

import com.rd.graph.tree.graph.AdjacencyMatrixGraph;
import com.rd.graph.tree.graph.Edge;
import com.rd.graph.tree.graph.GraphType;
import org.junit.Test;

import java.util.Set;

import static com.rd.graph.tree.graph.GraphType.DIRECTED;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class PrimsMSTTest {

    PrimsMST primsMST = new PrimsMST();


    @Test
    public void generateMST_unweighted_disconnected() {
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

        Set<Edge<Integer>> mst = primsMST.generateMST(graph1, 0);

        // Since graph is not connected mst is not correct
        assertNotEquals(graph1.getNumOfVertices() - 1, mst.size());
//        number of edges in mst is V - 1

    }

    @Test
    public void generateMST_unweighted_connected() {
        //Connected Graph
        AdjacencyMatrixGraph graph1 = new AdjacencyMatrixGraph(8, GraphType.UNDIRECTED);
        graph1.addEdge(2, 7);
        graph1.addEdge(3, 0, 2); // now this path wont be taken
        graph1.addEdge(0, 4);
        graph1.addEdge(0, 1);
        graph1.addEdge(2, 1);
        graph1.addEdge(1, 3);
        graph1.addEdge(3, 5);
        graph1.addEdge(6, 3);
        graph1.addEdge(4, 7);

        Set<Edge<Integer>> mst = primsMST.generateMST(graph1, 0);

        for (Edge e :
                mst) {
            System.out.println(e);
        }
        // Since graph is connected mst is correct now
        assertEquals(graph1.getNumOfVertices() - 1, mst.size());
//        number of edges in mst is V - 1


    }


    @Test
    public void generateMST_negative_DIRECTED_negative_weights() {
        AdjacencyMatrixGraph graph = new AdjacencyMatrixGraph(3, GraphType.UNDIRECTED);

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

        Set<Edge<Integer>> mst = primsMST.generateMST(graph, 0);

        for (Edge e :
                mst) {
            System.out.println(e);
        }
        // Since graph is connected mst is correct now
        assertEquals(graph.getNumOfVertices() - 1, mst.size());
        // number of edges in mst is V - 1


    }


    @Test
    public void generateMST_negative_DIRECTED_fail_negative_weights() {
        AdjacencyMatrixGraph graph = new AdjacencyMatrixGraph(4, GraphType.UNDIRECTED);


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

        Set<Edge<Integer>> mst = primsMST.generateMST(graph, 0);

        for (Edge e :
                mst) {
            System.out.println(e);
        }
        // Since graph is connected mst is correct now
        assertEquals(graph.getNumOfVertices() - 1, mst.size());
        // number of edges in mst is V - 1

        double sum = mst.stream().map(Edge::getWeight).mapToDouble(Double::doubleValue).sum();
        System.out.println(sum);
        assertEquals(0, Double.compare(5, sum));


    }
}