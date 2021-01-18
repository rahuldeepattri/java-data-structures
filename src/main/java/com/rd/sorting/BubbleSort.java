package com.rd.sorting;

// Use Case - Simple and Adaptable
// Time Complexity - O(n^2)
// Space Complexity - O(1)
// Is Stable - Yes
// Is Adatable? - Yes, but has worst case O(n)
// Swaps and Comparisions - Below
public class BubbleSort {

    // O(n^2)
    int swaps = 0;
    // O(n^2)
    int comparisons = 0;

    public <T extends Comparable<T>> void sort(T[] array) {

        for (int i = 0; i < array.length; i++) {

            boolean isSwap = false;

            for (int j = 0; j < array.length - i - 1; j++) {
                T curr = array[j];
                T next = array[j + 1];

                comparisons++;
                if (curr.compareTo(next) > 0) {
                    swap(array, j, j + 1);
                    isSwap = true;
                }
            }
            if (!isSwap) // the list is already sorted
                break;
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
        return "BubbleSort{" + "swaps=" + swaps + ", comparisons=" + comparisons + '}';
    }
}
