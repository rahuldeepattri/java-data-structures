package com.rd.graph.tree.graph;

import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.stream.IntStream;

@Slf4j
public enum GraphType {
    /**
     * Use breadth first search, for topological sort by using in degree and detect cycle.
     */
    DIRECTED {
        /**
         * If a DAG has no vertex with in degree as 0, then topological sort is not possible,
         * then it has a cycles for sure. Reverse cannot say.
         * <p>
         * We can use the above fact, keep on removing nodes and check if the sub graph has a vertex
         * with in-degree zero. If at any step we can find such a vertex then graph has cycles.
         * <p>
         * If topological sort is not possible for a directed graph, then it has cycles thus its not a DAG!
         *
         * @param graph
         * @return
         */
        public boolean detectCycle(Graph graph) {

            Map<Integer, Degree> graphDegree = graph.getDegree();
            Queue<Integer> queue = new LinkedList<>();
            graphDegree.forEach((node, degree) -> {
                if (degree.inDegree == 0) {
                    queue.add(node);
                }
            });
            if (queue.isEmpty()) return true;  // definitely graph has cycles
            ArrayList<Integer> topSortList = new ArrayList<>();

            while (!queue.isEmpty()) {

                Integer poll = queue.poll();
                topSortList.add(poll);

                for (Integer adjacentVertex : graph.getAdjacentVertices(poll)) {
                    Degree degree = graphDegree.get(adjacentVertex);
                    degree.inDegree--;
                    if (degree.inDegree == 0) {
                        queue.add(adjacentVertex);
                    }
                }

            }

            return (topSortList.size() != graph.getNumOfVertices());

        }
        /*

         */
/**
 Use depth first search, for topological sort,     * Use depth first search, for topological sort,
 *//*

        public boolean detectCycleDFS(Graph graph) {
            HashMap<Integer, String> visited = new HashMap<>();
            IntStream.range(0, graph.getNumOfVertices())
                    .forEach(vertex -> visited.put(vertex, "NOT VISITED"));

            for (int vertex = 0; vertex < graph.getNumOfVertices(); vertex++) {

                if (visited.get(vertex).equals("NOT VISITED")) {
                    boolean hasCycle = hasCycleDFS(vertex, graph, visited);
                    if (hasCycle) return true;
                }
            }

            return false;
        }

        */
/**
 * Uses Post order technique similar to topological sort
 *//*

        private boolean hasCycleDFS(Integer from, Graph graph, HashMap<Integer, String> visited) {

            if (visited.get(from).equals("VISITED")) {
                return false; // we were able to visit this previously successfully without cycles.
            }
            //while going down a path we found a node which we are already visiting then a cycle
            //is present
            else if (visited.get(from).equals("VISITING")) {
                return true;
            } else {
                // start visiting current node
                visited.put(from, "VISITING");
            }

            // explore
            for (Integer to :
                    graph.getAdjacentVertices(from)) {
                boolean hasCycleDFS = hasCycleDFS(to, graph, visited);
                if (hasCycleDFS) return true;
            }

            // mark current as visited
            visited.put(from, "VISITED");
            return false;


        }
*/

    },

    /**
     * Use depth first search ( similar to idea to topological sort)and detect cycle
     * <p>
     * similar code for DIRECTED Graphs -> Backtracking
     * <code>
     * for (int i = 0; i < numCourses; i++) {
     * // while going from any starting node do we reach an already visited node
     * if(dfsCycle(i, graph, visited))
     * return false;
     * }
     * visited.add(src);
     * <p>
     * for (int neighbour: graph.get(src)) {
     * boolean b = dfsCycle(neighbour, graph, visited);
     * if(b) return true;
     * }
     * <p>
     * visited.remove(src);
     * </code>
     */

    UNDIRECTED {
        @Override
        public boolean detectCycle(Graph graph) {
            // set of visiting + visited node
            Set<Integer> visited = new HashSet<>();
            for (int i = 0; i < graph.getNumOfVertices(); i++) {
                Integer curr = i;
                if (visited.contains(curr)) continue;

//                System.out.println("Starting: " + curr);
                visited.add(curr); // visitnig current node

                for (Integer adjacentVertex : graph.getAdjacentVertices(curr)) {
                    if (hasCycle(graph, curr, adjacentVertex, visited)) return true;
                }
            }
            return false;
        }


        private boolean hasCycle(Graph graph, Integer from, Integer to, Set<Integer> visited) {
//            System.out.println("From:" + from + " To:" + to);

            if (visited.contains(to)) return true;

            visited.add(to);

            for (Integer adjacentVertex : graph.getAdjacentVertices(to)) {
                if (adjacentVertex.equals(from)) continue;// do not visit the from vertex
                if (hasCycle(graph, to, adjacentVertex, visited)) return true;
            }
            return false;
        }


    };

    public abstract boolean detectCycle(Graph graph);

//    public abstract boolean detectCycleDFS(Graph graph);

    /**
     * Simple method for both directed and undirected graphs
     */
    public boolean detectCycleDFS(Graph graph) {
        HashMap<Integer, String> visited = new HashMap<>();
        IntStream.range(0, graph.getNumOfVertices())
                .forEach(vertex -> visited.put(vertex, "NOT VISITED"));

        for (int vertex = 0; vertex < graph.getNumOfVertices(); vertex++) {

            if (visited.get(vertex).equals("NOT VISITED")) {
                boolean hasCycle = hasCycleDFS(-1, vertex, graph, visited);
                if (hasCycle) return true;
            }
        }

        return false;
    }

    /**
     * Uses Post order technique similar to topological sort
     */
    private boolean hasCycleDFS(Integer from, Integer to, Graph graph, HashMap<Integer, String> visited) {
        log.debug("from:{} to:{} Status of {} :{}", from, to, to, visited.get(to));
        if (visited.get(to).equals("VISITED")) {
            log.debug("Already VISITED {}", to);
            return false; // we were able to visit this previously successfully without cycles.
            // so no need to check down further this path
        }
        //while going down a path we found a node which we are already visiting then a cycle
        //is present
        else if (visited.get(to).equals("VISITING")) {
            log.debug("Already VISITING {}", to);
            return true;
        } else {
            log.debug("Start VISITING {}", to);

            // start visiting current node
            visited.put(to, "VISITING");
        }

        // explore
        for (Integer neighbour :
                graph.getAdjacentVertices(to)) {
            if (neighbour.equals(from)) continue; // while visiting current node we dont want trivial cycles
            boolean hasCycleDFS = hasCycleDFS(to, neighbour, graph, visited);
            if (hasCycleDFS) return true;
        }
        log.debug("End VISITING {}", to);
        log.debug("Mark VISITED {}", to);

        // mark current as visited
        visited.put(to, "VISITED");
        return false;


    }

}
