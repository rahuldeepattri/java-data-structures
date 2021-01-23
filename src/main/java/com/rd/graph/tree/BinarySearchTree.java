package com.rd.graph.tree;//insert lookup

import java.util.*;

public class BinarySearchTree<T extends Comparable<T>> extends BinaryTree<T> implements Iterable<T> {

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

    /**
     * If the node is leaf node then no issues delete it simply
     * <p>
     * If node has only 1 child only then replace with the child.
     * <p>
     * If a node has both children then we need to find the inorder successor and replace
     * the node.
     * inorder successor will be in the right subtree and left most deepest node.
     *
     * @param toDelete
     * @return
     */
    @Override
    public boolean delete(T toDelete) {
        if (this.root == null) return false;

        Node<T> nodeToDelete = this.root;
        Node<T> parentLeft = null;
        Node<T> parentRight = null;

        //Find the node to delete
        while (nodeToDelete != null) {
            int compareTo = toDelete.compareTo(nodeToDelete.data);
            if (compareTo == 0) {
                break;
            } else if (compareTo < 0) {
                parentLeft = nodeToDelete;
                parentRight = null;
                nodeToDelete = nodeToDelete.left;
            } else {
                parentRight = nodeToDelete;
                parentLeft = null;
                nodeToDelete = nodeToDelete.right;
            }
        }

        if (nodeToDelete == null) return false;

        int childCount = 0;
        if (nodeToDelete.left != null) childCount++;
        if (nodeToDelete.right != null) childCount++;

        Node<T> toReplace = null;
        switch (childCount) {
            case 0:
                toReplace = null;
                break;
            case 1:
                toReplace = nodeToDelete.left != null ? nodeToDelete.left : nodeToDelete.right;
                break;

            case 2:
                toReplace = nodeToDelete.right;

                Node<T> toReplaceParent = null;
                while (toReplace.left != null) {
                    toReplaceParent = toReplace;
                    toReplace = toReplace.left;
                }
                if (toReplaceParent == null) {
                    nodeToDelete.right = toReplace.right;
                } else {
                    toReplaceParent.left = toReplace.right;
                    // toReplace might have a right child
                }

                toReplace.left = nodeToDelete.left;
                toReplace.right = nodeToDelete.right;
                break;

        }
        if (parentLeft != null) {
            parentLeft.left = toReplace;
        } else if (parentRight != null) {
            parentRight.right = toReplace;
        } else {
            this.root = toReplace;
        }

        return true;

    }

    /**
     * If the node is leaf node then no issues delete it simply
     * <p>
     * If node has only 1 child only then replace with the child.
     * <p>
     * If a node has both children then we need to find the inorder successor and replace
     * the node.
     * inorder successor will be in the right subtree and left most deepest node.
     *
     * @param toDelete
     * @return
     */
    public void deleteRecursive(T toDelete) {
        this.root = delete(this.root, toDelete);
    }

    public Node<T> delete(Node<T> root, T toDelete) {

        if (root == null) {
            return null;
        }

        int compareTo = toDelete.compareTo(root.data);

        if (compareTo < 0) {
            root.left = delete(root.left, toDelete);
        } else if (compareTo > 0) {
            root.right = delete(root.right, toDelete);
        } else { // found the node to delete

            //Case 1 node has no children
            if (root.left == null && root.right == null) {
                root = null; // this will automatically update the
                // parent reference
            }
            // Case 2 Only one child
            else if (root.left != null && root.right == null) {
                root = root.left;
            } else if (root.right != null && root.left == null) {
                root = root.right;
            }
            //Case 3 Both the child are present
            else {

                // Make sure findRightInorderSuccesor returns
                // successor from right subtree of current node
                Node<T> successor = findRightInorderSuccessor(root);
                root.data = successor.data;
                root.right = delete(root.right, successor.data);
            }

        }


        return root;

    }

    private Node<T> findRightInorderSuccessor(Node<T> root) {
        root = root.right;
        while (root.left != null) {
            root = root.left;
        }
        return root;
    }


    @Override
    public Iterator<T> iterator() {
        List<T> list = new ArrayList<>();
        inOrderTraversal(list::add);
        return list.listIterator();
    }

}


