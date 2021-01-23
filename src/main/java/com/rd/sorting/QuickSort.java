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

        int r = (int) Math.random() * (endIdx - startIdx) + startIdx;

        swap(array, startIdx, r);

        // all elements from start + 1 to pivotIdx are <= pivot and all the elements from pivotIdx to
        // j are >= pivot
        // Note we are comparing pivot element and jth element, and not pivotIdx th element and j th element
        T pivot = array[startIdx];
        int pivotIdx = startIdx;

        for (int j = startIdx + 1; j <= endIdx; j++) {

            T element = array[j];

            comparisons++;
            // 3 2 3 5 1 // 2 1
            if (pivot.compareTo(element) > 0) {
                pivotIdx++;
                swap(array, j, pivotIdx);
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
        swap(array, startIdx, pivotIdx);

        return pivotIdx;
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
