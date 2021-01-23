package com.rd.graph.tree.graph.shortestpath;

import com.rd.graph.tree.graph.Graph;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.stream.IntStream;

@Slf4j
public class DijkstraShortestPath {

	/*
	A----2----B---2----C
	|	    |       /
	1         3     3
	|	    |   /
	D----5----E
	
	E6

*/

    /**
     * Fail Case
     * a----2----b
     * |       /
     * 4    -3
     * |  /
     * c
     */

    public double shortestPath(Graph graph, int src, int dest) {
        HashMap<Integer, DistanceInfo> distanceMap = buildDistanceInfoMap(graph, src, dest);
        log.debug("{}", ShortestPathPrinter.shortestPath((Map) distanceMap, src, dest));
        log.debug("Shortest Distance: {}", distanceMap.get(dest).getDistance());
        return distanceMap.get(dest).getDistance();

    }

    private HashMap<Integer, DistanceInfo> buildDistanceInfoMap(Graph graph, int src, int dest) {
        HashMap<Integer, DistanceInfo> distanceMap = new HashMap<>();
        IntStream.range(0, graph.getNumOfVertices())
                .forEach(vertex ->
                        distanceMap.put(vertex, new DistanceInfo(Double.POSITIVE_INFINITY, -1))
                );

        //boolean[] visited = new boolean[graph.getNumOfVertices()];
        // we can revisit a node

        // starting from source vertex
        distanceMap.get(src).setDistance(0d);
        distanceMap.get(src).setLastVertex(src);


        Queue<Pair> queue = new PriorityQueue<>();
        queue.offer(new Pair(src, 0));

        while (!queue.isEmpty()) {
            log.debug("{}", queue.peek());

            Pair poll = queue.poll();

            int vertex = poll.getVertex();
            if (dest == vertex) break;

            double distToVertex = poll.getDistance();
            double shortestDistance = distanceMap.get(vertex).getDistance();
            if (distToVertex > shortestDistance) { // this can happen when the node was queued from two neighbors
                // we have already processed this
                // vertex and hence found a shorter
                // path earlier
                continue;
            }


            for (int neighbour : graph.getAdjacentVertices(vertex)) {
                double newDistance = distToVertex
                        + graph.getWeight(vertex, neighbour);

                double currDistance = distanceMap.get(neighbour)
                        .getDistance();
                if (newDistance < currDistance) { // relax step
                    distanceMap.get(neighbour)
                            .setDistance(newDistance);
                    distanceMap.get(neighbour)
                            .setLastVertex(vertex);
                    // To check queue has already the neighbour, if yes
                    // remove it and add new neighbour or have check at
                    // L#54
                    queue.offer(new Pair(neighbour, newDistance));
                }
            }
        }
        log.debug("Distance: {}", distanceMap);
        return distanceMap;
    }


    @Data
    @AllArgsConstructor
    static
    class Pair implements Comparable<Pair> {
        int vertex;
        double distance;

        @Override
        public int compareTo(Pair other) {
            return Double.compare(this.distance, other.distance);
            // TODO check double comparisons are correct or not
        }
    }

    @Data
    @AllArgsConstructor
    static class DistanceInfo implements PathTraceable {
        Double distance;
        Integer lastVertex;
    }

}




