package com.rd.graph.tree;

import java.util.function.Consumer;

public class MorrisTraversal {


    public <T> void inOrder(Node<T> root, Consumer<T> consumer) {

        Node<T> curr = root;


        while (curr != null) {
            if (curr.left != null) { // we should process left 1st if there is something
                Node<T> predecessor = curr.left;
                while (predecessor.right != curr && predecessor.right != null) {
                    predecessor = predecessor.right;
                }
                if (predecessor.right == null) { // we found in order predecessor
                    predecessor.right = curr; // insert back link to process this later
                    curr = curr.left; // move to left node to process it first
                } else if (predecessor.right == curr) {// we reached the current node again, it means  we have processed the left side.
                    predecessor.right = null; // remove the back link
                    consumer.accept(curr.data); // now left side is already processed
                    curr = curr.right; // we can move to right side now
                }
            } else { // we can consume current node as there is nothing in left side
                consumer.accept(curr.data);
                curr = curr.right; // we can move to right side now
            }


        }
    }

    //ROOT LEFT RIGHT
    public <T> void preOrder(Node<T> root, Consumer<T> consumer) {

        Node<T> curr = root;


        while (curr != null) {
            // we cant process a node whenever we found it
            //bec if we insert backlinks we would processing the node twice

            if (curr.left != null) { // we should process left 1st then right, if there is something

                // we need to insert a back link so that we can process the right side

                Node<T> predecessor = curr.left;

                while (predecessor.right != curr && predecessor.right != null) {
                    predecessor = predecessor.right;
                }
                if (predecessor.right == null) { // we found in order predecessor
                    predecessor.right = curr; // insert back link to process right of curr later
                    consumer.accept(curr.data);
                    curr = curr.left; // move to left node to process it first
                } else if (predecessor.right == curr) {// we reached the current node again, it means  we have processed the left side.
                    predecessor.right = null; // remove the back link
                    // we have already processed this node
                    curr = curr.right; // we can move to right side now
                }
            } else { // there is nothing on left
                consumer.accept(curr.data);
                curr = curr.right; // we can move to right side now
            }


        }
    }


}
