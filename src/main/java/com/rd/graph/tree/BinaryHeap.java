package com.rd.graph.tree;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

/**
 * Heap is a tree with every node satisfying heap and shape property.
 * The nodes in the tree can have at most k children.
 * if k == 2 then it's a binary heap. k can tell how flat
 * out tree is.
 * <p>
 * Heap Property - Every node in a tree should have value >= or <= than of its children.
 * Shape Property - The tree should be complete ie nodes at level is filled
 * and then on a new level.
 */
public class BinaryHeap<T extends Comparable<T>> {

    /**
     * i th left child is at 2*i + 1 and right child is at 2*i + 2
     * i th parent is at (i - 1) / 2;
     */
    /**
     * Tree is only a logical form. Impl is using arrays only.
     */
    private final ArrayList<T> arrayList = new ArrayList<>();

    private Comparator<T> comparator;

    public BinaryHeap() {
        this.comparator = T::compareTo;
    }

    public BinaryHeap(Comparator<T> tComparator) {
        this.comparator = tComparator;
    }

    public void reverse() {

        this.comparator = comparator.reversed();
    }

    public int getLeftChildIdx(int idx) {
        int left = 2 * idx + 1;
        if (left >= arrayList.size()) { // no left element present
            return -1;
        }
        return left;
    }

    public int getRightChildIdx(int idx) {
        int right = (2 * idx) + 2;
        if (right >= arrayList.size()) { // no left element present
            return -1;
        }
        return right;
    }

    public int getParentIdx(int idx) {
        if (idx < 0 || idx >= arrayList.size()) {//idx not valid
            return -1;
        }
        return (idx - 1) / 2;
    }

    protected void swap(int idxA, int idxB) {
        T tmp = arrayList.get(idxA);
        arrayList.set(idxA, arrayList.get(idxB));
        arrayList.set(idxB, tmp);

    }

    public int getCount() {
        return arrayList.size();
    }

    public boolean isEmpty() {
        return arrayList.isEmpty();
    }

    public T getElementAtIdx(int idx) {
        return arrayList.get(idx);
    }

    private void insertAtLeaf(T element) {
        arrayList.add(element);
    }

    /**
     * The element at idx needs to move up.
     *
     * @param idx
     */
    protected void siftUp(int idx) {
        int parentIdx = getParentIdx(idx);
        if (parentIdx < 0) {
            return;
        }
        T parent = getElementAtIdx(parentIdx);
        T child = getElementAtIdx(idx);

        if (comparator.compare(parent, child) > 0) { //parent is greater than child
            swap(parentIdx, idx);
            siftUp(parentIdx);
        }

    }

    /**
     * The element at idx needs to move down.
     *
     * @param idx
     */
    protected void siftDown(int idx) {

        int leftChildIdx = getLeftChildIdx(idx);
        int rightChildIdx = getRightChildIdx(idx);
        if (rightChildIdx < 0 && leftChildIdx < 0) { // we dont have any children to move down
            return;
        }
        // we need to select a child and check if we need to move down
        int toSwap;
        if (rightChildIdx >= 0 && leftChildIdx >= 0) { // we have both children, select min
            int compareTo = comparator.compare(getElementAtIdx(rightChildIdx), getElementAtIdx(leftChildIdx));
            toSwap = compareTo < 0 ? rightChildIdx : leftChildIdx;
        } else if (rightChildIdx >= 0) {
            toSwap = rightChildIdx;
        } else {
            toSwap = leftChildIdx;
        }


        T currElement = getElementAtIdx(idx);
        T swapElement = getElementAtIdx(toSwap);

        if (comparator.compare(swapElement, currElement) < 0) { // below element is less than current
            swap(toSwap, idx);
            siftDown(toSwap);
        }


    }

    public void insert(T element) {
        insertAtLeaf(element);
        siftUp(getCount() - 1);
    }

    private int getLastIndex() {
        return arrayList.size() - 1;
    }

    public T getHighestPriority() {
        return arrayList.get(0);
    }

    public T removeHighestPriority() {
        int lastIndex = getLastIndex();
        if (lastIndex < 0) return null;

        T hp = arrayList.get(0);
        swap(0, lastIndex);
        arrayList.remove(lastIndex);
        siftDown(0);

        return hp;
    }

    public void printHeapArray() {
        for (T item : arrayList) {
            System.out.print(item + ", ");
        }
        System.out.println();

        System.out.println("Highest priority: " + getHighestPriority());

    }

    /**
     * O(n/2)
     *
     * @return
     */
    public T getLowestPriority() {
        int lastIdx = getLastIndex();
        int parentLastIdx = getParentIdx(lastIdx);

        if (parentLastIdx < 0) return null;
        else if (getCount() == 1) return getElementAtIdx(0);

        T lowest = arrayList.get(parentLastIdx + 1);
        for (int i = parentLastIdx + 2; i < arrayList.size(); i++) {
            T curr = arrayList.get(i);
            int compare = comparator.compare(lowest, curr);

        /*    System.out.print(lowest + ":");
            System.out.println(curr);
            System.out.println(compare);*/
            if (compare < 0) { // lowest priority will have high value in natural ordering
                lowest = curr;
//                System.out.println("swapped:" + lowest);
            }
        }

        return lowest;
    }

    // 1 2 3 4 5, k = 2 => 5, 4
    public static <T extends Comparable<T>> List<T> kLargest(int k, Stream<T> elements) {

        Comparator<T> natural = T::compareTo;
        return kElements(k, elements, natural);
    }

    // 5 4 3 2 1, k = 2 => 1, 2
    public static <T extends Comparable<T>> List<T> kSmallest(int k, Stream<T> elements) {

        Comparator<T> natural = T::compareTo;
        Comparator<T> reversed = natural.reversed();
        return kElements(k, elements, reversed);

    }

    private static <T extends Comparable<T>> List<T> kElements(int k, Stream<T> elements, Comparator<T> comparator) {
        BinaryHeap<T> heap = new BinaryHeap<>(comparator);

        elements.forEach(element -> {
            if (heap.getCount() < k ) {
                heap.insert(element);
            } else {
                T head = heap.getHighestPriority();
                if (comparator.compare(head, element) < 0) {
                    // the incoming element is bigger than minimum value
// then this element is a candidate, else if  //element that is coming is small then it won't be //part of the largest k elements
                    heap.removeHighestPriority();
                    heap.insert(element);
                }
            }
        });

        return heap.toList();
    }

    private List<T> toList() {
        return new ArrayList<>(arrayList);
    }


}


