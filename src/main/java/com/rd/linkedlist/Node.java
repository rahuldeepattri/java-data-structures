package com.rd.linkedlist;

/**
 * This class is a data structure or unit to represent the data and next node in a linked list.
 * We use generic class to store data of any type.
 *
 * @param <T> The data should be comparable. Its required for equality checks so that we can do things like finding a node or sort.
 */
public class Node<T extends Comparable<T>> {
    private T data;
    private Node<T> next;

    public Node(T data) {
        this.data = data;
        //next is by default null
    }

    public Node(T data, Node<T> next) {
        this.data = data;
        this.next = next;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Node<T> getNext() {
        return next;
    }

    public void setNext(Node<T> next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "[Data: " + data + ", Next: " + (next == null ? null : next.getData()) + "]";
    }

    //We will not be comparing the nodes but the data inside nodes.
}
