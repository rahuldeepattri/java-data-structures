package com.rd.graph.tree;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class BinarySearchTreeTest {

    @Test
    public void preOrderTraversal() {
        BinaryTree<Integer> bt = new BinarySearchTree<>();

        bt.add(1);
        bt.add(2);
        bt.add(3);

        List<Integer> list = new ArrayList<>();
        bt.preOrderTraversal(list::add);

        assertEquals(Arrays.asList(1, 2, 3), list);
    }


    @Test
    public void postOrderTraversal() {
        BinaryTree<Integer> bt = new BinarySearchTree<>();

        bt.add(1);
        bt.add(2);
        bt.add(3);
        List<Integer> list = new ArrayList<>();
        bt.postOrderTraversal(list::add);

        assertEquals(Arrays.asList(3, 2, 1), list);
    }


    @Test
    public void inOrderTraversal() {
        BinaryTree<Integer> bt = new BinarySearchTree<>();

        bt.add(1);
        bt.add(2);
        bt.add(3);

        List<Integer> list = new ArrayList<>();
        bt.inOrderTraversal(list::add);

        assertEquals(Arrays.asList(1, 2, 3), list);
    }


    @Test
    public void breadthFirstTraversal() {
        BinaryTree<Integer> bt = new BinarySearchTree<>();

        bt.add(1);
        bt.add(2);
        bt.add(3);

        List<Integer> list = new ArrayList<>();
        bt.breadthFirstTraversal(list::add);

        assertEquals(Arrays.asList(1, 2, 3), list);
    }

    @Test(expected = NullPointerException.class)
    public void breadthFirstTraversal_null_test() {
        BinaryTree<Integer> bt = new BinarySearchTree<>();

        bt.add(1);
        bt.add(2);
        bt.add(3);
        bt.add(null);
        bt.add(5);
        bt.add(6);

        List<Integer> list = new ArrayList<>();
        bt.breadthFirstTraversal(list::add);
        System.out.println(list);
        assertEquals(Arrays.asList(1, 2, 3, null, 5, 6), list);
    }

    @Test()
    public void inOrderTraversal_bst_check() {
        BinaryTree<Integer> bt = new BinarySearchTree<>();

        bt.add(6);
        bt.add(2);
        bt.add(1);
        bt.add(4);
        bt.add(3);
        bt.add(5);

        List<Integer> list = new ArrayList<>();
        bt.inOrderTraversal(list::add);
        System.out.println(list);
        assertEquals(Arrays.asList(1, 2, 3, 4, 5, 6), list);
    }

    @Test()
    public void test_lookup() {
        BinarySearchTree<Integer> bt = new BinarySearchTree<>();

        bt.add(1);
        bt.add(2);
        bt.add(3);
        bt.add(4);
        bt.add(5);
        bt.add(6);

        assertTrue(bt.lookup(4));
        assertFalse(bt.lookup(-1));
    }


    @Test()
    public void test_min_max() {
        BinarySearchTree<Integer> bt = new BinarySearchTree<>();


        bt.add(6);
        bt.add(2);
        bt.add(1);
        bt.add(4);
        bt.add(3);
        bt.add(5);

        assertEquals((Integer) 1, bt.getMin().get());
        assertEquals((Integer) 6, bt.getMax().get());

    }

    @Test()
    public void findInRangeClosed() {
        BinarySearchTree<Integer> bt = new BinarySearchTree<>();


        bt.add(6);
        bt.add(2);
        bt.add(1);
        bt.add(4);
        bt.add(3);
        bt.add(5);

        assertEquals(Arrays.asList(1, 2, 3), bt.findInRangeClosed(1, 3));
        assertEquals(Arrays.asList(1, 2, 3, 4, 5, 6), bt.findInRangeClosed(1, 6));
        assertEquals(Arrays.asList(1, 2, 3, 4, 5, 6), bt.findInRangeClosed(-1, 7));
        assertEquals(Arrays.asList(1, 2, 3, 4, 5, 6), bt.findInRangeClosed(1, 6));
        assertEquals(Arrays.asList(1), bt.findInRangeClosed(1, 1));
        assertEquals(Arrays.asList(), bt.findInRangeClosed(-11, -1));


    }

    @Test()
    public void findInRangeClosedOptimized() {
        BinarySearchTree<Integer> bt = new BinarySearchTree<>();


        bt.add(6);
        bt.add(2);
        bt.add(1);
        bt.add(4);
        bt.add(3);
        bt.add(5);

        assertEquals(Arrays.asList(1, 2, 3), bt.findInRangeClosedOptimized(bt.root, 1, 3));
        assertEquals(Arrays.asList(1, 2, 3, 4, 5, 6), bt.findInRangeClosedOptimized(bt.root, 1, 6));
        assertEquals(Arrays.asList(1, 2, 3, 4, 5, 6), bt.findInRangeClosedOptimized(bt.root, -1, 7));
        assertEquals(Arrays.asList(1, 2, 3, 4, 5, 6), bt.findInRangeClosedOptimized(bt.root, 1, 6));
        assertEquals(Arrays.asList(1), bt.findInRangeClosedOptimized(bt.root, 1, 1));
        assertEquals(Arrays.asList(), bt.findInRangeClosedOptimized(bt.root, -11, -1));


    }

}