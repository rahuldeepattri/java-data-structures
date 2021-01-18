package com.rd.graph.tree;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class BinaryTreeTest {

    @Test
    public void preOrderTraversal() {
        BinaryTree<Integer> bt = new BinaryTree<>();

        bt.add(1);
        bt.add(2);
        bt.add(3);

        List<Integer> list = new ArrayList<>();
        bt.preOrderTraversal(list::add);

        assertEquals(Arrays.asList(1, 2, 3), list);
    }


    @Test
    public void postOrderTraversal() {
        BinaryTree<Integer> bt = new BinaryTree<>();

        bt.add(1);
        bt.add(2);
        bt.add(3);
        List<Integer> list = new ArrayList<>();
        bt.postOrderTraversal(list::add);

        assertEquals(Arrays.asList(2, 3, 1), list);
    }


    @Test
    public void inOrderTraversal() {
        BinaryTree<Integer> bt = new BinaryTree<>();

        bt.add(1);
        bt.add(2);
        bt.add(3);

        List<Integer> list = new ArrayList<>();
        bt.inOrderTraversal(list::add);

        assertEquals(Arrays.asList(2, 1, 3), list);
    }


    @Test
    public void breadthFirstTraversal() {
        BinaryTree<Integer> bt = new BinaryTree<>();

        bt.add(1);
        bt.add(2);
        bt.add(3);

        List<Integer> list = new ArrayList<>();
        bt.breadthFirstTraversal(list::add);

        assertEquals(Arrays.asList(1, 2, 3), list);
    }

    @Test
    public void breadthFirstTraversal_null_test() {
        BinaryTree<Integer> bt = new BinaryTree<>();

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


    @Test
    public void depth_test() {
        BinaryTree<Integer> bt = new BinaryTree<>();

        assertEquals(0, bt.depth());

        bt.add(1);
        assertEquals(1, bt.depth());

        bt.add(2);
        assertEquals(2, bt.depth());

        bt.add(3);
        assertEquals(2, bt.depth());

        bt.add(5);
        assertEquals(3, bt.depth());

        bt.add(6);
        assertEquals(3, bt.depth());


    }


    @Test
    public void mirror_test() {
        BinaryTree<Integer> bt = new BinaryTree<>();

        bt.add(1);
        bt.add(2);
        bt.add(3);
        bt.add(4);
        bt.add(5);
        bt.add(6);

        List<Integer> list = new ArrayList<>();
        bt.breadthFirstTraversal(list::add);

        BinaryTree<Integer> mirror = bt.mirror();
        List<Integer> mirrorList = new ArrayList<>();
        mirror.breadthFirstTraversal(mirrorList::add);

        System.out.println(list);
        System.out.println(mirrorList);
        assertNotEquals(list, mirrorList);
    }

    @Test
    public void testEquals() {

        BinaryTree<Integer> integerBinaryTree = new BinaryTree<>();

        integerBinaryTree.add(1);
        integerBinaryTree.add(2);
        integerBinaryTree.add(3);
        integerBinaryTree.add(4);
        integerBinaryTree.add(5);
        integerBinaryTree.add(6);


        BinaryTree<String> stringBinaryTree = new BinaryTree<>();

        stringBinaryTree.add("1");
        stringBinaryTree.add("2");
        stringBinaryTree.add("3");
        stringBinaryTree.add("4");
        stringBinaryTree.add("5");
        stringBinaryTree.add("6");

        assertNotEquals(integerBinaryTree,stringBinaryTree);

    }
}
