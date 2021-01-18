package com.rd.sorting;



// Use Case -
// Time Complexity - O()
// Space Complexity - O()
// Is Stable - 
// Is Adatable? - 
// Swaps and Comparisions - 
public class Sort {
    static <T extends Comparable<T>> void selectionSortLessSwaps(T[] array) {
        int steps = 0;
        int swaps = 0;
        int comparisons = 0;
        int min;
        for (int i = 0; i < array.length - 1; i++) {
            min = i;
            for (int j = i + 1; j < array.length; j++) {
                comparisons++;
                if (array[min].compareTo(array[j]) > 0)
                    min = j;
            }
            swap(array, i, min);
            swaps++;
            steps++;
            print(array, steps, comparisons, swaps);

        }
    }

    static <T extends Comparable<T>> void selectionSort(T[] array) {
        int steps = 0;
        int swaps = 0;
        int comparisons = 0;

        for (int i = 0; i < array.length; i++) {
            for (int j = i; j < array.length; j++) {
                comparisons++;
                if (array[i].compareTo(array[j]) > 0) {
                    swap(array, i, j);
                    swaps++;
                }
            }
            steps++;
            print(array, steps, comparisons, swaps);
        }
    }

    static <T extends Comparable<T>> void bubbleSort(T[] array) {
        int steps = 0;
        int swaps = 0;
        int comparisons = 0;

        boolean isSwapped;
        for (int i = 0; i < array.length - 1; i++) {
            isSwapped = false;
            for (int j = 0; j < array.length - 1 - i; j++) {
                comparisons++;
                if (array[j].compareTo(array[j + 1]) > 0) {
                    swap(array, j, j + 1);
                    swaps++;
                    isSwapped = true;
                }
            }
            steps++;
            print(array, steps, comparisons, swaps);
            if (!isSwapped)
                break;
        }
    }

    static <T extends Comparable<T>> void insertionSort(T[] array) {
        int steps = 0;
        int swaps = 0;
        int comparisons = 0;

        for (int i = 1; i < array.length; i++) {
            for (int j = i; j > 0; j--) {
                comparisons++;
                if (array[j].compareTo(array[j - 1]) < 0) { // note the less than sign
                    swap(array, j, j - 1);
                    swaps++;
                } else
                    break; // Notice we can break when we know that current element is greater than one element before it
                //insertion is adaptive but less as compared to bubble sort because we come out of inner loop only
            }
            steps++;
            print(array, steps, comparisons, swaps);
        }
    }

    static <T extends Comparable<T>> void insertionSort(T[] array, int startIdx, int increment) {
        int steps = 0;
        int swaps = 0;
        int comparisons = 0;

        for (int i = startIdx + increment; i < array.length; i += increment) {
            for (int j = i; j - increment >= 0; j -= increment) {
                comparisons++;
                if (array[j].compareTo(array[j - increment]) < 0) { // note the less than sign
                    swap(array, j, j - increment);
                    swaps++;
                } else
                    break; // Notice we can break when we know that current element is greater than one element before i
            }
            steps++;
            System.out.print("Increment = " + increment + ", ");
            print(array, steps, comparisons, swaps);
        }
    }

//    static <T extends Comparable<T>> void shellSort(T[] array, int k) {
//        int steps = 0;
//        int swaps = 0;
//        int comparisons = 0;
//        for (int l = k; l > 0; l--) {
//            for (int i = 0; i < array.length; i += l) {
//                steps++;
//                for (int j = i; j > 0; j -= l) {
//                    comparisons++;
//                    if (array[j].compareTo(array[j - l]) < 0) {
//                        swap(array, j, j - l);
//                        swaps++;
//                    }
//                    else
//                        break;
//
//                }
//                print(array, steps, comparisons, swaps);
//            }
//        }
//    }

    static <T extends Comparable<T>> void shellSort(T[] array) {
        int increment = array.length / 2;
        while (increment >= 1) {
            for (int startIdx = 0; startIdx < increment; startIdx++) {
                insertionSort(array, startIdx, increment);
            }
            increment /= 2;
        }
    }

    @SuppressWarnings("unchecked")
    static <T extends Comparable<T>> void mergeSort(T[] array) {
        if (array.length < 2)
            return;
        int mid = array.length >> 1;

        //Note we have to be care full of fact that mid could be zero and int[0] wont store any value
        // better to play with length than indexes
        T[] first = (T[]) new Comparable[mid];
        T[] second = (T[]) new Comparable[array.length - mid];

        split(array, first, second);
        mergeSort(first);
        mergeSort(second);
        sortedMerger(array, first, second);
        print(array);

    }

    private static <T extends Comparable<T>> void sortedMerger(T[] array, T[] first, T[] second) {
        int mergeIdx = 0;
        int firstIdx = 0;
        int secondIdx = 0;

        while (firstIdx < first.length && secondIdx < second.length) {
            if (first[firstIdx].compareTo(second[secondIdx]) < 0) {
                array[mergeIdx] = first[firstIdx];
                firstIdx++;
            } else {
                array[mergeIdx] = second[secondIdx];
                secondIdx++;
            }
            mergeIdx++;
        }

        while (firstIdx < first.length) {
            array[mergeIdx] = first[firstIdx];
            firstIdx++;
            mergeIdx++;
        }
        while (secondIdx < second.length) {
            array[mergeIdx] = second[secondIdx];
            secondIdx++;
            mergeIdx++;
        }

    }

    private static <T> void split(T[] array, T[] first, T[] second) {
        System.arraycopy(array, 0, first, 0, first.length);
        System.arraycopy(array, first.length, second, 0, second.length);
    }


    private static int split(int start, int end) {
        return start + ((end - start) >> 1);
    }

    private static <T extends Comparable<T>> void sortedMergeInPlace(T[] array, int start1, int end1, int start2, int end2) {
        int first = start1;
        int second = start2;
        //TODO n2 logn
        while (first <= end1 && second <= end2) {
            // one list is sorted and elements from 2nd list just bubble infirst list
            if (array[first].compareTo(array[second]) > 0) {
                swap(array, first, second);
            } else
                first++;
        }
    }

    private static <T extends Comparable<T>> void quickSort(T[] array) {
        if (array.length < 2)
            return;
        quickSortDriver(array, 0, array.length - 1);
    }

    private static <T extends Comparable<T>> void quickSortDriver(T[] array, int low, int high) {
        if (high <= low)
            return;
        int pivot = partition(array, low, high);
        quickSortDriver(array, pivot + 1, high);
        quickSortDriver(array, low, pivot - 1);
    }

    private static <T extends Comparable<T>> int partition(T[] array, int low, int high) {
        T pivot = array[0];
        int l = low;
        int h = high;

        while (l < h) {
            while (array[l].compareTo(pivot) <= 0 && l < h) { //l < h meaning we haven't crossed each other
                l++;
            }
            while (array[h].compareTo(pivot) > 0) {
                h--;
            }
            if (l < h) // check if we havent crossed h and l
                swap(array, h, l);
        }
        swap(array, low, h); // in last step the l and h will cross each other and h now holds position where pivot should be moved
        System.out.println("Pivot is: " + pivot);
        print(array);
        return h;
    }


    private static <T> void swap(T[] array, int i, int j) {
        //dont use fancy swaps bec if i and j are same then we will get wrong output
        T t = array[i];
        array[i] = array[j];
        array[j] = t;
    }

    private static <T> void print(T[] array, int steps, int comparisons, int swaps) {
        for (T value : array)
            System.out.print(value + " ");
        System.out.println("[steps: " + steps + ", comparisons: " + comparisons + ", swaps: " + swaps + "]");
    }

    private static <T> void print(T[] array) {
        for (T value : array)
            System.out.print(value + " ");
        System.out.println("");
    }
}
