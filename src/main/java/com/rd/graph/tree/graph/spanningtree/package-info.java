package com.rd.graph.tree.graph.spanningtree;


/**
 * UNDIRECTED Shortest Path from A to B is same shortest path from B to A
 * CONNECTED is each vertex is connected(reachable) to every other vertex
 * <p>
 * UNDIRECTED connected graphs ->  Use Prim's Algo (Greedy) O(E logV), similar to
 * Dijkstra, https://stackoverflow.com/questions/22649416/why-cant-prims-or-kruskals-algorithms-be-used-on-a-directed-graph
 * https://www.geeksforgeeks.org/why-prims-and-kruskals-mst-algorithm-fails-for-directed-graph/
 * <p>
 * <p>
 * <p>
 * UNDIRECTED disconnected graphs ->  Use Kruskal's Algo (Greedy) O(E logV),
 * https://stackoverflow.com/questions/22649416/why-cant-prims-or-kruskals-algorithms-be-used-on-a-directed-graph
 * https://www.geeksforgeeks.org/why-prims-and-kruskals-mst-algorithm-fails-for-directed-graph/
 */