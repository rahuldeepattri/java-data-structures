package com.rd.graph.tree.graph.shortestpath;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Used for Distance Table for a specific path stores(path may not be the shortest)
 * Each entry is DistanceInfo
 * <br>
 * 1.   distance of <i>from</i> node to <i>to</i> node.
 * <br>
 * 2.   last vertex/ previous vertex encountered in the path.
 */
@Data
@AllArgsConstructor
public class DistanceInfo {
    private int distance;
    private int lastVertex;


}
