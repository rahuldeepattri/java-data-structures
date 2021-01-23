package com.rd.sorting;

/**
 * If we are using in place heap sort then its not adaptive.
 * we dont need to sift up but will need to sift down
 * <p>
 * no extra memory !
 * n logn !
 */
public class HeapSort<T extends Comparable<T>> {

    private T[] array;
    private int size;

    int swaps = 0;
    int comparisons = 0;

    public void sort(T[] arr) {
        this.array = arr;
        this.size = 0;
        for (int i = 0; i < arr.length; i++) {
            size++;
            siftUp(i);
        }

        while (size > 0) {
            swap(0, size - 1);
            size--;
            siftDown(0);
        }
    }

    /**
     * Move the node down to correct place
     *
     * @param idx
     */
    private void siftDown(int idx) {
        int leftChildIdx = getLeftChildIdx(idx);
        int rightChildIdx = getRightChildIdx(idx);

        if (leftChildIdx < 0 && rightChildIdx < 0) { // No more node to check
            return;
        }

        int toSwap;

        if (leftChildIdx >= 0 && rightChildIdx >= 0) {
            T left = array[leftChildIdx];
            T right = array[rightChildIdx];

            /*
                    35
                  60   75
             */
            comparisons++;
            toSwap = (left.compareTo(right) > 0) ? leftChildIdx : rightChildIdx;

        } else if (leftChildIdx >= 0) {
            toSwap = leftChildIdx;
        } else {
            toSwap = rightChildIdx;
        }
        T curr = array[idx];
        T child = array[toSwap];

        comparisons++;
        if (curr.compareTo(child) < 0) {
            swap(idx, toSwap);
            siftDown(toSwap);
        }

    }

    private void siftUp(int idx) {
        int parentIdx = getParentIdx(idx);
        if (parentIdx < 0) {
            return;
        }
        T parent = array[parentIdx];
        T curr = array[idx];

        comparisons++;
        if (curr.compareTo(parent) > 0) {
            swap(idx, parentIdx);
            siftUp(parentIdx);
        }

    }

    private int getParentIdx(int idx) {
        if (idx < 0 || idx >= size) {
            return -1;
        }
        return (idx - 1) / 2;
    }

    private void swap(int idx, int toSwap) {
        T tmp = array[idx];
        array[idx] = array[toSwap];
        array[toSwap] = tmp;
        swaps++;
    }

    private int getRightChildIdx(int idx) {
        int right = 2 * idx + 2;
        if (right >= size)
            return -1;
        return right;
    }

    private int getLeftChildIdx(int idx) {
        int left = 2 * idx + 1;
        if (left >= size)
            return -1;
        return left;
    }

    @Override
    public String toString() {
        return "HeapSort{" + "swaps=" + swaps + ", comparisons=" + comparisons + '}';
    }
}

//phase 1 - heapify
// for each node sift it up (n log n)

//
//heuristic here the no of ops will be less??
// also same sift down code can be use for the next phase
// find the last parent and for each node in reversed list
// from last parent, sift down - n log n


//phase 2 - sort
// do n times
// get max element and swap it with last element of heap and reduce size of heap
// sift down first element
