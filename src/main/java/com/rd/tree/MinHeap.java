package com.rd.tree;

public class MinHeap<T extends Comparable<T>> extends BinaryHeap<T> {

    public MinHeap(int size) {
        super(size);
    }

    @Override
    public void siftDown(int idx) {
        int left = getLeftChildIndex(idx);
        int right = getRightChildIndex(idx);
        //Find minimum of two
        int min = -1;

        if (left != -1 && right != -1)
            min = getElementAtIndex(left).compareTo(getElementAtIndex(right)) <= 0 //may need need to swap if values are same
                    ? left : right;
        else if (left != -1)
            min = left;
        else if (right != -1)
            min = right;

        //Now both child dont exist
        if (min == -1)
            return;
        //compare and sift
        if (getElementAtIndex(idx).compareTo(getElementAtIndex(min)) > 0) {
            swap(idx, min);
            siftDown(min);
        }

    }


    @Override
    public void siftUp(int idx) {
        int parent = getParentIndex(idx);
        if (parent == -1)
            return;
        if (getElementAtIndex(parent).compareTo(getElementAtIndex(idx)) > 0) {
            swap(idx, parent);
            siftUp(parent);
        }
    }

    public T getMin() {

        try {
            return getHighestPriority();
        } catch (BinaryHeap.HeapEmptyException Ignore) {
            return null;
        }
    }


}
