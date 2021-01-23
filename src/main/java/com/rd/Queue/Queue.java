package com.rd.Queue;

import java.lang.reflect.Array;

// import java.util.Array;
// import com.rd.linkedlist.Node;/

/**
 * This queue implementation uses a circular array.
 */
public class Queue<T> {
    public static final int EMPTY_VALUE = -1;

    private int start = EMPTY_VALUE;
    private int end = EMPTY_VALUE;
    private int capacity;
    private T[] elements;

    @SuppressWarnings("unchecked")
    public Queue(Class<T> clazz, int capacity) {
        this.capacity = capacity;
        this.elements = (T[]) Array.newInstance(clazz, capacity);
    }

    public void add(T element) throws QueueFullException {
        if (!offer(element))
            throw new QueueFullException();
    }

    public boolean offer(T element) {

        int idx = getInsertIdx();

        if (idx != start) {
            elements[idx] = element;
            end = idx;
            if (start == EMPTY_VALUE) {
                start = end;
            }
            return true;
        }

        return false;
    }

    public T remove() throws QueueEmptyException {
        T element = poll();
        if (poll() == null)
            throw new QueueEmptyException();
        return element;
    }

    public T poll() {
        if (isEmpty())
            return null;
        T element = elements[start];
        elements[start] = null;

        start = getRemoveIdx();
        return element;
    }


    public T peek() {
        if (isEmpty())
            return null;
        return elements[start];
    }

    //if head == tail i

    public boolean isEmpty() {
        return start == EMPTY_VALUE;
    }

    public boolean isFull() {
        // next position of end is equal to start then it means
        // queue is full
        return start == getInsertIdx();
    }

    private int getInsertIdx() {
        return (end + 1) % capacity;
    }

    private int getRemoveIdx() {
        if (start == end) return EMPTY_VALUE;
        return (start + 1) % capacity;
    }

    public String toString() {
        return java.util.Arrays.toString(elements) + ", Start:" + start + " , End:" + end;
    }

    private static class QueueFullException extends Exception {

    }

    private static class QueueEmptyException extends Exception {

    }
}
