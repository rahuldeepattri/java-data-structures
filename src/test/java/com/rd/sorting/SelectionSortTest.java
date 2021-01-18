package com.rd.sorting;

import com.rd.sorting.SelectionSort;
import java.util.*;
import java.util.stream.*;
import org.junit.Test;

public class SelectionSortTest {

    @Test
    public void test() {

        SelectionSort sorter = new SelectionSort();

        Integer[] array = Stream.of(2,5,6,1,3,4)
                .collect(Collectors.toList())
                .toArray(new Integer[0]);
        
        System.out.println(Arrays.toString(array));
        sorter.sort(array);
        System.out.println(sorter);
        System.out.println(Arrays.toString(array));


    }
}
