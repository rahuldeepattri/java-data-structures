package com.rd.graph.tree.graph.shortestpath;

import com.rd.graph.tree.graph.Graph;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.stream.IntStream;

@Slf4j
public class DijkstraFewerEdges {

    public static final char CHARACTER_STARTING = 'a';
    private static final double SOURCE_DISTANCE = 0d;
    private static final int SOURCE_EDGE_COUNT = 0;
    private static final int UNKNOWN_LAST_VERTEX = -1;

    public double shortestPath(Graph graph, char src, char dest) {
        return this.shortestPath(graph, src - CHARACTER_STARTING, dest - CHARACTER_STARTING);
    }

    public List<Integer> getShortestPath(Graph graph, char src, char dest) {
        return this.getShortestPath(graph, src - CHARACTER_STARTING, dest - CHARACTER_STARTING);
    }

    public double shortestPath(Graph graph, int src, int dest) {
        Map<Integer, DistanceInfo> map = this.buildDistanceInfo(graph, src, dest);
        return map.get(dest).getDistance();

    }

    public List<Integer> getShortestPath(Graph graph, int src, int dest) {
        Map<Integer, DistanceInfo> map = this.buildDistanceInfo(graph, src, dest);
        return ShortestPathTracer.shortestPath((Map) map, src, dest);
    }

    public Map<Integer, DistanceInfo> buildDistanceInfo(Graph graph, int src, int dest) {

        HashMap<Integer, DistanceInfo> distanceMap = new HashMap<>();
        IntStream.range(0, graph.getNumOfVertices())
                .forEach(vertex ->
                        distanceMap.put(vertex,
                                new DistanceInfo(
                                        vertex,
                                        Double.POSITIVE_INFINITY,// Dont know the distance
                                        Integer.MAX_VALUE, // Dont know how many edges will be req
                                        UNKNOWN_LAST_VERTEX
                                ))
                );
        distanceMap.get(src).setDistance(SOURCE_DISTANCE);
        distanceMap.get(src).setEdgeCount(SOURCE_EDGE_COUNT);
        distanceMap.get(src).setLastVertex(src);

        PriorityQueue<VertexInfo> queue = new PriorityQueue<>(
                getVertexInfoComparator()
        );

        VertexInfo srcVertexInfo = new VertexInfo(
                src,
                SOURCE_DISTANCE,
                SOURCE_EDGE_COUNT
        );

        queue.offer(srcVertexInfo);

        /*
            a --- b
            |    /
            |  /
            c

        */
        while (!queue.isEmpty()) {
            VertexInfo currVertexInfo = queue.poll();

            int currVertex = currVertexInfo.getVertex();

//Early stopping is okay because we only pick best paths
//from priority queue
            if (currVertex == dest) break;

            double currDistance = currVertexInfo.getDistance();
            int currEdgeCount = currVertexInfo.getEdgeCount();

// a vertex can be revisited but if the distance is less
// than what we already found then no need to visit again

            DistanceInfo currDistanceInfo = distanceMap.get(currVertex);
            if (alreadyProcessedBetter(currVertexInfo, currDistanceInfo)) {
                // this can happen if before processing the vertex info
                // a better vertex info was inserted and via other path

                // suppose if we process current node, we will be only
                // adding distance since all distances are positive

                // while in bfs we dont have issue because we dont add visited nodes
                log.debug("alreadyProcessedBetter {}, {}", currVertexInfo, currDistanceInfo);
                continue;
            }

            for (int neighbour : graph.getAdjacentVertices(currVertex)) {

                double newDistance = currDistance +
                        graph.getWeight(currVertex, neighbour);
                int newEdgeCount = currEdgeCount + 1;

                DistanceInfo neighbourDistanceInfo =
                        distanceMap.get(neighbour);

                int compare = Double.compare(newDistance,
                        neighbourDistanceInfo.getDistance());


                if (compare == 0) {
                    compare = Integer.compare(newEdgeCount,
                            neighbourDistanceInfo.getEdgeCount());
                }

                if (compare < 0) {// we found a better path
                    neighbourDistanceInfo.setDistance(newDistance);
                    neighbourDistanceInfo.setEdgeCount(newEdgeCount);
                    neighbourDistanceInfo.setLastVertex(currVertex);

                    VertexInfo newNeighbour = new VertexInfo(
                            neighbour,
                            newDistance,
                            newEdgeCount
                    );
                    queue.offer(newNeighbour);

                }
            }

        }

        return distanceMap;


    }

    private Comparator<VertexInfo> getVertexInfoComparator() {
        return Comparator.comparingDouble(VertexInfo::getDistance)
                .thenComparingInt(VertexInfo::getEdgeCount);
    }

    public boolean alreadyProcessedBetter(
            VertexInfo vertexInfo,
            DistanceInfo distanceInfo
    ) {
        int compare = Double.compare(vertexInfo.getDistance(),
                distanceInfo.getDistance());


        if (compare == 0) {
            return vertexInfo.getEdgeCount()
                    > distanceInfo.getEdgeCount();
        } else {
            return compare > 0;
        }
    }

    @Data
    @AllArgsConstructor
    static class DistanceInfo implements PathTraceable {
        Integer vertex;
        Double distance;
        Integer edgeCount;
        Integer lastVertex;
    }

    @Data
    @AllArgsConstructor
    static class VertexInfo {
        int vertex;
        double distance;
        int edgeCount;
    }

}






