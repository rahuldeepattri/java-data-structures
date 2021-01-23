package com.rd.linkedlist;

import java.util.Arrays;
import java.util.List;

/**
 * Complexity
 * Add/Delete at the beginning = O(1)
 * Add/Delete at the end = O(N)
 * Finding an element = O(N)
 * *
 *
 * @param <T>
 */
public class LinkedList<T extends Comparable<T>> {
    public int length; //Gives the length of list in constant time
    private Node<T> head;

    public LinkedList() {
    }

    public Node<T> getHead() {
        return head;
    }

    public int count() {
        if (head == null)
            return 0;
        int count = 0;
        Node<T> curr = head;
        while (curr != null) {
            curr = curr.getNext();
            count++;
        }
        return count;
    }

    /**
     * Print all the nodes in the linked list.
     */
    public void print() {
        StringBuilder builder = new StringBuilder();
        builder.append("[\n");
        int i = 0;
        Node<T> curr = head;
        while (curr != null) {
            builder.append(curr.getData());
            builder.append(" is at index: ");
            builder.append(i);
            builder.append("\n");
            i++;
            curr = curr.getNext();
        }
        builder.append("]");
        System.out.println(builder);
    }

    /**
     * Append a new node to the end of the linked list.
     */
    public void append(T data) {
        push(data);
    }

    public T peek() {
        if (head == null)
            return null;
        Node<T> curr;
        curr = head;
        while (curr.getNext() != null) {
            curr = curr.getNext();
        }
        return curr.getData();
    }

    public T pop() {
        if (head == null)
            return null;
        Node<T> curr, newLast;
        newLast = curr = head;
        while (curr.getNext() != null) {
            newLast = curr;
            curr = curr.getNext();
        }

        length--;
        //TODO To ask
        T data = curr.getData();
        if (curr == head) {
            truncate();
            return data;
        }
        newLast.setNext(null);

        return data;
    }


    /**
     * Insert data at the end.
     *
     * @param data
     */
    public void push(T data) {
        length++;
        if (head == null) {
            head = new Node<>(data);

            return;
        }
        Node<T> curr;
        curr = head;
        while (curr.getNext() != null) {
            curr = curr.getNext();
        }
        curr.setNext(new Node<T>(data));
    }

    /**
     * Delete all the elements in the linked list.
     */
    public void truncate() {
        // The objects in heap will be garbage collected even if they have references stored
        // If an object is non reachable from stack frame it will be garbage collected.
        // Java's garbage collector will clean up the memory
        // for all the elements being used in this linked list if
        // they are no longer referenced in the program.
        head = null;
        length = 0;
    }

    public T peekHead() {
        return head == null ? null : head.getData();
    }

    public void insertAtHead(T data) {
        Node<T> node = new Node<>(data);
        node.setNext(head);
        head = node;
        length++;
    }

    /**
     * Insert the data into the correct position in a sorted list. Assume
     * that the list is sorted in ascending order.
     */
    public void insertSorted(T data) {
        if (head == null || head.getData().compareTo(data) > 0) {
            insertAtHead(data);
            return;
        }
        Node<T> curr = head;
        //head is already tested
        while (curr.getNext() != null && curr.getNext().getData().compareTo(data) < 0) {
            curr = curr.getNext();
        }
        insertAfter(curr, data);

    }

    public void insertAfter(Node<T> node, T data) {
        Node<T> next = node.getNext();
        node.setNext(new Node<>(data));
        node.getNext().setNext(next);
        length++;
    }

    public void insertAfter(Node<T> node, Node<T> newNode) {
        Node<T> next = node.getNext();
        node.setNext(newNode);
        node.getNext().setNext(next);
        length++;
    }

    public T get(int index) {
        if (index >= length)
            throw new IllegalArgumentException("index should be less than length");
        if (head == null)
            return null;
        int i = 0;
        Node<T> curr = head;
        while (i != index) {
            i++;
            curr = curr.getNext();
        }
        return curr.getData();
    }

    /**
     * Insert at the nth index in the list. Return if the number of nodes is
     * less than n.
     */
    public void insert(int index, T data) {
        if (index > length)
            throw new IllegalArgumentException("index should be less than or equal length");
        if (head == null || index == length) {
            push(data);
            return;
        }
        if (index == 0) {
            insertAtHead(data);
            return;
        }

        // Move the curr node to one before the index where we
        // want to insert the element and adjust the pointers accordingly.
        int i = 0;
        Node<T> currAtIdx = head;
        while (i != index - 1) {
            currAtIdx = currAtIdx.getNext();
            i++;
        }
        Node<T> next = currAtIdx.getNext();
        currAtIdx.setNext(new Node<>(data));
        currAtIdx.getNext().setNext(next);
        length++;
    }

    /**
     * Append the nodes of the other list to the end of the curren list.
     */
    public void appendList(LinkedList<T> list) {

        Node<T> toAdd = list.getHead();
        while (toAdd != null) {
            push(toAdd.getData());//any change in old might reflect here also
            toAdd = toAdd.getNext();
        }
    }

    /**
     * Split a linked list into 2 equal parts. If there are an odd number of
     * elements, the extra element should be in the first list.
     */

    public List<Node<T>> frontBackSplit() {
        if (head == null)
            return null;
        Node<T> start = head, end = head;
        Boolean isLengthOdd = Boolean.FALSE;
        while (end.getNext() != null) {
            isLengthOdd = Boolean.FALSE;
            start = start.getNext();
            end = end.getNext();
            if (end.getNext() != null) {
                end = end.getNext();
                isLengthOdd = Boolean.TRUE;
            }
        }
        //1 2 3 4
        if (isLengthOdd)
            start = start.getNext();
        return Arrays.asList(head, start);
    }

    /**
     * Remove duplicates in a sorted list.
     */
    public void removeDuplicates() {
        if (head == null || head.getNext() == null)
            return;
        Node<T> curr = head.getNext(), prev = head;
        // 1 1 1 2 2 3 5 5
        //1 1
        while (curr != null) {
            if (prev.getData().equals(curr.getData())) {
                removeAfter(prev);
                curr = prev.getNext();
            } else {
                prev = curr;
                curr = curr.getNext();
            }

        }

    }

    /**
     * Remove one node after current node
     *
     * @param node
     */
    public void removeAfter(Node<T> node) {
        if (node == null || node.getNext() == null) return;
        node.setNext(node.getNext().getNext());
        length--;
    }

    /**
     * Move the head element or the first element from this list to
     * the destination linked list as the destination's new head node.
     */
    public void changeHead(LinkedList<T> destinationList) {
        //0 0, 1 0 , 0 1 , 1 2 10 20
        if (head == null)
            return;
        Node<T> hold = head;
        head = head.getNext();
        length--;
        hold.setNext(destinationList.getHead());
        destinationList.head = hold;
        destinationList.length++;
    }

    /**
     * Create a new sorted list which is the merged from two original sorted lists.
     * Assume the lists are sorted in ascending order.
     */
    public LinkedList<T> sortedMergeList(LinkedList<T> otherList) {
        if (otherList == null)
            throw new NullPointerException();
        Node<T> first = head, second = otherList.getHead();
        LinkedList<T> result = new LinkedList<>();

        while (first != null && second != null) {
            if (first.getData().compareTo(second.getData()) >= 1) {
                result.push(second.getData());
                second = second.getNext();
            } else if (first.getData().compareTo(second.getData()) < 1) {
                result.push(first.getData());
                first = first.getNext();
            }
        }

        while (first != null) {
            result.push(first.getData());
            first = first.getNext();

        }
        while (second != null) {
            result.push(second.getData());
            second = second.getNext();
        }

        return result;
    }

    /**
     * Reverse all the nodes in the linked list so that the last node
     * becomes the first node.
     */
    public void reverseList() {
        if (head == null || head.getNext() == null)
            return;

        Node<T> prev, next, curr = head;
        prev = head;
        curr = head.getNext();
        head.setNext(null);
        while (curr != null) {
            next = curr.getNext();
            curr.setNext(prev);
            prev = curr;
            curr = next;
        }
        head = prev;
    }

    public void recursiveReverseList() {
        if (head == null || head.getNext() == null)
            return;
        recursiveReverseListDriver(head, head.getNext());

    }

    private void recursiveReverseListDriver(Node<T> prev, Node<T> curr) {
        if (curr == null) {
            head = prev;
            return;
        }
        Node<T> next = curr.getNext();
        curr.setNext(prev);
        prev = curr;
        curr = next;

        recursiveReverseListDriver(prev, curr);

    }

    public void mergeSort() {
        if (head == null || head.getNext() == null)
            return;
        LinkedList<T> first = new LinkedList<>();
        LinkedList<T> second = new LinkedList<>();
        split(this, first, second);
        first.mergeSort();
        second.mergeSort();
        LinkedList<T> merged = sortedMerge(first, second);
        this.head = merged.head;

    }

    private LinkedList<T> sortedMerge(LinkedList<T> first, LinkedList<T> second) {

        LinkedList<T> list = new LinkedList<>();

        Node<T> firstPointer = first.head;
        Node<T> secondPointer = second.head;

        while (firstPointer != null && secondPointer != null) {
            if (firstPointer.getData().compareTo(secondPointer.getData()) < 0) {
                list.append(firstPointer.getData());
                firstPointer = firstPointer.getNext();
            } else {
                list.append(secondPointer.getData());
                secondPointer = secondPointer.getNext();
            }
        }
        while (firstPointer != null) {
            list.append(firstPointer.getData());
            firstPointer = firstPointer.getNext();
        }
        while (secondPointer != null) {
            list.append(secondPointer.getData());
            secondPointer = secondPointer.getNext();
        }
        return list;
    }

    private void split(LinkedList<T> list, LinkedList<T> first, LinkedList<T> second) {
        if (list.length < 2)
            return;
        Node<T> slow = list.getHead();
        Node<T> fast = list.getHead().getNext();
        //1 2
        //2
        while (fast != null && fast.getNext() != null) {
            slow = slow.getNext();
            fast = fast.getNext().getNext();
        }
        copyLinkedList(list, first, list.getHead(), slow.getNext());
        copyLinkedList(list, second, slow.getNext(), null);
    }

    private void copyLinkedList(LinkedList<T> src, LinkedList<T> dest, Node<T> start, Node<T> end) {
        Node<T> curr = start;
        while (curr != null && curr != end) {
            dest.append(curr.getData());
            curr = curr.getNext();
        }
    }
}