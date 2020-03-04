package com.rd.tree;

public class MaxHeap<T extends Comparable<T>> extends BinaryHeap<T> {
    public MaxHeap(int size) {
        super(size);
    }

    @Override
    public void siftDown(int idx) {
        int left = getLeftChildIndex(idx);
        int right = getRightChildIndex(idx);
        int max = -1;
        if (left != -1 && right != -1) {
            max = getElementAtIndex(left).compareTo(getElementAtIndex(right)) >= 0
                    ? left : right;
        } else if (left != -1)
            max = left;
        else if (right != -1)
            max = -1;
        else
            return;
        if (getElementAtIndex(idx).compareTo(getElementAtIndex(max)) < 0) {
            swap(idx, max);
            siftDown(max);
        }
    }

    @Override
    public void siftUp(int idx) {
        int parent = getParentIndex(idx);
        if (parent != 1
                && getElementAtIndex(idx).compareTo(getElementAtIndex(parent)) > 0) {
            swap(idx, parent);
            siftDown(parent);
        }
    }
}
