package com.rd.tree;

import org.junit.Test;

public class TestHeap {
    @Test
    public void TestMin() throws BinaryHeap.HeapFullException, BinaryHeap.HeapEmptyException {
        MinHeap<Integer> minHeap = new MinHeap<>(30);

        minHeap.insert(9);
        minHeap.insert(4);
        minHeap.insert(17);
        minHeap.printHeapArray();
        minHeap.insert(6);
        minHeap.printHeapArray();

        minHeap.insert(100);
        minHeap.insert(20);
        minHeap.printHeapArray();
        minHeap.insert(2);
        minHeap.insert(1);
        minHeap.insert(5);
        minHeap.insert(3);
        minHeap.printHeapArray();

        minHeap.removeHighestPriority();
        minHeap.printHeapArray();
        minHeap.removeHighestPriority();
        minHeap.printHeapArray();
    }

    @Test
    public void TestMax() throws BinaryHeap.HeapFullException, BinaryHeap.HeapEmptyException {
        MaxHeap<Integer> maxHeap = new MaxHeap<>(40);

        maxHeap.insert(9);
        maxHeap.insert(4);
        maxHeap.insert(17);
        maxHeap.printHeapArray();
        maxHeap.insert(6);
        maxHeap.printHeapArray();

        maxHeap.insert(100);
        maxHeap.insert(20);
        maxHeap.insert(2);
        maxHeap.insert(1);
        maxHeap.printHeapArray();
        maxHeap.insert(5);
        maxHeap.insert(3);
        maxHeap.printHeapArray();

        maxHeap.removeHighestPriority();
        maxHeap.printHeapArray();
        maxHeap.removeHighestPriority();
        maxHeap.printHeapArray();
    }
}
