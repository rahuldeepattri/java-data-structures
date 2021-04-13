package com.rd.tree;


import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;

import static org.junit.Assert.assertEquals;

public class BinaryTree<T> {
    Node<T> root;

    static int noOfUniqueTrees(int n) {
        if (n < 0)
            return -1;
        if (n == 0)
            return 1; // this is because a null can also be arranged
        if (n == 1)
            return 1;
        return 3 + noOfUniqueTrees(n - 1);
    }

    void bFS() {
        if (root == null)
            return;
        Queue<Node<T>> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Node<T> node = queue.remove();
            System.out.print(node.getData() + " ");
            if (node.getLeftChild() != null)
                queue.add(node.leftChild);
            if (node.getRightChild() != null)
                queue.add(node.rightChild);
        }
    }

    void preOrderDFS() {
        preOrderDFSSubtree(root);
    }

    void inOrderDFS() {
        inOrderDFSSubtree(root);
    }

    void postOrderDFS() {
        postOrderDFSSubtree(root);
    }

    void preOrderDFSSubtree(Node<T> node) {
        if (node == null)
            return;
        System.out.print(node.data + " ");
        preOrderDFSSubtree(node.getLeftChild());
        preOrderDFSSubtree(node.getRightChild());
    }

    void inOrderDFSSubtree(Node<T> node) {
        if (node == null)
            return;

        inOrderDFSSubtree(node.getLeftChild());
        System.out.print(node.data + " ");
        inOrderDFSSubtree(node.getRightChild());
    }

    void postOrderDFSSubtree(Node<T> node) {
        if (node == null)
            return;
        postOrderDFSSubtree(node.getLeftChild());
        postOrderDFSSubtree(node.getRightChild());
        System.out.print(node.data + " ");

    }

    public int getDepth() {
        return getDepthFrom(root);
    }

    public int getDepthFrom(Node<T> node) {
        if (node == null)
            return 0;
        return 1 + Math.max(getDepthFrom(node.getRightChild()), getDepthFrom(node.getLeftChild()));
    }

    public Node<T> mirror() {
        return mirror(root);
    }

    private Node<T> mirror(Node<T> node) {
        if (node == null)
            return null;
        Node<T> rightChild = node.getRightChild();
        Node<T> leftChild = node.getLeftChild();
        node.setLeftChild(rightChild);
        node.setRightChild(leftChild);
        mirror(rightChild);
        mirror(leftChild);
        return node;
    }

    @Test
    public void test() {
        BinaryTree binaryTree = new BinaryTree();

        Node<Character> a = new Node<>('A');
        Node<Character> b = new Node<>('B');
        Node<Character> c = new Node<>('C');
        Node<Character> d = new Node<>('D');
        Node<Character> e = new Node<>('E');
        Node<Character> f = new Node<>('F');
        Node<Character> g = new Node<>('G');
        Node<Character> h = new Node<>('H');
        Node<Character> x = new Node<>('X');

        a.setLeftChild(b);
        a.setRightChild(c);
        c.setLeftChild(d);
        c.setRightChild(e);
        d.setLeftChild(f);
        d.setRightChild(h);
        e.setRightChild(g);
        b.setLeftChild(x);

        binaryTree.root = a;

        binaryTree.bFS();
        System.out.println();
        binaryTree.preOrderDFS();
        System.out.println();

        binaryTree.inOrderDFS();
        System.out.println();

        binaryTree.postOrderDFS();
        System.out.println();

        assertEquals(4, binaryTree.getDepth());
        binaryTree.mirror();
        assertEquals(c, a.getLeftChild());
        assertEquals(b, a.getRightChild());
        assertEquals(x, b.getRightChild());
        assertEquals(f, d.getRightChild());


    }
}

class Node<T> {
    T data;
    Node<T> leftChild;
    Node<T> rightChild;

    public Node(T data) {
        this.data = data;
    }

    public Node(T data, Node<T> leftChild, Node<T> rightChild) {
        this.data = data;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Node<T> getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(Node<T> leftChild) {
        this.leftChild = leftChild;
    }

    public Node<T> getRightChild() {
        return rightChild;
    }

    public void setRightChild(Node<T> rightChild) {
        this.rightChild = rightChild;
    }
}
