package com.rd.sorting;

import java.util.ArrayList;
import java.util.List;

// Use Case - Faster than insertion sort, but uses extra space
// Time Complexity - O(nlogn)
// Space Complexity - O(n + log(n))
// Is Stable - Yes
// Is Adaptable? - No
public class MergeSort {

    int maxSize = 0;
    int comparisons = 0;

    public <T extends Comparable<T>> void sort(T[] array) {
        mergeSort(array, 0, array.length - 1);
    }

    public <T extends Comparable<T>> void mergeSort(T[] array, int startIdx, int endIdx) {

        if (startIdx == endIdx || endIdx < startIdx) // Trivial Case
            return;

        // 0 9 -> 4
        // 0 4 , 5 9
        // 0 2, 3 4, 5 7, 8,9
        // 0 0, 1 2, 3 3, 4 4, 5 6, 6 7, 8 8, 9 9
        int mid = (endIdx - startIdx) / 2 + startIdx;

        mergeSort(array, startIdx, mid);
        mergeSort(array, mid + 1, endIdx);

        sortedMerge(array, startIdx, mid, mid + 1, endIdx);

    }

    private <T extends Comparable<T>> void sortedMerge(T[] array, int fs, int fe, int ss, int se) {
        int size = fe - fs + se - ss + 2;
        if (size > maxSize)
            maxSize = size;

        List<T> result = new ArrayList<>(size);

        int fptr = fs; // first ptr
        int sptr = ss; // second ptr
        while (fptr <= fe && sptr <= se) {

            T first = array[fptr];
            T second = array[sptr];

            comparisons++;
            if (first.compareTo(second) <= 0) {
                result.add(first);
                fptr++;
            } else {
                result.add(second);
                sptr++;
            }

        }

        while (fptr <= fe) {
            T first = array[fptr];
            result.add(first);
            fptr++;
        }
        while (sptr <= se) {
            T second = array[sptr];
            result.add(second);
            sptr++;
        }

        int resPtr = 0;
        for (int i = fs; i <= se; i++) {
            array[i] = result.get(resPtr);
            resPtr++;
        }
    }

    @Override
    public String toString() {
        return "MergeSort{" + "MaxSize=" + maxSize + ", comparisons=" + comparisons + '}';
    }
}
