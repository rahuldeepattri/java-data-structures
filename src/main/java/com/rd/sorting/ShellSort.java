package com.rd.sorting;

// Use Case - Uses insertion sort & adaptable, prefered over insertion sort as its complexity is less than O(n ^ 2)
// Time Complexity - bw O(n) and O(n^2)
// Space Complexity - O(1)
// Is Stable - Yes
// Is Adatable? - Yes
// Swaps and Comparisions - Below
public class ShellSort {

    int swaps = 0;
    int comparisons = 0;

    public <T extends Comparable<T>> void sort(T[] array) {
        
        int increment = array.length / 2;

        while (increment >= 1) {
            //for each sublist do insertion sort
            for (int startIdx = 0; startIdx < increment; startIdx++) {
                insertionSort(array, startIdx, increment);
            }
            increment /= 2;
        }
    }


    public <T extends Comparable<T>> void  insertionSort(T[] array, int startIdx, int increment) {
        
        for (int i = startIdx; i < array.length - increment; i++) {// last element is taken care by inner loop

            for(
                int j = i + increment;
                j - increment >= startIdx;
                j = j - increment
            ) { // bubble in the element outside the list
                T toAdd = array[j];
                T sorted = array[j - increment];
                
                this.comparisons++;
                if(toAdd.compareTo(sorted) < 0) {
                    swap(array, j, j - increment);
                } else //the new element has reached correct place in sorted sub list
                    break;
            }
        }

    }

    private void swap(Object[] array, int a, int b) {
        Object temp = array[a];
        array[a] = array[b];
        array[b] = temp;
        this.swaps++;
    }

    @Override
    public String toString() {
        return "ShellSort{" + "swaps=" + swaps + ", comparisons=" + comparisons + '}';
    }
}
