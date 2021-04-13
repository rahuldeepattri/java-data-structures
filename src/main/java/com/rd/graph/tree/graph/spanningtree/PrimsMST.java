package com.rd.graph.tree.graph.spanningtree;

import com.rd.graph.tree.graph.Edge;
import com.rd.graph.tree.graph.Graph;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.stream.IntStream;

@Slf4j
public class PrimsMST {

    public Set<Edge<Integer>> generateMST(Graph graph, Integer src) {
        int V = graph.getNumOfVertices();

        //Distance map holds the distance edges reachable from our current set of
        //visited vertices. Adding a new vertex may improve some will either increase
        //the number of edges reachable or find a shortest path to already reachable edges
        HashMap<Integer, DistanceInfo> distanceMap = new HashMap<>();

        IntStream.range(0, V)
                .forEach(vertex ->
                        distanceMap.put(
                                vertex,
                                new DistanceInfo(Double.POSITIVE_INFINITY, -1))
                );
        // for each node in our visited set
        // find the shortest outgoing edge
        // add it set and repeat

        // include src node in MST
        distanceMap.get(src).setLastVertex(src);
        distanceMap.get(src).setDistance(0d);

        /*
        The PQ will tell us which vertex we can visit next.
        PQ will have vertex info ie the reachable vertex from
        set of visited vertices and how much distance it is from our set.

        If a better path is available when we add a vertex to visited then
        give it priority.
         */
        Queue<VertexInfo> queue = new PriorityQueue<>(Comparator.comparingDouble(VertexInfo::getDistance));

        //from src node try to find all the edges
        //src node is at an edge of 0 distance
        queue.offer(new VertexInfo(src, 0d));
        Set<Edge<Integer>> mst = new LinkedHashSet<>();

        /**
         * Think of visited edges as cloud which keeps on expanding till it
         * has all the nodes. Initially this is empty
         */
        Set<Integer> visitedVertices = new HashSet<>();

        while (!queue.isEmpty() && visitedVertices.size() != V) { // till we have all nodes in out mst
            log.debug("Queue: {}", queue);

            // find the closest next vertex
            VertexInfo poll = queue.poll();

            int currentVertex = poll.getVertex();

            /*
              Do not visit already visited vertex
                   A ----2---- B
                   |           |
                   |           |
                   2           1
                   |           |
                   |           |
                   C ----2---- D
             */

            if (visitedVertices.contains(currentVertex))
                continue;

            //Note that minimum distance was updated before adding to queue
            double currentVertexDist = poll.getDistance();

            if (currentVertex != src) {
                // Get the nearest edge from visited vertices to unvisited vertex
                Edge<Integer> edge = Edge.<Integer>builder()
                        .from(distanceMap.get(currentVertex).getLastVertex())
                        .to(currentVertex)
                        .weight(currentVertexDist)
                        .build();
                mst.add(edge);
            }

            visitedVertices.add(currentVertex);

            for (int neighbor : graph.getAdjacentVertices(currentVertex)) {
                if (visitedVertices.contains(neighbor)) {
                    continue;
                }
                 /*
                   A ----2---- B
                   |           |
                   |           |
                   2           1
                   |           |
                   |           |
                   C ----2---- D

                    vertex  distance    lastVertex
                    a           0       a
                    b           2       a
                    c           2       a
                    d           1       b
                 */
                // since the neighbor will not be part of queue
                double distance = graph.getWeight(currentVertex, neighbor);
                if (distance < distanceMap.get(neighbor).getDistance()) {
                    // If we know we cannot do any better then dont add to queue
                    // else we know a better path exists then add to queue so that
                    // it gets processed first.

                    // since distance map keeps track of nearest edge from
                    // our visited nodes. If we already have a better distance
                    // available which is reachable via other nodes in our mst
                    // we dont need to add to edge to queue which wont
                    // have a better result.

                    distanceMap.get(neighbor).setDistance(distance);
                    distanceMap.get(neighbor).setLastVertex(currentVertex);

                    queue.offer(new VertexInfo(neighbor, distance));
                }

            }


        }
        log.debug("distanceMap: {}", distanceMap);
        return mst;
    }
}