package com.rd.graph.tree.graph.shortestpath;

/**
 * For Shortest Path in an undirected/directed graph we can use BFS.
 * <p>
 * For Shortest Path in a +/- weighted DAG we can use topological sort.
 * <p>
 * For Shortest Path in a undirected/directed + weighted graph (no -ve cycles) we can use Dijkstra's Algo.
 * For Shortest Path in a undirected +- weighted graph we can use Bellmen Ford's Algo.
 * For Shortest Path in an undirected +- weighted graph we can use Floyd Warshall Algo.
 * <p>
 * Distance Table for a specific path stores(path may node be the shortest)
 * 1.   distance of <i>from</i> node to <i>to</i> node.
 * 2.   last vertex/ previous vertex encountered in the path
 */

//todo verify for negative wieghts , add the amount to all the edges and see
// if using dijkstras we will get a correct solution


//detect postive cycles- dfs for undirected, bfs in degree for directed
//todo detect neagtive cycles- dfs for undirected, bfs in degree for directed
//detect +cycle by assuming graph to be directed and
// check that in a cycle if the total sum is negative;
