package com.rd.graph.tree;

import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.*;

public class BinaryTreeUtilsTest {

    @Test
    public void rootToLeafPath() {
        BinaryTree<Integer> bt = new BinaryTree<>();

        assertFalse(BinaryTreeUtils.rootToLeafPath(bt, 0));
        assertFalse(BinaryTreeUtils.rootToLeafPath(bt, -1));
        bt.add(1);
        assertTrue(BinaryTreeUtils.rootToLeafPath(bt, 1));
        assertFalse(BinaryTreeUtils.rootToLeafPath(bt, -1));
        assertFalse(BinaryTreeUtils.rootToLeafPath(bt, 3));
        bt.add(2);
        assertTrue(BinaryTreeUtils.rootToLeafPath(bt, 3));
        assertFalse(BinaryTreeUtils.rootToLeafPath(bt, 2));
        bt.add(3);
        assertTrue(BinaryTreeUtils.rootToLeafPath(bt, 4));
        assertTrue(BinaryTreeUtils.rootToLeafPath(bt, 3));
        bt.add(4);
        assertTrue(BinaryTreeUtils.rootToLeafPath(bt, 7));
        bt.add(5);
        assertTrue(BinaryTreeUtils.rootToLeafPath(bt, 8));
        bt.add(6);
        assertTrue(BinaryTreeUtils.rootToLeafPath(bt, 10));

    }

    @Test
    public void printPath() {
        BinaryTree<Integer> bt = new BinaryTree<>();

        bt.add(1);
        bt.add(2);
        bt.add(3);
        bt.add(4);
        bt.add(5);
        bt.add(6);
        bt.add(7);
        bt.add(8);
        bt.add(9);
        bt.add(10);

        BinaryTreeUtils.printPaths(bt, System.out);

    }

    @Test
    public void leastCommonAncestor() {
        BinaryTree<Integer> bt = new BinaryTree<>();

        bt.add(1);
        bt.add(2);
        bt.add(3);
        bt.add(4);
        bt.add(5);
        bt.add(6);
  /*
              1
          2		3
        4   5    6  null
 */
        Optional<Node<Integer>> optional;


        optional = BinaryTreeUtils.leastCommonAncestor(bt, 4, 5);
        assertTrue(optional.isPresent());
        System.out.println(optional);

        optional = BinaryTreeUtils.leastCommonAncestor(bt, 6, 5);
        assertTrue(optional.isPresent());
        System.out.println(optional);

        optional = BinaryTreeUtils.leastCommonAncestor(bt, 7, 5);
        assertFalse(optional.isPresent());
        System.out.println(optional);

    }

    @Test
    public void copy() {
        BinaryTree<Integer> bt = new BinaryTree<>();

        bt.add(1);
        bt.add(2);
        bt.add(3);
        bt.add(4);
        bt.add(5);
        bt.add(6);

        BinaryTree<Integer> copy = new BinaryTree<>();

        BinaryTreeUtils.copy(bt, copy);

        assertEquals(bt, copy);

    }

    @Test
    public void doubleTree() {
        BinarySearchTree<Integer> bt = new BinarySearchTree<>();

        bt.add(6);
        bt.add(3);
        bt.add(1);
        bt.add(4);
        bt.add(5);
        bt.add(2);

        BinarySearchTree<Integer> doubleTree = BinaryTreeUtils.doubleTree(bt);

        assertNotEquals(bt, doubleTree);
        TreePrinter.print(bt.root);
        TreePrinter.print(doubleTree.root);
    }

    @Test
    public void countStructurallyUniqueTrees_slow() {

        System.out.println(BinaryTreeUtils.countStructurallyUniqueTrees(18));
    }


    @Test
    public void countStructurallyUniqueTreesDP_optimized() {

        System.out.println(BinaryTreeUtils.countStructurallyUniqueTreesDP(18));
    }

    @Test
    public void countStructurallyUniqueTrees() {
        assertEquals(
                BinaryTreeUtils.countStructurallyUniqueTrees(18),
                BinaryTreeUtils.countStructurallyUniqueTreesDP(18),
                0.001d
        );

        // TODO why this is very slow as compared to calling this method individually?
    }


}