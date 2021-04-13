package com.rd.sorting;

// Use Case - Best in most cases
// Time Complexity - O(nlogn)
// Space Complexity - O(1)
// Is Stable - No
// Is Adatable? - No
// Swaps and Comparisions - Below
public class QuickSort {


    int swaps = 0;

    // O(n^2)
    int comparisons = 0;

    public <T extends Comparable<T>> void sort(T[] array) {

        quickSort(array, 0, array.length - 1);

    }

    public <T extends Comparable<T>> void quickSort(T[] array, int startIdx, int endIdx) {

        if (startIdx >= endIdx)
            return;

        int pivot = partition(array, startIdx, endIdx);

        quickSort(array, startIdx, pivot - 1); // since pivot is at correct position
        quickSort(array, pivot + 1, endIdx);

    }

    // https://ocw.mit.edu/courses/electrical-engineering-and-computer-science/6-046j-introduction-to-algorithms-sma-5503-fall-2005/video-lectures/lecture-4-quicksort-randomized-algorithms/lec4.pdf
    private <T extends Comparable<T>> int partition(T[] array, int startIdx, int endIdx) {

        int r = (int) ((Math.random() * (endIdx - startIdx)) + startIdx);

        swap(array, startIdx, r);


        // Note we are comparing pivot element and jth element,
        // and not partitionIdx th element and j th element
        T pivot = array[startIdx];
        int partitionIdx = startIdx; //used for tracking the final position of pivot element

        // all elements from start to partitionIdx are <= pivot and
        // all the elements from partitionIdx to j are >= pivot
        // all the elements after j till end are unknown - our job is to discover all

        // try increasing partitionIdx
        for (int j = startIdx + 1; j <= endIdx; j++) {

            T element = array[j];

            comparisons++;
            // 3 2 3 5 1 // 2 1
            if (pivot.compareTo(element) > 0) {
                // if pivot element is greater than current element then
                // 1. If we increment j now then,
                // all the elements from partitionIdx to j are  not >= pivot
                // because the current element will be less that pivot element
                partitionIdx++; //increase partitionIdx
                swap(array, j, partitionIdx); // and swap so above property is true
                // 2 3 3 5 1
            } else {
                // 3 5 3 2 1
                // i j
                // 2 5 3 3 1
                // i j
                // 2 1 3 3 5
                // i
                // do nothing
            }

        }
        swap(array, startIdx, partitionIdx);

        return partitionIdx;
    }

    private void swap(Object[] array, int a, int b) {
        Object temp = array[a];
        array[a] = array[b];
        array[b] = temp;
        swaps++;
    }

    @Override
    public String toString() {
        return "QuickSort{" + "swaps=" + swaps + ", comparisons=" + comparisons + '}';
    }
}
