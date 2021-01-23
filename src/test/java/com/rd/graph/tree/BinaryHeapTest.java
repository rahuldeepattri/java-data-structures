package com.rd.graph.tree;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BinaryHeapTest {

    @Test
    public void maxHeapTest() {
        Comparator<Integer> minComparator = Integer::compareTo;
        Comparator<Integer> maxComparator = minComparator.reversed();

        BinaryHeap<Integer> maxHeap = new BinaryHeap<>(maxComparator);

        maxHeap.insert(9);
        assertEquals((Integer) 9, maxHeap.getHighestPriority());
        assertEquals((Integer) 9, maxHeap.getLowestPriority());

        maxHeap.insert(4);
        assertEquals((Integer) 9, maxHeap.getHighestPriority());
        assertEquals((Integer) 4, maxHeap.getLowestPriority());

        maxHeap.insert(17);
        assertEquals((Integer) 17, maxHeap.getHighestPriority());
        assertEquals((Integer) 4, maxHeap.getLowestPriority());

        maxHeap.insert(6);
        assertEquals((Integer) 17, maxHeap.getHighestPriority());
        assertEquals((Integer) 4, maxHeap.getLowestPriority());

        maxHeap.insert(100);
        assertEquals((Integer) 100, maxHeap.getHighestPriority());
        assertEquals((Integer) 4, maxHeap.getLowestPriority());

        maxHeap.insert(20);
        assertEquals((Integer) 100, maxHeap.getHighestPriority());
        assertEquals((Integer) 4, maxHeap.getLowestPriority());

        maxHeap.printHeapArray();
        maxHeap.insert(2);
        maxHeap.insert(1);
        maxHeap.insert(5);
        maxHeap.insert(3);
        maxHeap.printHeapArray();

        maxHeap.removeHighestPriority();
        maxHeap.printHeapArray();
        maxHeap.removeHighestPriority();
        maxHeap.printHeapArray();

    }

    @Test
    public void minHeapTest() {

        BinaryHeap<Integer> maxHeap = new BinaryHeap<>();

        maxHeap.insert(9);
        assertEquals((Integer) 9, maxHeap.getHighestPriority());
        assertEquals((Integer) 9, maxHeap.getLowestPriority());

        maxHeap.insert(4);
        assertEquals((Integer) 4, maxHeap.getHighestPriority());
        assertEquals((Integer) 9, maxHeap.getLowestPriority());

        maxHeap.insert(17);
        assertEquals((Integer) 4, maxHeap.getHighestPriority());
        assertEquals((Integer) 17, maxHeap.getLowestPriority());

        maxHeap.insert(6);
        assertEquals((Integer) 4, maxHeap.getHighestPriority());
        assertEquals((Integer) 17, maxHeap.getLowestPriority());

        maxHeap.insert(100);
        assertEquals((Integer) 4, maxHeap.getHighestPriority());
        assertEquals((Integer) 100, maxHeap.getLowestPriority());

        maxHeap.insert(20);
        assertEquals((Integer) 4, maxHeap.getHighestPriority());
        assertEquals((Integer) 100, maxHeap.getLowestPriority());

        maxHeap.printHeapArray();
        maxHeap.insert(2);
        maxHeap.insert(1);
        maxHeap.insert(5);
        maxHeap.insert(3);
        maxHeap.printHeapArray();

        maxHeap.removeHighestPriority();
        maxHeap.printHeapArray();
        maxHeap.removeHighestPriority();
        maxHeap.printHeapArray();

    }

    @Test
    public void kLargest() {
        Stream<Integer> integerStream = Stream.of(12, 2, 34, 10, 4);

        List<Integer> list = BinaryHeap.kLargest(3, integerStream);
        System.out.println(list);
        assertTrue(Arrays.asList(10,34,12).containsAll(list));
    }

    @Test
    public void kSmallest() {
        Stream<Integer> integerStream = Stream.of(12, 2, 34, 10, 4);

        List<Integer> list = BinaryHeap.kSmallest(3, integerStream);
        System.out.println(list);
        assertTrue(Arrays.asList(2,10,4).containsAll(list));
    }
}
