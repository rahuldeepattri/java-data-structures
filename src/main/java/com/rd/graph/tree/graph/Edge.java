package com.rd.graph.tree.graph;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Edge<T> {

    private Vertex<T> from;
    private Vertex<T> to;

    private double weight;

    public static <T> EdgeBuilder<T> builder() {
        return new EdgeBuilder<T>();
    }

    public static class EdgeBuilder<T> {
        private Vertex<T> from;
        private Vertex<T> to;
        private double weight;

        EdgeBuilder() {
            weight = 1.0; //default weight is 1
        }

        public EdgeBuilder<T> from(T from) {
            this.from = new Vertex<>(from);
            return this;
        }

        public EdgeBuilder<T> to(T to) {
            this.to = new Vertex<>(to);
            return this;
        }

        public EdgeBuilder<T> weight(double weight) {
            this.weight = weight;
            return this;
        }

        public Edge<T> build() {
            return new Edge<T>(from, to, weight);
        }

        public String toString() {
            return "Edge.EdgeBuilder(from=" + this.from + ", to=" + this.to + ", weight=" + this.weight + ")";
        }
    }
}

/*

 */

