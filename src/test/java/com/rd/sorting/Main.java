package com.rd.sorting;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertFalse;

public class Main {
    Integer[] int0;
    Integer[] int1;
    Integer[] int2;
    Integer[] int3;
    Integer[] int4;
    Integer[] int5;
    Integer[] int6;
    Integer[] int7;
    Integer[] int8;
    Integer[] int9;
    Integer[] int10;
    Integer[] int11;
    Integer[] int12;
    Integer[] int13;
    Integer[] int14;
    Integer[] int15;

    Integer[] res0;
    Integer[] res1;
    Integer[] res2;
    Integer[] res3;
    Integer[] res4;
    Integer[] res5;
    Integer[] res6;
    Integer[] res7;
    Integer[] res8;
    Integer[] res9;
    Integer[] res10;
    Integer[] res11;
    Integer[] res12;
    Integer[] res13;
    Integer[] res14;
    Integer[] res15;

    Integer[][] tests;
    Integer[][] results;

    Pair[] stabletest;
    Pair[] stableresult;

    @Before
    public void initialize() {
        int0 = new Integer[]{};
        int1 = new Integer[]{1};
        int2 = new Integer[]{2, 1};
        int3 = new Integer[]{1, 2};
        int4 = new Integer[]{1, 1};
        int5 = new Integer[]{1, 1, 1};
        int13 = new Integer[]{1, 2, 3, 4};
        int6 = new Integer[]{1, 3, 2};
        int7 = new Integer[]{2, 3, 1};
        int8 = new Integer[]{3, 2, 1};
        int9 = new Integer[]{3, 2, 3};
        int10 = new Integer[]{4, 5, 5, 1};
        int11 = new Integer[]{5, 5, 4, 5};
        int12 = new Integer[]{5, 5, 4, 5, 5};
        int14 = new Integer[]{4, 5, 6, 2, 1, 7, 10, 3, 8, 9};
        int15 = new Integer[]{5, 4, 3, 2, 1};

        res0 = new Integer[]{};
        res1 = new Integer[]{1};
        res2 = new Integer[]{1, 2};
        res3 = new Integer[]{1, 2};
        res4 = new Integer[]{1, 1};
        res5 = new Integer[]{1, 1, 1};
        res13 = new Integer[]{1, 2, 3, 4};
        res6 = new Integer[]{1, 2, 3};
        res7 = new Integer[]{1, 2, 3};
        res8 = new Integer[]{1, 2, 3};
        res9 = new Integer[]{2, 3, 3};
        res10 = new Integer[]{1, 4, 5, 5};
        res11 = new Integer[]{4, 5, 5, 5};
        res12 = new Integer[]{4, 5, 5, 5, 5};
        res14 = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        res15 = new Integer[]{1, 2, 3, 4, 5};

        tests = new Integer[][]{int0, int1, int2, int3, int4, int5, int6, int7, int8, int9, int10, int11, int12,
                int13, int14, int15};
        results = new Integer[][]{res0, res1, res2, res3, res4, res5, res6, res7, res8, res9, res10, res11, res12,
                res13, res14, int15};

        stabletest = new Pair[]{new Pair(0, 1), new Pair(1, 1), new Pair(2, 4), new Pair(3, 2), new Pair(5, 4),
                new Pair(6, 2), new Pair(7, 3)};
        stableresult = new Pair[]{new Pair(0, 1), new Pair(1, 1), new Pair(3, 2), new Pair(6, 2), new Pair(7, 3),
                new Pair(2, 4), new Pair(5, 4)};

    }

    @Test
    public void SelectionSort() {
        for (int i = 0; i < tests.length; i++) {
            System.out.println("Selection Sort Test: " + i + ": " + Arrays.toString(tests[i]));
            SelectionSort sorter = new SelectionSort();
            sorter.sort(tests[i]);
            System.out.println(sorter);
            assertArrayEquals(results[i], tests[i]);
        }

    }

    @Test
    public void bubbleSort() {
        for (int i = 0; i < tests.length; i++) {
            System.out.println("Bubble Sort Test: " + i + ": " + Arrays.toString(tests[i]));
            BubbleSort sorter = new BubbleSort();
            sorter.sort(tests[i]);
            System.out.println(sorter);
            assertArrayEquals(results[i], tests[i]);
        }

    }

    @Test
    public void insertionSort() {
        for (int i = 0; i < tests.length; i++) {
            System.out.println("Insertion Sort Test: " + i + ": " + Arrays.toString(tests[i]));
            InsertionSort sorter = new InsertionSort();
            sorter.sort(tests[i]);
            System.out.println(sorter);
            assertArrayEquals(results[i], tests[i]);
        }

    }


    @Test
    public void shellSortInsertion() {

        for (int i = 0; i < tests.length; i++) {
            System.out.println("Insertion Sort Test: " + i + ": " + Arrays.toString(tests[i]));
            ShellSort sorter = new ShellSort();
            sorter.insertionSort(tests[i], 0, 1);
            System.out.println(sorter);
            assertArrayEquals(results[i], tests[i]);
        }
    }

    @Test
    public void shellSort() {
        for (int i = 0; i < tests.length; i++) {
            System.out.println("Shell Sort Test: " + i + ": " + Arrays.toString(tests[i]));
            ShellSort sorter = new ShellSort();
            sorter.sort(tests[i]);
            System.out.println(sorter);
            assertArrayEquals(results[i], tests[i]);
        }

    }


    @Test
    public void mergeSort() {
        for (int i = 0; i < tests.length; i++) {
            System.out.println("Merge Sort Test: " + i + ": " + Arrays.toString(tests[i]));
            MergeSort sorter = new MergeSort();
            sorter.sort(tests[i]);
            System.out.println(sorter);
            assertArrayEquals(results[i], tests[i]);
        }
        System.out.println("Not Stable Test: " + Arrays.toString(stabletest));
        MergeSort sorter = new MergeSort();
        sorter.sort(stabletest);
        Assert.assertTrue(Arrays.equals(stableresult, stabletest)); // assertFalse
    }


    @Test
    public void heapSort() {
        for (int i = 0; i < tests.length; i++) {
            System.out.println("Quick Sort Test: " + i + ": " + Arrays.toString(tests[i]));
            HeapSort sorter = new HeapSort();
            sorter.sort(tests[i]);
            System.out.println(sorter);
            assertArrayEquals(results[i], tests[i]);
        }

    }

    @Test
    public void quickSort() {
        for (int i = 0; i < tests.length; i++) {
            System.out.println("Quick Sort Test: " + i + ": " + Arrays.toString(tests[i]));
            QuickSort sorter = new QuickSort();
            sorter.sort(tests[i]);
            System.out.println(sorter);
            assertArrayEquals(results[i], tests[i]);
        }

    }



}

class Pair implements Comparable<Pair> {
    int index;
    int value;

    Pair(int i, int v) {
        index = i;
        value = v;
    }

    @Override
    public int compareTo(Pair o) {
        return Integer.compare(value, o.value);
    }

    @Override
    public String toString() {
        return "[idx: " + index + ", val: " + value + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Pair))
            return false;
        Pair check = (Pair) obj;
        return index == check.index && value == check.value;
    }
}