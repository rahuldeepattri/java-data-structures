package com.rd.sorting;

// Use Case - Simple and Good for very less swaps
// Time Complexity - O(n^2)
// Space Complexity - O(1)
// Is Stable - No, can be made
// Is Adatable? - No
// Swaps and Comparisions - Below
public class SelectionSort {

    // O(n)
    int swaps = 0;
    // O(n(n+1)/2)
    int comparisons = 0;

    public <T extends Comparable<T>> void sort(T[] array) {

        for (int i = 0; i < array.length; i++) {
            int maxIdx = 0;
            int lastIdx = array.length - i - 1;
            for (int j = 0; j <= lastIdx; j++) {
                T curr = array[j];
                T max = array[maxIdx];
                comparisons++;
                if (max.compareTo(curr) < 0) {
                    maxIdx = j;
                }
            }
            swap(array, maxIdx, lastIdx);
        }
    }

    private void swap(Object[] array, int a, int b) {
        Object temp = array[a];
        array[a] = array[b];
        array[b] = temp;
        swaps++;
    }

    @Override
    public String toString() {
        return "SelectionSort{" + "swaps=" + swaps + ", comparisons=" + comparisons + '}';
    }
}
