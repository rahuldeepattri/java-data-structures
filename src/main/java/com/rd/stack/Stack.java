package com.rd.stack;

import com.rd.linkedlist.Node;

public class Stack<T extends Comparable<T>> {
    private Node<T> top = null;
    private int size = 0;

    private Node<T> minTop = null;

    public void push(T data) {
        top = new Node<>(data, top);
        size++;
        pushMin(data);
    }

    private void pushMin(T data) {
        if (minTop == null)
            minTop = new Node<>(data, minTop);
        else {
            T peek = minTop.getData();
            if (peek.compareTo(data) >= 0) {
                minTop = new Node<>(data, minTop);

            } else {
                minTop = new Node<>(peek, minTop);
            }
        }
    }

    public T pop() {
        if (isEmpty())
            return null;
        T data = top.getData();
        top = top.getNext();
        size--;
        minTop = minTop.getNext();
        return data;
    }

    public boolean isEmpty() {
        return top == null;
    }

    public T peek() {
        if (isEmpty())
            return null;
        return top.getData();
    }

    public T getMinimum() {
        if (minTop == null)
            return null;
        return minTop.getData();
    }

    public void print() {

        if (top != null) {
            System.out.println(top.toString());
        } else System.out.println();
    }

    @Override
    public String toString() {
        return top + "";
    }
}
