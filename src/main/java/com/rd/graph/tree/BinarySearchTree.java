package com.rd.graph.tree;//insert lookup

import java.util.*;

public class BinarySearchTree<T extends Comparable<T>> extends BinaryTree<T> {

    // Node<T> root;

    @Override
    public void add(T data) {
        insert(data);
    }

    public void insert(T toAdd) {
        insert(this.root, toAdd);
    }

    private void insert(Node<T> node, T toAdd) {
        Objects.requireNonNull(toAdd);
        if (this.root == null) {
            this.root = new Node<>(toAdd);
            return;
        }

        T nodeData = node.data;
        int compareTo = toAdd.compareTo(nodeData);

        if (compareTo <= 0) {
            if (node.left == null) {
                node.left = new Node<>(toAdd);
            } else {
                insert(node.left, toAdd);
            }

        } else {
            if (node.right == null) {
                node.right = new Node<>(toAdd);
            } else {
                insert(node.right, toAdd);
            }
        }

    }

    public boolean lookup(T toFind) {
        return lookup(this.root, toFind);
    }


    public boolean lookup(Node<T> node, T toFind) {
        if (node == null)
            return false;

        int compareTo = node.data.compareTo(toFind);

        if (compareTo == 0)
            return true;
        else if (compareTo < 0)
            return lookup(node.right, toFind);
        else
            return lookup(node.left, toFind);
    }


    public Optional<T> getMin() {
        if (root == null)
            return Optional.empty();

        Node<T> node = root;

        while (node.left != null) {
            node = node.left;
        }
        return Optional.of(node.data);
    }

    public Optional<T> getMax() {
        if (root == null)
            return Optional.empty();

        Node<T> node = root;

        while (node.right != null) {
            node = node.right;
        }
        return Optional.of(node.data);
    }

    /**
     * Returns a list of all the elements within min and max inclusive.
     */


    public List<T> findInRangeClosed(T min, T max) {
        return findInRangeClosed(this.root, min, max);
    }

    public List<T> findInRangeClosedInOrder(Node<T> node, T min, T max) {
        if (node == null) return Collections.emptyList();

        List<T> result = new ArrayList<>();

        List<T> left = findInRangeClosed(node.left, min, max);

        result.addAll(left);

        if (isInRangeClosed(node.data, min, max) == 0) {
            result.add(node.data);

            List<T> right = findInRangeClosed(node.right, min, max);

            result.addAll(right);
        }
        return result;

    }


    public List<T> findInRangeClosed(Node<T> node, T min, T max) {
        if (node == null) return Collections.emptyList();

        List<T> result = new ArrayList<>();

        int compareTo = isInRangeClosed(node.data, min, max);

        if (compareTo < 0) { // data < min, we need to move to right

            List<T> right = findInRangeClosed(node.right, min, max);
            result.addAll(right);

        } else if (compareTo > 0) { //max < data, we need to move to left

            List<T> left = findInRangeClosed(node.left, min, max);
            result.addAll(left);

        } else { // min < data < max
            //we need to do in order processing from current node
            List<T> inOrder = findInRangeClosedInOrder(node, min, max);
            result.addAll(inOrder);
        }
        return result;

    }

    public List<T> findInRangeClosedOptimized(Node<T> node, T min, T max) {
        if (node == null) return Collections.emptyList();

        List<T> result = new ArrayList<>();

        int compareTo = isInRangeClosed(node.data, min, max);

        if (compareTo < 0) { // data <= min, we need to move to right
            List<T> right = findInRangeClosedOptimized(node.right, min, max);
            result.addAll(right);
        }
        if (compareTo == 0) {
            // min < data < max
            result.addAll(findInRangeClosedOptimized(node.left, min, max));
            result.add(node.data);
            result.addAll(findInRangeClosedOptimized(node.right, min, max));
        }
        if (compareTo > 0) { //max < data, we need to move to left

            List<T> left = findInRangeClosedOptimized(node.left, min, max);
            result.addAll(left);
        }
        return result;

    }

    /**
     * Returns 0 1 or -1, if data is in range, after range or before range.
     */
    public int isInRangeClosed(T data, T min, T max) {
        if (min.compareTo(data) > 0)
            return -1;
        else if (max.compareTo(data) < 0)
            return 1;
        else
            return 0;
    }


}


