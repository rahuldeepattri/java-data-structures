package com.rd.graph.tree;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class MorrisTraversalTest {

    MorrisTraversal morrisTraversal = new MorrisTraversal();

    @Test
    public void preOrderTraversal() {
        BinaryTree<Integer> bt = new BinaryTree<>();

        bt.add(1);
        bt.add(2);
        bt.add(3);
        bt.add(4);
        bt.add(5);

        List<Integer> list = new ArrayList<>();
        morrisTraversal.preOrder(bt.root, list::add);

        assertEquals(Arrays.asList(1, 2, 4, 5, 3), list);
    }


    @Test
    public void inOrderTraversal() {
        BinaryTree<Integer> bt = new BinaryTree<>();

        bt.add(1);
        bt.add(2);
        bt.add(3);
        bt.add(4);
        bt.add(5);

        List<Integer> list = new ArrayList<>();
        morrisTraversal.inOrder(bt.root, list::add);

        assertEquals(Arrays.asList(4, 2, 5, 1, 3), list);
    }


}