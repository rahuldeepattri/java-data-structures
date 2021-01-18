package com.rahul.linkedlist;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class LinkedListTest {
    LinkedList<Integer> list;

    @Before
    public void doBefore() {
        list = new LinkedList<>();
    }

    @After
    public void doAfter() {
        list = null;
    }

    @Test
    public void basic() {
        LinkedList<Integer> list = new LinkedList<>();
        assertNull(list.peekHead());

        assertEquals(0, list.count());
        list.append(12);
        assertEquals(12, list.peekHead().intValue());
        assertEquals(1, list.count());

        list.append(113);
        assertEquals(12, list.peekHead().intValue());
        assertEquals(2, list.count());

        list.insert(2, 4);
        assertEquals(4, list.get(2).intValue());

        list.insert(0, 0);
        assertEquals(0, list.get(0).intValue());

        list.insert(1, 1);
        assertEquals(1, list.get(1).intValue());

        list.insert(3, 4);
        assertEquals(4, list.get(3).intValue());


    }

    @Test
    public void testPushPoppeekTail() {
        LinkedList<Integer> list = new LinkedList<>();
        list.push(1);
        list.print();
        Assert.assertEquals(1, list.pop().intValue());

        list.push(2);
        list.push(3);
        list.print();
        Assert.assertEquals(3, list.pop().intValue());


        list.push(4);
        list.print();
        Assert.assertEquals(4, list.peekTail().intValue());


        Assert.assertEquals(4, list.pop().intValue());
        Assert.assertEquals(2, list.pop().intValue());

        list.print();
        Assert.assertNull(list.peekTail());
        Assert.assertNull(list.pop());


    }

    @Test
    public void testInsertSorted() {
        LinkedList<Integer> l = new LinkedList<>();
        l.insertSorted(2);
        assertEquals(2, l.get(0).intValue());

        l.insertSorted(5);
        assertEquals(5, l.get(1).intValue());

        l.insertSorted(1);
        assertEquals(1, l.get(0).intValue());

        l.insertSorted(4);
        assertEquals(4, l.get(2).intValue());

        l.insertSorted(3);
        assertEquals(3, l.get(2).intValue());

    }

    @Test
    public void appendList() {
        LinkedList<Integer> a = new LinkedList<Integer>();
        a.append(1);
        LinkedList<Integer> b = new LinkedList<Integer>();
        b.append(2);
        b.append(3);

        a.appendList(b);
        assertEquals(1, a.get(0).intValue());
        assertEquals(2, a.get(1).intValue());
        assertEquals(3, a.get(2).intValue());

        a = new LinkedList<Integer>();
        a.append(1);
        a.append(2);
        b = new LinkedList<Integer>();
        b.append(3);
        b.append(4);

        a.appendList(b);
        assertEquals(3, a.get(2).intValue());
        assertEquals(4, a.get(3).intValue());
    }

    /**
     * Split a linked list into 2 equal parts. If there are an odd number of
     * elements, the extra element should be in the first list.
     */
    @Test
    public void frontBackSplit() {
        LinkedList<Integer> a = new LinkedList<Integer>();
        a.append(1);
        assertEquals(1, a.frontBackSplit().get(0).get(0).intValue());


        a.append(2);
        assertEquals(1, a.frontBackSplit().get(0).get(0).intValue());
        assertEquals(2, a.frontBackSplit().get(1).get(0).intValue());

        a.append(3);
        assertEquals(1, a.frontBackSplit().get(0).get(0).intValue());
        assertEquals(3, a.frontBackSplit().get(1).get(0).intValue());


        a.append(4);
        assertEquals(1, a.frontBackSplit().get(0).get(0).intValue());
        assertEquals(3, a.frontBackSplit().get(1).get(0).intValue());

        a.append(5);
        assertEquals(1, a.frontBackSplit().get(0).get(0).intValue());
        assertEquals(4, a.frontBackSplit().get(1).get(0).intValue());
    }

    @Test
    public void removeDuplicates() {


        list.push(1);
        list.removeDuplicates();
        assertEquals(1, list.get(0).intValue());

        list.push(2);
        list.push(2);
        list.push(2);
        list.push(3);
        list.push(3);
        list.push(4);
        list.removeDuplicates();
        assertEquals(1, list.get(0).intValue());
        assertEquals(2, list.get(1).intValue());
        assertEquals(3, list.get(2).intValue());
        assertEquals(4, list.get(3).intValue());


        list.push(5);
        list.push(5);
        list.push(6);
        list.push(7);
        list.push(7);

        list.removeDuplicates();
        assertEquals(5, list.get(4).intValue());
        assertEquals(5, list.get(4).intValue());
        assertEquals(7, list.get(6).intValue());
    }

    @Test(expected = NullPointerException.class)
    public void removeDuplicatesException() {
        list.push(1);
        list.push(1);
        list.removeDuplicates();
        assertEquals(1, list.get(0).intValue());
        assertEquals(1, list.get(1).intValue());

    }

    @Test(expected = NullPointerException.class)
    public void removeDuplicatesExceptionTest2() {
        list.push(1);
        list.push(1);
        list.push(1);
        list.removeDuplicates();
        assertEquals(1, list.get(0).intValue());
        assertEquals(1, list.get(1).intValue());

    }

    @Test()
    public void removeDuplicatesExceptionTest3() {
        list.removeDuplicates();
        assertNull(list.get(0));

    }


    /**
     * Move the head element or the first element from this list to
     * the destination linked list as the destination's new head node.
     */
    @Test
    public void changeHead() {
        LinkedList<Integer> destinationList = new LinkedList<>();
        list.changeHead(destinationList);
        assertNull(list.getHead());
        assertNull(destinationList.getHead());

        list.push(1);
        list.changeHead(destinationList);
        assertNull(list.getHead());
        assertEquals(1, destinationList.get(0).intValue());

        destinationList = new LinkedList<>();
        list = new LinkedList<>();
        destinationList.push(1);
        list.changeHead(destinationList);
        assertNull(list.getHead());
        assertEquals(1, destinationList.get(0).intValue());


        destinationList = new LinkedList<>();
        list = new LinkedList<>();
        list.push(10);
        destinationList.push(20);
        destinationList.push(30);
        list.changeHead(destinationList);
        assertNull(list.getHead());
        assertEquals(10, destinationList.get(0).intValue());
        assertEquals(20, destinationList.get(1).intValue());

        destinationList = new LinkedList<>();
        list = new LinkedList<>();
        list.push(1);
        list.push(2);
        destinationList.push(20);
        destinationList.push(30);
        list.changeHead(destinationList);
        assertEquals(2, list.get(0).intValue());
        assertEquals(1, destinationList.get(0).intValue());

    }

    @Test
    public void merge() {
        LinkedList<Integer> otherList = new LinkedList<>();

        LinkedList<Integer> result = list.sortedMergeList(otherList);
        assertNull(result.getHead());

        list.append(1);
        result = list.sortedMergeList(otherList);
        assertNull(otherList.getHead());
        assertEquals(1, result.getHead().intValue());

        list.truncate();
        otherList.append(1);
        result = list.sortedMergeList(otherList);
        assertNull(list.getHead());
        assertEquals(1, result.getHead().intValue());

        list.truncate();
        otherList.truncate();

        list.push(1);
        list.push(3);
        list.push(5);
        list.push(6);
        otherList.push(0);
        otherList.push(2);
        otherList.push(4);
        result = list.sortedMergeList(otherList);
        assertEquals(0, result.get(0).intValue());
        assertEquals(1, result.get(1).intValue());
        assertEquals(2, result.get(2).intValue());
        assertEquals(3, result.get(3).intValue());
        assertEquals(4, result.get(4).intValue());
        assertEquals(5, result.get(5).intValue());
        assertEquals(6, result.get(6).intValue());

    }

    @Test
    public void reverseList() {
        list.reverseList();
        assertNull(list.getHead());

        list.push(1);
        list.reverseList();
        assertEquals(1, list.get(0).intValue());

        list.push(2);
        list.reverseList();
        assertEquals(2, list.get(0).intValue());
        assertEquals(1, list.get(1).intValue());

        list.truncate();
        list.push(1);
        list.push(2);
        list.push(3);
        list.reverseList();
        assertEquals(1, list.get(2).intValue());
        assertEquals(2, list.get(1).intValue());
        assertEquals(3, list.get(0).intValue());


    }

    @Test
    public void recursiveReverseList() {
        list.recursiveReverseList();
        assertNull(list.getHead());

        list.push(1);
        list.recursiveReverseList();
        assertEquals(1, list.get(0).intValue());

        list.push(2);
        list.recursiveReverseList();
        assertEquals(2, list.get(0).intValue());
        assertEquals(1, list.get(1).intValue());

        list.truncate();
        list.push(1);
        list.push(2);
        list.push(3);
        list.recursiveReverseList();
        assertEquals(1, list.get(2).intValue());
        assertEquals(2, list.get(1).intValue());
        assertEquals(3, list.get(0).intValue());


    }

    @Test
    public void mergeSortLinkedList() {
        list.append(1);
        list.append(5);
        list.append(3);
        list.append(2);
        list.append(6);
        list.append(4);
        list.mergeSort();
        assertEquals(1, list.get(0).intValue());
        assertEquals(2, list.get(1).intValue());
        assertEquals(3, list.get(2).intValue());
        assertEquals(4, list.get(3).intValue());
        assertEquals(5, list.get(4).intValue());
        assertEquals(6, list.get(5).intValue());

        list = new LinkedList<>();
        list.append(4);
        list.append(1);
        list.mergeSort();
        assertEquals(1, list.get(0).intValue());
        assertEquals(4, list.get(1).intValue());
    }
}

