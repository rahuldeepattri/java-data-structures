package com.rd.searching;

public class BinarySearch {

    public <T extends Comparable<T>> int find(T[] array, T element) {

        int start = 0;
        int end = array.length - 1;

        while (start <= end) {
            int mid = (end - start) / 2 + start;

            T midElement = array[mid];
            int compareTo = element.compareTo(midElement);

            if (compareTo < 0) {
                end = mid - 1;
            } else if (compareTo > 0) {
                start = mid + 1;
            } else {
                return mid;
            }

        }

        return -1;
    }

}
