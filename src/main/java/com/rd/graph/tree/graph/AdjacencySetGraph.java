package com.rd.graph.tree.graph;

import lombok.ToString;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

import static com.rd.graph.tree.graph.GraphType.DIRECTED;
import static com.rd.graph.tree.graph.GraphType.UNDIRECTED;
import static java.lang.System.out;

@ToString
public class AdjacencySetGraph implements Graph {

    //    private Map<Vertex<Integer>, List<Edge<Integer>>> adjacencyList;
//    private Map<Vertex<Integer>, Set<Edge<Integer>>> adjacencyList;
//    private Set<Node> adjacencySet;
    private final int numOfNodes;
    private final List<Node> nodeList;

    private GraphType graphType = DIRECTED;

    public AdjacencySetGraph(int numOfNodes, GraphType graphType) {
        this.numOfNodes = numOfNodes;
        this.graphType = graphType;
        this.nodeList = new ArrayList<>(numOfNodes);
        initializeSet();
    }

    public AdjacencySetGraph(int numOfNodes) {
        this.numOfNodes = numOfNodes;
        this.nodeList = new ArrayList<>(numOfNodes);
        initializeSet();
    }

    public static void main(String[] args) {
        AdjacencySetGraph graph = new AdjacencySetGraph(5, UNDIRECTED);

        out.println(graph.nodeList);

        graph.addEdge(0, 1);
        graph.addEdge(2, 1);
        graph.addEdge(1, 2);
//        graph.addEdge(2, 0);
        graph.addEdge(6, 2);
//        graph.addEdge(2, 2);
        graph.addEdge(3, 2);
        out.println(graph.nodeList);
        out.println(graph.getAdjacentVertices(2));

        ArrayList<Integer> list = new ArrayList<>();

        graph.breadthFirstTraversal(list::add, 0);

        out.println(list);

        list = new ArrayList<>();

        graph.depthFirstTraversal(list::add, 0);

        out.println(list);

        list = new ArrayList<>();
        Boolean[] booleans = new Boolean[graph.numOfNodes];
        for (int i = 0; i < graph.numOfNodes; i++) {
            graph.postOrderTraversal(list::add, booleans, i);
        }

        out.println(list);

        list = new ArrayList<>();
        booleans = new Boolean[graph.numOfNodes];
        for (int i = 0; i < graph.numOfNodes; i++) {
            graph.bfs(list::add, booleans, i);
        }
        out.println(list);

        out.println(graph.getDegree());
        out.println("Checking for cycles");
        out.println(UNDIRECTED.detectCycle(graph));
        out.println(UNDIRECTED.detectCycleDFS(graph));

        graph.addEdge(3, 1);

        out.println(UNDIRECTED.detectCycle(graph));
        out.println(UNDIRECTED.detectCycleDFS(graph));


    }

    private void initializeSet() {
        IntStream.range(0, numOfNodes)
                .mapToObj(Node::new)
                .forEach(nodeList::add);
    }

    @Override
    public void addEdge(Integer v1, Integer v2) {
        if (isInvalidIdx(v1) || isInvalidIdx(v2)) return;

        this.nodeList.get(v1).addEdge(v2);
        if (graphType == UNDIRECTED) {
            this.nodeList.get(v2).addEdge(v1);
        }
    }

    @Override
    public void removeEdge(Integer v1, Integer v2) {
        if (isInvalidIdx(v1) || isInvalidIdx(v2)) return;

        this.nodeList.get(v1).removeEdge(v2);
        if (graphType == UNDIRECTED) {
            this.nodeList.get(v2).removeEdge(v1);
        }
    }

    @Override
    public List<Integer> getAdjacentVertices(Integer vertex) {
        if (isInvalidIdx(vertex)) return Collections.emptyList();
        Set<Integer> set = this.nodeList.get(vertex).getAdjacencySet();
        ArrayList<Integer> integers = new ArrayList<>(set);
        Collections.sort(integers);
        return integers;
    }

    private boolean isInvalidIdx(Integer idx) {
        return idx == null || idx < 0 || idx >= this.numOfNodes;
    }

    @Override
    public int getNumOfVertices() {
        return numOfNodes;
    }

    @Override
    public double getWeight(int vertex, int neighbour) {
        throw new UnsupportedOperationException();
    }
}
