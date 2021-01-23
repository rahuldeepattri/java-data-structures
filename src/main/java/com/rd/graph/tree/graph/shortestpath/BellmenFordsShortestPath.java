package com.rd.graph.tree.graph.shortestpath;

import com.rd.graph.tree.graph.Graph;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

/**
 * If Graph is in matrix form then O(V ^ 3), as E = V^2 in matrix
 * If Graph is in adjacency list form then O(E * V)
 */
@Slf4j
public class BellmenFordsShortestPath {
    public double shortestPath(Graph graph, int src, int dest) {
        HashMap<Integer, DistanceInfo> distanceMap = buildDistanceInfoMap(graph, src);
        log.debug("{}", ShortestPathPrinter.shortestPath((Map) distanceMap, src, dest));
        log.debug("Shortest Distance: {}", distanceMap.get(dest).getDistance());
        return distanceMap.get(dest).getDistance();

    }

    /**
     * for v - 1 times, for each edge do relaxation
     */
    private HashMap<Integer, DistanceInfo> buildDistanceInfoMap(Graph graph, int src) {

        int v = graph.getNumOfVertices();
        HashMap<Integer, DistanceInfo> distanceMap = new HashMap<>();
        IntStream.range(0, v)
                .forEach(vertex ->
                        distanceMap.put(vertex,
                                new DistanceInfo(Double.POSITIVE_INFINITY, -1))
                );
        distanceMap.get(src).setDistance(0d);
        distanceMap.get(src).setLastVertex(0);

        for (int i = 1; i < v; i++) { // for V - 1 times
//            HashSet<String> set = new HashSet<>();

            for (int vertex = 0; vertex < v; vertex++) { // for each edge
                double vertexDist = distanceMap.get(vertex).getDistance();

                for (int neighbor : graph.getAdjacentVertices(vertex)) {

                    /*String edge = vertex + ":" + neighbor;
                    if(set.contains(edge))
                    log.debug("{}", edge);

                    set.add(edge);*/


                    double neighborDist = distanceMap.get(neighbor)
                            .getDistance();
                    double newDist = vertexDist +
                            graph.getWeight(vertex, neighbor);
                    if (newDist < neighborDist) {
                        distanceMap.get(neighbor).setDistance(newDist);
                        distanceMap.get(neighbor)
                                .setLastVertex(vertex);
                    }

                }
            }
        }

        for (int i = 1; i < v; i++) { // for v - 1 times

            for (int vertex = 0; vertex < v; vertex++) { // for each edge
                double vertexDist = distanceMap.get(vertex).getDistance();

                for (int neighbor : graph.getAdjacentVertices(vertex)) {
                    double neighborDist = distanceMap.get(neighbor)
                            .getDistance();
                    double newDist = vertexDist +
                            graph.getWeight(vertex, neighbor);
                    if (newDist < neighborDist) {
                        distanceMap.get(neighbor)
                                .setDistance(Double.NEGATIVE_INFINITY);
                        distanceMap.get(neighbor)
                                .setLastVertex(vertex);
                    }

                }
            }
        }

        log.debug("Distance: {}", distanceMap);
        return distanceMap;

    }

    @Data
    @AllArgsConstructor
    static class DistanceInfo implements PathTraceable {
        Double distance;
        // we can use int distance with default value of 100000
        // adding any value to Integer.MAX_VALUE will overflow
        Integer lastVertex;
    }
}






