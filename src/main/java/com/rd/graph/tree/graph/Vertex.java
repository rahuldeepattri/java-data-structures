package com.rd.graph.tree.graph;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString(includeFieldNames = false)
public class Vertex<T> {
    private T id;
}
