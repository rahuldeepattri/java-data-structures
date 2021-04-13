package com.rd.tree;


import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class BinarySearchTree<T extends Comparable<T>> extends BinaryTree<T> {


    void add(T data) {
        super.root = addAfter(super.root, data);
    }

    void add(Node<T> nodeToInsert) {
        super.root = addAfter(super.root, nodeToInsert);
    }

    Node<T> search(T data) {
        if (root == null)
            return null;
        Node<T> node = this.root;
        while (node != null) {
            if (node.data.compareTo(data) == 0)
                return node;
            else if (node.data.compareTo(data) < 0)
                node = node.getRightChild();
            else
                node = node.getLeftChild();
        }
        return null;
    }

    //returns parent
    Node<T> addAfter(Node<T> parent, T data) {
        if (parent == null)
            return new Node<>(data);
        if (parent.data.compareTo(data) <= 0)
            parent.setRightChild(addAfter(parent.getRightChild(), data));
        else
            parent.setLeftChild(addAfter(parent.getLeftChild(), data));
        return parent;
    }

    Node<T> addAfter(Node<T> parent, Node<T> nodeToInsert) {
        if (parent == null)
            return nodeToInsert;
        if (parent.data.compareTo(nodeToInsert.data) <= 0)
            parent.setRightChild(addAfter(parent.getRightChild(), nodeToInsert));
        else
            parent.setLeftChild(addAfter(parent.getLeftChild(), nodeToInsert));

        return parent;
    }

    Node<T> findMin() {
        return findMinAfter(root);
    }

    Node<T> findMax() {
        return findMaxAfter(root);
    }

    public Node<T> findMinAfter(Node<T> node) {
        if (node == null)
            return null;
        if (node.getLeftChild() == null)
            return node;
        else
            return findMinAfter(node.getLeftChild());
    }

    public Node<T> findMaxAfter(Node<T> node) {
        if (node == null)
            return null;
        if (node.getRightChild() == null)
            return node;
        else
            return findMaxAfter(node.getRightChild());
    }


    @Test
    public void test() {
        BinarySearchTree<Character> binaryTree = new BinarySearchTree<>();

        Node<Character> a = new Node<>('A');
        Node<Character> b = new Node<>('B');
        Node<Character> c = new Node<>('C');
        Node<Character> d = new Node<>('D');
        Node<Character> e = new Node<>('E');
        Node<Character> f = new Node<>('F');
        Node<Character> g = new Node<>('G');
        Node<Character> h = new Node<>('H');
        Node<Character> x = new Node<>('X');

        binaryTree.add(d);
        binaryTree.add(e);
        binaryTree.add(f);
        binaryTree.add(a);
        binaryTree.add(b);
        binaryTree.add(c);
        binaryTree.add(g);
        binaryTree.add(h);
        binaryTree.add(x);


        System.out.println();
        binaryTree.preOrderDFS();
        System.out.println();

        binaryTree.inOrderDFS();
        System.out.println();

        binaryTree.postOrderDFS();
        System.out.println();

        assertEquals(b, binaryTree.search('B'));
        assertEquals(x, binaryTree.search('X'));
        assertNull(binaryTree.search('r'));

        assertEquals(a, binaryTree.findMin());
        assertEquals(x, binaryTree.findMax());

    }
}


