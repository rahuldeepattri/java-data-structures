package com.rd.sorting;

// Use Case - Slightly complex & adaptable, when n is small bec less swaps and comparisons
// Time Complexity - O(n^2)
// Space Complexity - O(1)
// Is Stable - Yes
// Is Adatable? - Yes, but has worst case O(n)
// Swaps and Comparisions - Below
public class InsertionSort {

    // O(n^2) but on avg it is less
    int swaps = 0;
    // O(n^2) but on avg it is less
    int comparisons = 0;

    public <T extends Comparable<T>> void sort(T[] array) {


        for (int i = 0; i < array.length - 1; i++) {
            // Go upto second last element, the last element will be taken care
            // of by inner loop

            // Consider that till index i the list is sorted,
            // we call it sorted sublist

            //Add one item to sorted list
            for (int j = i + 1; j > 0; j--) {
                //Bubble in the element outside the sorted sublist

                T toAdd = array[j];
                T sorted = array[j - 1];

                comparisons++;
                if (toAdd.compareTo(sorted) < 0) {
                    swap(array, j, j - 1);
                } else //the new element has reached correct place in sorted sub list
                    break;
            }

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
        return "InsertionSort{" + "swaps=" + swaps + ", comparisons=" + comparisons + '}';
    }
}
