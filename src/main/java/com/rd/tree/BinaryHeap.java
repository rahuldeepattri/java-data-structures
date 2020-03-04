package com.rd.tree;

import java.lang.reflect.Array;

public abstract class BinaryHeap<T extends Comparable<T>> {
    private T[] array;
    /**
     * Store the current length of array, helps in insertion and removal
     **/
    private int count = 0;

    public BinaryHeap(Class<T> clazz, int size) {
        this.array = (T[]) Array.newInstance(clazz, size);
    }

    public BinaryHeap(int size) {
        this.array = (T[]) new Comparable[size];
    }

    public int getLeftChildIndex(int idx) {
        int l = 2 * idx + 1;
        if (l >= count) // check to see if the left child exists or not, we might get arrays out of index exception
            return -1;
        return l;
    }

    public int getRightChildIndex(int idx) {
        int r = 2 * idx + 2;
        if (r >= count) // check to see if the right child exists or not, we might get arrays out of index exception
            return -1;
        return r;
    }

    public int getParentIndex(int idx) {
        if (idx > count || idx < 0) // check to see if the parent can be found, we might get arrays out of index exception
            return -1;
        return (idx - 1) / 2;
    }

    public int getCount() {
        return count;
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public boolean isFull() {
        return count == array.length;
    }

    public T getElementAtIndex(int idx) {
        return array[idx];
    }

    protected void swap(int idx1, int idx2) {
        T temp = array[idx1];
        array[idx1] = array[idx2];
        array[idx2] = temp;
        temp = null; //dont need to do this as when this frame is popped reference wont remain
    }

    public abstract void siftDown(int idx);

    public abstract void siftUp(int idx);

    public int insert(T data) throws HeapFullException {
        if (isFull())
            throw new HeapFullException();
        array[count] = data;
        siftUp(count);
        return count++;
    }

    public T removeHighestPriority() throws HeapEmptyException {
        if (isEmpty())
            throw new HeapEmptyException();
        T temp = array[0];
        swap(0, count - 1);
        siftDown(0);
        count--;
        return temp;
    }

    public T getHighestPriority() throws HeapEmptyException {
        if (isEmpty())
            throw new HeapEmptyException();
        return array[0];
    }

    public void printHeapArray() {
        for (int i = 0; i < count; i++) {
            System.out.print(array[i] + ", ");
        }
        System.out.println();

        try {
            System.out.println("Highest priority: " + getHighestPriority());
        } catch (HeapEmptyException ex) {

        }
    }

    public static class HeapFullException extends Exception {
        public HeapFullException() {
            super("Heap is full");
        }
    }

    public static class HeapEmptyException extends Exception {
        public HeapEmptyException() {
            super("Heap is full");
        }
    }
}
