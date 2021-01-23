package com.rd.graph.tree.graph.spanningtree;

import com.rd.graph.tree.graph.AdjacencySetGraph;
import com.rd.graph.tree.graph.Edge;
import com.rd.graph.tree.graph.Graph;
import com.rd.graph.tree.graph.GraphType;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

@Slf4j

public class KruskalsMST {


    public Set<Edge<Integer>> generateMST(Graph graph) {

        int numOfVertices = graph.getNumOfVertices();

        PriorityQueue<Edge<Integer>> edgeQueue = new PriorityQueue<>(Comparator.comparing(Edge::getWeight));
      /*  IntStream.range(0, numOfVertices)
                .mapToObj(vertex ->
                        graph.getAdjacentVertices(vertex)
                                .stream()
                                .map(to -> Edge.<Integer>builder()
                                        .from(vertex)
                                        .to(to)
                                        .weight(graph.getWeight(vertex, to))
                                        .build())
                )
                .flatMap(Function.identity())
//                .flatMap(Stream::distinct) // TODO remove 1 to 2 and 2 to 1 edges
                .forEach(edgeQueue::offer);
*/
        for (int vertex = 0; vertex < numOfVertices; vertex++) {
            for (int neighbour : graph.getAdjacentVertices(vertex)) {
                if (neighbour >= vertex) { // do not add duplicate edges
                    Edge<Integer> build = Edge.<Integer>builder()
                            .from(vertex)
                            .to(neighbour)
                            .weight(graph.getWeight(vertex, neighbour))
                            .build();
                    edgeQueue.add(build);
                }
            }
        }


        log.debug("Edges : {}", edgeQueue);
        Set<Edge<Integer>> mst = new LinkedHashSet<>();
        Set<Integer> visitedVertices = new HashSet<>();

        // As we keep on adding edges to mst we check that if adding an edge to mst
        // doesnt leads to cycle. For that we maintain a copy of mst as sub graph
        // and check for cycle in that graph
        Graph subGraph = new AdjacencySetGraph(graph.getNumOfVertices(), GraphType.UNDIRECTED);

        while (mst.size() + 1 != graph.getNumOfVertices() && !edgeQueue.isEmpty()) {
            Edge<Integer> poll = edgeQueue.poll();

            subGraph.addEdge(poll.getFrom().getId(), poll.getTo().getId());
            if (GraphType.UNDIRECTED.detectCycle(subGraph)) {
                log.debug("Cycle Detected");
                // if a cycle is detected do not add this edge
                subGraph.removeEdge(poll.getFrom().getId(), poll.getTo().getId());
            } else {
                mst.add(poll);
            }
        }

        log.debug("MST: {}", mst);
        return mst;
    }

}
