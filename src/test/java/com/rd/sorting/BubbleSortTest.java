package com.rd.sorting;

import org.junit.Test;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BubbleSortTest {

    @Test
    public void test() {

        BubbleSort sorter = new BubbleSort();

        Integer[] array = Stream.of(2, 5, 6, 1, 3, 4)
                .collect(Collectors.toList())
                .toArray(new Integer[0]);

        System.out.println(Arrays.toString(array));
        sorter.sort(array);
        System.out.println(sorter);
        System.out.println(Arrays.toString(array));


    }
}
