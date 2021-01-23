package com.rahul.linkedlist;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.function.Consumer;

public class LinkedList<T extends Comparable<T>> {

    private Node<T> head;
    private Node<T> tail;
    private int size;

    public T peekHead() {
        if (head == null)
            return null;
        return head.getData();
    }

    public T peekTail() {
        if (tail == null)
            return null;
        return tail.getData();
    }

    public void append(T i) {
        this.add(i);
    }

    public void push(T i) {
        this.add(i);
    }

    public void insert(int idx, T data) {
        if (idx < 0 || idx > size)
            return;
        else if (idx == 0) {
            addToHead(data);
        } else if (idx == size) {
            addAtTail(data);
        } else {
            idx--;
            Node<T> prev = head;
            while (idx != 0) {
                prev = prev.getNext();
                idx--;
            }
            //1 2 3
            //prev, new, next
            prev.setNext(new Node<>(data, prev.getNext()));
            size++;
        }
    }

    public T get(int idx) {
        if (head == null || idx >= size || idx < 0)
            return null;
        Node<T> tmp = head;
        while (idx > 0) {
            tmp = tmp.getNext();
            idx--;
        }
        return tmp.getData();
    }

    public void print() {
        this.traverse(node -> System.out.print(node + ", "));
        System.out.println();
    }

    /**
     * Insert the data into the correct position in a sorted list. Assume
     * that the list is sorted in ascending order.
     */
    public void insertSorted(T data) {
        if (this.isEmpty()) {
            addToHead(data);
            return;
        }
        // 1
        // 1 4 or -1 1

        // 3 5 7
        // 3 5 6 7 or -1 1
        Node<T> prev = null, curr;
        //find the position where to insert
        for (curr = head; curr != null; prev = curr, curr = curr.getNext()) {
            if (curr.getData().compareTo(data) > 0) { // insert at first position where data is less than current node.
                break;
            }
        }
        if (prev == null) {
            addToHead(data);
        } else {
            prev.setNext(new Node<>(data, curr));
        }
        size++;
    }

    public Node<T> findPrev(T data) {
        if (this.isEmpty() || size == 1)
            return null;
        for (Node<T> prev = head, curr = head.getNext(); curr != null; prev = curr, curr = curr.getNext())
            if (curr.getData().compareTo(data) == 0)
                return prev;
        return null;
    }

    public Node<T> find(T data) {
        if (this.isEmpty())
            return null;
        for (Node<T> curr = head; curr != null; curr = curr.getNext())
            if (curr.getData().compareTo(data) == 0)
                return curr;
        return null;
    }

    /**
     * O(m)
     * Append the nodes of the other list to the end of the current list.
     */
    public void appendList(LinkedList<T> list) {
        list.traverse(node -> this.addAtTail(node.getData()));
    }

    /**
     * Split a linked list into 2 equal parts. If there are an odd number of
     * elements, the extra element should be in the first list.
     */
    public List<LinkedList<T>> frontBackSplit() {
        // We can use size to know the half or use one fast and one slow pointer to know the half
        if (head == null)
            return Collections.emptyList();

        Node<T> slowPtr = head, fastPtr = head;
        // 1 2 3 4 5 6 7 8 9
        // 1 2 3 4 '5' 6 7 8 9 10
        // 1   3   5   7   9
        while (fastPtr.next != null && fastPtr.next.next != null) {
            slowPtr = slowPtr.next;
            fastPtr = fastPtr.next.next;
        }
        // create two new lists, one ending at slowPtr and one starting after faster Ptr
        LinkedList<T> first = new LinkedList<>();
        LinkedList<T> second = new LinkedList<>();

        Node<T> tmp = head;
        while (tmp != slowPtr.getNext()) {
            first.add(tmp.getData());
            tmp = tmp.getNext();
        }
        while (tmp != null) {
            second.add(tmp.getData());
            tmp = tmp.getNext();
        }
        return Arrays.asList(first, second);
    }

    /**
     * Remove duplicates in a list.
     */
    public void removeDuplicates() {
        if (head == null || head.getNext() == null)
            return;
        HashSet<T> hashSet = new HashSet();
        hashSet.add(head.getData());

        Node<T> prev = head, curr = head.getNext();
        while (curr != null) {
            boolean isAbsent = hashSet.add(curr.getData());
            if (!isAbsent) { //element was present
                prev.setNext(curr.getNext());
                curr = curr.getNext();
                size--;
                continue;
            }
            prev = curr;
            curr = curr.getNext();
            //1 2 3 3 5
        }

    }

    public T getHead() {
        if (head == null)
            return null;
        return head.getData();
    }

    public T first() {
        return getHead();
    }

    /**
     * Move the head element or the first element from this list to
     * the destination linked list as the destination's new head node.
     */
    public void changeHead(LinkedList<T> destinationList) {
        if (head == null)
            return;
        Node<T> tmp = head;
        head = head.getNext();
        size--;
        tmp.setNext(destinationList.head);
        destinationList.head = tmp;
        destinationList.size++;
    }

    /**
     * Create a new sorted list which is the merged from two original sorted lists.
     * Assume the lists are sorted in ascending order.
     */
    public LinkedList<T> sortedMergeList(LinkedList<T> otherList) {

        Node<T> firstPtr = head, secondPtr = otherList.head;
        LinkedList<T> newList = new LinkedList<>();
        while (firstPtr != null && secondPtr != null) {
            int compareTo = firstPtr.getData().compareTo(secondPtr.getData());
            if (compareTo > 0) { //first data is greater so add the seconds data in new list
                newList.addAtTail(secondPtr.getData());
                secondPtr = secondPtr.getNext();
            } else if (compareTo < 0) { //2nd data is greater so add the first data in new list
                newList.addAtTail(firstPtr.getData());
                firstPtr = firstPtr.getNext();
            } else {
                newList.addAtTail(firstPtr.getData());
                // since both have same element move both forward
                firstPtr = firstPtr.getNext();
                secondPtr = secondPtr.getNext();
            }
        }
        while (firstPtr != null) {

            newList.addAtTail(firstPtr.getData());
            firstPtr = firstPtr.getNext();

        }

        while (secondPtr != null) {
            newList.addAtTail(secondPtr.getData());
            secondPtr = secondPtr.getNext();

        }
        return newList;

    }

    public void truncate() {
        head = null;
        tail = null;
        size = 0;
    }

    public void reverseList() {
        if (head == null || head.getNext() == null)
            return;


        Node<T> prev = head, curr = head.getNext(), next;
        prev.setNext(null);
        while (curr != null) {
            //Before prev -> curr -> next

            next = curr.getNext();
            curr.setNext(prev);
            //After prev <- curr - > next

            prev = curr;
            curr = next;
        }
        tail = head;
        head = prev;

    }

    public void recursiveReverseList() {
        if (head == null || head.getNext() == null)
            return;
        tail = head;
        Node<T> next = head.getNext();
        head.setNext(null);
        recursiveReverse(head, next);
    }

    private void recursiveReverse(Node<T> prev, Node<T> curr) {
        if (curr == null) {
            head = prev;
            return;
        }
        Node<T> next = curr.getNext();
        curr.setNext(prev);
        recursiveReverse(curr, next);

    }

    /**
     * Using merge sort, sort the linked list
     */
    public void mergeSort() {
        //need to update head and tail
        head = mergeSort(head);
        this.traverse(tNode -> this.tail = tNode);
    }

    private Node<T> mergeSort(Node<T> head) {
        if (head == null || head.getNext() == null)
            return head;
        // Step 1. Divide in 2 parts
        List<Node<T>> divide = this.splitInPlace(head);
        if (divide == null)
            return head;

        Node<T> first = divide.get(0);
        Node<T> second = divide.get(1);

        // Step 2. Solve/Conquer the parts

        mergeSort(first);
        mergeSort(second);

        // Step 3. Join the solved parts
        return sortedMerge(first, second);
    }

    private Node<T> sortedMerge(Node<T> first, Node<T> second) {
        if (first == null)
            return second;
        else if (second == null)
            return first;

        Node<T> result;
        /* Pick either a or b, and recur */
        int compareTo = first.getData().compareTo(second.getData());
        if (compareTo >= 0) {
            //1->4->5
            //0->2
            result = second;
            result.setNext(sortedMerge(first, second.getNext()));
        } else {
            result = first;
            result.setNext(sortedMerge(first.getNext(), second));
        }

        return result;
    }

    /**
     * @param head
     * @return Empty lists if the lists cannot be divide further, else current list divided into two parts.
     */
    private List<Node<T>> splitInPlace(Node<T> head) {

        if (head == null || head.getNext() == null)
            return null;

        // We will use two pointers two find middle and split
        Node<T> fastPtr = head.getNext(), slowPtr = head;

        // 1 2 3 4 5 6 7 8 9
        // 1 2 3 4 5
        // 2 4 6 8 null

        // 1 2
        // 2 null
        while (fastPtr != null && fastPtr.next != null) {
            slowPtr = slowPtr.getNext();
            fastPtr = fastPtr.getNext().getNext();
        }
        Node<T> next = slowPtr.getNext();
        slowPtr.setNext(null);
        return Arrays.asList(this.head, next);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        this.traverse(tNode -> builder.append(tNode).append(", "));

        return builder.toString();
    }

    /**
     * O(n)
     *
     * @param data
     */
    public void addAtTail(T data) {
        Node<T> toAdd = new Node<>(data);
        size++;
        if (head == null) {
            tail = head = toAdd;
            return;
        }

        Node<T> temp = head;
        while (temp.getNext() != null)
            temp = temp.getNext();
        temp.setNext(toAdd);
        tail = toAdd;
    }

    /**
     * O(1)
     *
     * @param data
     */
    public void add(T data) {
        Node<T> toAdd = new Node<>(data);
        if (tail == null) {
            head = tail = toAdd;
            size++;
            return;
        }
        tail.setNext(toAdd);
        tail = toAdd;
        size++;
    }

    /**
     * O(1)
     *
     * @param data
     */
    public void addToHead(T data) {
        head = new Node<>(data, head);
        if (tail == null) tail = head;
        size++;
    }

    /**
     * O(n)
     */
    public void remove(T data) {
        if (head == null)
            return;

        else if (head.getData().compareTo(data) == 0) {
            head = head.getNext();
            size--;
            if (this.isEmpty())
                tail = null;
            return;
        }
        for (Node<T> curr = head.getNext(), prev = head; curr != null; prev = curr, curr = curr.getNext()) {
            if (curr.getData().compareTo(data) == 0) {
                if (curr == tail) {
                    tail = prev;
                }
                prev.setNext(curr.getNext());
                size--;
                return;
            }
        }
    }

    /**
     * O(n)
     */
    public void remove(int idx) {
        if (head == null || idx >= size || idx < 0)
            return;
        else if (idx == 0) {
            head = head.getNext();
            size--;
        }
        Node<T> curr = head.getNext(), prev = head;
        for (int i = 0; curr != null; i++) {
            if (i == idx) {
                prev.setNext(curr.getNext());
                size--;
                break;
            }
            prev = curr;
            curr = curr.getNext();
        }
    }

    /**
     * O(1)
     *
     * @return
     */
    public T pop() {
        if (tail == null) // No elements
            return null;
        else {
            T data = tail.getData();
            if (head.getNext() == null) {
                head = tail = null;
            } else {
                Node<T> tmp = head;
                while (tmp.getNext().getNext() != null) {
                    tmp = tmp.getNext();
                }
                tail = tmp;
                tmp.setNext(null);
            }
            size--;
            return data;
        }
    }

    /**
     * O(n)
     *
     * @param forEach
     */
    public void traverse(Consumer<Node<T>> forEach) {
        for (Node<T> node = head; node != null; node = node.getNext())
            forEach.accept(node);
    }

    /**
     * O(1)
     *
     * @return
     */
    public int size() {
        return size;
    }

    /**
     * O(n)
     *
     * @return
     */
    public int count() {
        final int[] count = new int[1];
        this.traverse(data -> count[0]++);
        return count[0];
    }

    /**
     * O(1)
     *
     * @return
     */
    public boolean isEmpty() {
        return size == 0;
    }

    class Node<T extends Comparable<T>> {
        private T data;
        private Node<T> next;

        public Node(T data, Node<T> next) {
            this.data = data;
            this.next = next;
        }

        public Node(T data) {
            this.data = data;
        }

        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }

        public Node<T> getNext() {
            return next;
        }

        public void setNext(Node<T> next) {
            this.next = next;
        }

        public String toString() {
            return data.toString();
        }

    }

}