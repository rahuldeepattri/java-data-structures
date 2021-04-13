package com.rd.graph.tree.graph.shortestpath;

import com.rd.graph.tree.graph.Graph;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.stream.IntStream;

/**
 * For Shortest Path in an undirected/directed graph we can use BFS.
 * <p>
 * For Shortest Path in a +/- weighted DAG we can use topological sort.
 * For Shortest Path in a undirected + weighted graph we can use Dijkstra's Algo.
 * For Shortest Path in a undirected +- weighted graph we can use Bellmen Ford's Algo.
 * For Shortest Path in an undirected +- weighted graph we can use Floyd Warshall Algo.
 * <p>
 * Distance Table for a specific path stores(path may node be the shortest)
 * 1.   distance of <i>from</i> node to <i>to</i> node.
 * 2.   last vertex/ previous vertex encountered in the path
 */
@Slf4j
public class BFS {


    public int shortestPath(Graph graph, Integer src, Integer dest) {
        Map<Integer, DistanceInfo> distanceTable = buildDistanceTable(graph, src);
        log.debug("{}", ShortestPathTracer.shortestPath((Map) distanceTable, src, dest));
        return distanceTable.get(dest).getDistance();

    }

    private Map<Integer, DistanceInfo> buildDistanceTable(Graph graph, Integer src) {
        Map<Integer, DistanceInfo> distanceTable = new HashMap<>();

        IntStream.rangeClosed(0, graph.getNumOfVertices())
                .forEach(i -> distanceTable.put(i, new DistanceInfo(-1, -1)));

        distanceTable.get(src).setDistance(0);
        distanceTable.get(src).setLastVertex(src);

        log.debug(graph.toString());
        log.debug(distanceTable.toString());

        Queue<Integer> queue = new LinkedList<>();
        queue.offer(src);
        // each node will be visited once
        // since we will not add already visited node in the queue
        while (!queue.isEmpty()) {
            /*
             0 ------ 1 -------2
             |        |    /
             3--------4
             */
            log.debug(queue.toString());
            log.debug(distanceTable.toString());

            Integer from = queue.poll();
            // we are sure that already visited nodes are not added to queue

            for (Integer to : graph.getAdjacentVertices(from)) {
                if (distanceTable.get(to).getDistance() != -1) {
                    // If distance is not -1 then we know we have visited this node
                    continue;
                    // e.g going from 0 to 1 and then trying to go 1 to 0
                }

                int minDist = distanceTable.get(from).getDistance() + 1;
                distanceTable.get(to).setDistance(minDist);
                distanceTable.get(to).setLastVertex(from);

                queue.offer(to);
            }

            log.debug(distanceTable.toString());

        }
        return distanceTable;
    }


    @Data
    @AllArgsConstructor
    private static class DistanceInfo implements PathTraceable {
        private Integer distance;
        private Integer lastVertex;

    }

}