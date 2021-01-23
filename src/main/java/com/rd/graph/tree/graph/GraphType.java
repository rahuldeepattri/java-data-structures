package com.rd.graph.tree.graph;

import java.util.*;

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
            ArrayList<Integer> topSort = new ArrayList<>();

            while (!queue.isEmpty()) {

                Integer poll = queue.poll();
                topSort.add(poll);

                for (Integer adjacentVertex : graph.getAdjacentVertices(poll)) {
                    Degree degree = graphDegree.get(adjacentVertex);
                    degree.inDegree--;
                    if (degree.inDegree == 0) {
                        queue.add(adjacentVertex);
                    }
                }

            }

            return (topSort.size() != graph.getNumOfVertices());

        }


    },

    /**
     * Use depth first search ( similar to idea to topological sort)and detect cycle
     */
    UNDIRECTED {
        @Override
        public boolean detectCycle(Graph graph) {

            Set<Integer> visited = new HashSet<>();
            for (int i = 0; i < graph.getNumOfVertices(); i++) {
                Integer curr = i;
                if (visited.contains(curr)) continue;

//                System.out.println("Starting: " + curr);
                visited.add(curr);

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

}
