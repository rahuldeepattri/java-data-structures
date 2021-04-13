package com.rd.graph.tree;

import java.util.*;
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

    public void inOrderIterativeTraversal(Consumer<T> consumer) {
        Deque<Node<T>> stack = new LinkedList<>();

        Node<T> curr = this.root;

        while (true) {
            if (curr != null) { // before processing curr node process the left node.
                stack.offerFirst(curr); // will process later
                curr = curr.left; // process the left node.
            } else { // we have processed left side
                if (stack.isEmpty()) // nothing more to process
                    break;

                curr = stack.pollFirst();

                consumer.accept(curr.data); // process the root

                curr = curr.right;  // move to right
            }
        }
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

    //Root Left Right
    public void preOrderTraversalIterative(Consumer<T> consumer) {
        Node<T> curr = this.root;

        Deque<Node<T>> stack = new LinkedList<>();

        while (true) {
            if (curr != null) {
                consumer.accept(curr.data); // process current node
                stack.offerFirst(curr); // need a way to access the right node
                curr = curr.left; //move to left
            } else { // we have processed all the nodes and its left nodes.
                if (stack.isEmpty())
                    break;
                curr = stack.pollFirst(); // the current node would have been processed already,
                // we need its right node to be processed
                curr = curr.right;
            }
        }

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

    //Left Right Root
    public void postOrderTraversalIterative(Consumer<T> consumer) {
        Node<T> curr = this.root;

        Deque<Node<T>> stack = new LinkedList<>();

        // TODO how to have a visited Node<T> set with equals and hashcode
        Set<Node<T>> visited = new HashSet<>(); // Do we really need to override for our use case? yes
        while (true) {
            if (curr != null) {  // before processing root process left
                stack.offerFirst(curr);
                curr = curr.left;  // move to left
            } else { // no more left nodes then process right first.
                if (stack.isEmpty())
                    break;

                curr = stack.pop();
                if (curr.right == null || visited.contains(curr.right)) { // nothing to process on right or have visited right earlier
                    consumer.accept(curr.data);
                    visited.add(curr);
                    curr = null;  // see stack for root of current node
                } else if (!visited.contains(curr.right)) { // have not visited right side yet
                    stack.offerFirst(curr);
                    curr = curr.right; // move to right
                } /*else { // have visited left and right
                    consumer.accept(curr.data);
                    visited.add(curr);
                    curr = null;
                }*/

            }

        }
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

    ///         1
    ///     2       3
    ///   4  5
    public boolean delete(T toDelete) {
        if (this.root == null) return false;

        // parent of node having toDelete is not required as we will
        // replace the value of node having toDelete data with right most node of last level
        // we need parent of the right most node

        Node<T> nodeToDelete = null;
        Node<T> parentLeft = null;
        Node<T> parentRight = null;

        Queue<Node<T>> queue = new LinkedList<>();
        queue.offer(this.root);

        while (!queue.isEmpty()) {
            Node<T> poll = queue.poll();

            if (nodeToDelete == null && poll.data.equals(toDelete)) {
                nodeToDelete = poll;
            }
            if (poll.left != null) {
                queue.offer(poll.left);
                parentLeft = poll;
                parentRight = null;
            }
            if (poll.right != null) {
                queue.offer(poll.right);
                parentLeft = null;
                parentRight = poll;
            }

        }
        if (nodeToDelete == null)
            return false;

        if (parentLeft != null) {
            nodeToDelete.data = parentLeft.left.data;
            parentLeft.left = null; // since we are finding the parent of last node
        } else if (parentRight != null) {
            nodeToDelete.data = parentRight.right.data;
            parentRight.right = null;
        } else { //we have only root node
            this.root = null;
        }

        return true;
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

    public void print() {
        TreePrinter.print(this.root);
    }
}

class Node<T> implements TreePrinter.PrintableNode {
    private final UUID uuid;
    public T data;
    public Node<T> left;
    public Node<T> right;

    public Node(T data) {
        this.data = data;
        this.uuid = UUID.randomUUID();
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Node<?> node = (Node<?>) o;

        return uuid.equals(node.uuid);
    }

    @Override
    public int hashCode() {
        return uuid.hashCode(); // we can let it be default
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



