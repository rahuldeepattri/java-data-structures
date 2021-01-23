package com.rd.Queue;

import java.util.ArrayDeque;
import java.util.Deque;

public class QueueBuildWith2Stacks<T> {

    private Deque<T> enqueStack = new ArrayDeque<>();
    private Deque<T> dequeStack = new ArrayDeque<>();

    public QueueBuildWith2Stacks() {
    }

    public boolean offer(T element) {
        return enqueStack.offerFirst(element);
    }

    public T poll() {

        T element = dequeStack.pollFirst();
        if (element != null)
            return element;

        while (!enqueStack.isEmpty()) {
            element = enqueStack.pollFirst();
            dequeStack.offerFirst(element);
        }
        return dequeStack.pollFirst();
    }

    @Override
    public String toString() {
        return "Enque: " + enqueStack.toString() + "\nDeque: " + dequeStack.toString();
    }


}
