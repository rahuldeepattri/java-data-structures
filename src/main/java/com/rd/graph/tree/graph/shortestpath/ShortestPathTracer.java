package com.rd.graph.tree.graph.shortestpath;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class ShortestPathTracer {

    public static List<Integer> shortestPath(Map<Integer, PathTraceable> distanceInfoMap, Integer src, Integer dest) {
        if (
                distanceInfoMap.get(dest).getDistance().equals(Double.POSITIVE_INFINITY)
                        || distanceInfoMap.get(dest).getDistance().equals(Double.NEGATIVE_INFINITY)
                        || distanceInfoMap.get(dest).getDistance().equals(-1)) {
            return Collections.emptyList();
        }
        ArrayList<Integer> result = new ArrayList<>();

        for (Integer vertex = dest;
             !vertex.equals(-1) && !vertex.equals(src);
             vertex = distanceInfoMap.get(vertex).getLastVertex()) {
            result.add(vertex);
        }

        result.add(src);
        Collections.reverse(result);
        return result;
    }
}
