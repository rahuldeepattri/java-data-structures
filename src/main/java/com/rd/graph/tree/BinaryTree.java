package com.rd.graph.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.function.Consumer;

public class BinaryTree<T> {

    protected Node<T> root;

    public BinaryTree(T data) {
        root = new Node<>(data);
    }

    public BinaryTree() {
    }

    //Left Root Right
    public void inOrderTraversal(Consumer<T> consumer) {
        inOrderTraversal(root, consumer);
    }

    private void inOrderTraversal(Node<T> root, Consumer<T> consumer) {
        if (root == null) return;

        inOrderTraversal(root.left, consumer);

        consumer.accept(root.data);

        inOrderTraversal(root.right, consumer);
    }

    //Root Left Right
    public void preOrderTraversal(Consumer<T> consumer) {
        preOrderTraversal(root, consumer);
    }

    private void preOrderTraversal(Node<T> root, Consumer<T> consumer) {
        if (root == null) return;

        consumer.accept(root.data);

        preOrderTraversal(root.left, consumer);

        preOrderTraversal(root.right, consumer);

    }

    //Left Right Root
    public void postOrderTraversal(Consumer<T> consumer) {
        postOrderTraversal(root, consumer);
    }

    private void postOrderTraversal(Node<T> root, Consumer<T> consumer) {
        if (root == null) return;

        postOrderTraversal(root.left, consumer);

        postOrderTraversal(root.right, consumer);

        consumer.accept(root.data);

    }


    public void breadthFirstTraversal(Consumer<T> consumer) {

        Queue<Node<T>> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {

            Node<T> node = queue.poll();

            if (node == null) continue;

            consumer.accept(node.data);

            queue.offer(node.left);
            queue.offer(node.right);
        }

    }


    ///         1
///     2       3
///   4  5      6  7
    public void add(T data) {

        if (this.root == null) {
            root = new Node<>(data);
            return;
        }
        Queue<Node<T>> queue = new LinkedList<>();

        queue.offer(this.root);

        while (!queue.isEmpty()) {
            Node<T> node = queue.poll();

            if (node.left == null) {
                node.left = new Node<>(data);
                return;
            } else if (node.right == null) {
                node.right = new Node<>(data);
                return;
            }

            queue.offer(node.left);
            queue.offer(node.right);

        }

    }

    public int depth() {
        return depth(root);
    }


    private int depth(Node<T> node) {
        if (node == null) return 0;
        return 1 + Math.max(depth(node.left), depth(node.right));
    }

    public BinaryTree<T> mirror() {
/*
	        1
         2    3
     4    5  6    7

	        1
         3     2
       7   6  5   4
*/

        BinaryTree<T> bt = new BinaryTree<>();
        if (this.root == null) return bt;
        bt.root = new Node<>(this.root.data);
        mirror(this.root, bt.root);
        return bt;

    }

    public void mirror(Node<T> src, Node<T> dest) {
        if (src == null) return;

        if (src.right != null)
            dest.left = new Node<>(src.right.data);

        if (src.left != null)

            dest.right = new Node<>(src.left.data);

        mirror(src.left, dest.right);
        mirror(src.right, dest.left);


    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof BinaryTree<?>))
            return false;
        List<T> thisElements = new ArrayList<>();
        List<T> otherElements = new ArrayList<>();

        this.inOrderTraversal(thisElements::add);
        ((BinaryTree<T>) o).inOrderTraversal(otherElements::add);

        return thisElements.equals(otherElements);
    }


    @Override
    public String toString() {
        return "BinaryTree{" +
                "root=" + root +
                '}';
    }
}

class Node<T> implements TreePrinter.PrintableNode {
    public T data;
    public Node<T> left;
    public Node<T> right;

    public Node(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data + '}';
    }


    @Override
    public TreePrinter.PrintableNode getLeft() {
        return left;
    }

    @Override
    public TreePrinter.PrintableNode getRight() {
        return right;
    }

    @Override
    public String getText() {
        return data.toString();
    }
}



