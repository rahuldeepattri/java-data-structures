package com.rd.graph.tree.graph;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.IntConsumer;

/**
 * This interface uses integer identifiers for a graph.
 * The integer identifier can be used to access indexes from array.
 * The actual vertex details can be looked up from this identifier
 * from a separate data structure.
 */
public interface Graph {


    void addEdge(Integer v1, Integer v2);

    void removeEdge(Integer v1, Integer v2);

    List<Integer> getAdjacentVertices(Integer vertex);

    int getNumOfVertices();

    default void breadthFirstTraversal(IntConsumer consumer, Integer start) {

        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();

        queue.offer(start);

        while (!queue.isEmpty()) {
            Integer poll = queue.poll();
            if (visited.contains(poll)) continue; // for self loops

            consumer.accept(poll);
            visited.add(poll);

            for (Integer vertex : getAdjacentVertices(poll)) {
                if (!visited.contains(vertex))
                    queue.offer(vertex);
            }
        }
    }

    default void depthFirstTraversal(IntConsumer consumer, Integer start) {

        Set<Integer> visited = new HashSet<>();
        Deque<Integer> stack = new LinkedList<>();

        stack.offerFirst(start);

        while (!stack.isEmpty()) {

            Integer top = stack.pollFirst();
            if (visited.contains(top)) continue; // for self loops
            consumer.accept(top);
            visited.add(top);

            for (Integer vertex : getAdjacentVertices(top)) {
                if (!visited.contains(vertex))
                    stack.offer(vertex);
            }
        }
    }


    default void postOrderTraversal(IntConsumer consumer, Boolean[] visited, Integer start) {

        if (Boolean.TRUE.equals(visited[start]))
            return;
        visited[start] = Boolean.TRUE;

        for (Integer vertex : getAdjacentVertices(start)) {
            if (!Boolean.TRUE.equals(visited[vertex]))
                postOrderTraversal(consumer, visited, vertex);
        }
        consumer.accept(start);
    }


    default void bfs(IntConsumer consumer, Boolean[] visited, Integer start) {
        if (Boolean.TRUE.equals(visited[start]))
            return;

        Queue<Integer> queue = new LinkedList<>();

        queue.offer(start);

        while (!queue.isEmpty()) {
            Integer poll = queue.poll();
            if (Boolean.TRUE.equals(visited[poll])) continue;

            visited[poll] = Boolean.TRUE;
            consumer.accept(poll);

            for (Integer vertex : getAdjacentVertices(start)) {
                if (!Boolean.TRUE.equals(visited[vertex]))
                    queue.offer(vertex);
            }

        }

    }


    default Map<Integer, Degree> getDegree() {
        Map<Integer, Degree> degrees = new HashMap<>();

        for (int curr = 0; curr < getNumOfVertices(); curr++) {

            List<Integer> adjacentVertices = getAdjacentVertices(curr);
            for (Integer adjacentVertex : adjacentVertices) {
                degrees.computeIfAbsent(adjacentVertex, t -> new Degree()).inDegree++;
            }
            degrees.computeIfAbsent(curr, t -> new Degree()).outDegree = adjacentVertices.size();

        }
        return degrees;
    }


    double getWeight(int vertex, int neighbour);
}

class Degree {
    int inDegree;
    int outDegree;

    @Override
    public String toString() {
        return "Degree{" +
                "inDegree=" + inDegree +
                ", outDegree=" + outDegree +
                '}';
    }
}