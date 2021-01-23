package com.rd.graph.tree.graph.shortestpath;

public interface PathTraceable {
    <T> T getDistance();

    Integer getLastVertex();
}
