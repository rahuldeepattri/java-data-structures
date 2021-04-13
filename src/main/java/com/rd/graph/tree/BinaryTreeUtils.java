package com.rd.graph.tree;

import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;

public class BinaryTreeUtils {

    /**
     * Given a binary tree and a number,
     * return true if the tree has a root-to-leaf path such that
     * adding up all the values along the path equals the given number.
     * Return false if no such path can be found.
     */

    public static <B extends Integer> boolean rootToLeafPath(BinaryTree<B> bt, int sum) {
        return nodeToLeafPath(bt.root, sum);
    }

    private static <B extends Integer> boolean nodeToLeafPath(Node<B> node, int sum) {
        if (node == null)
            return false;

        sum = sum - node.data.intValue();

        if (sum == 0 && node.left == null && node.right == null)
            return true;

        return nodeToLeafPath(node.left, sum) || nodeToLeafPath(node.right, sum);


    }


    /**
     * Given a binary tree, print out all of its root-to-leaf paths, one per line.
     * This problem is a little harder than it looks, since the "path so far" needs to be communicated between the recursive calls. Hint: In C, C++, and Java, probably the best solution is to create a recursive helper function printPathsRecur(node, int path[], int pathLen), where the path array communicates the sequence of nodes that led up to the current call. Alternately, the problem may be solved bottom-up, with each node returning its list of paths. This strategy works quite nicely in Lisp, since it can exploit the built in list and mapping primitives. (Thanks to Matthias Felleisen for suggesting this problem.)
     */

    public static <T> void printPaths(BinaryTree<T> bt, PrintStream out) {
        printPaths(bt.root, out, new ArrayList<>(bt.depth()));
    }

        /*
            1
          2		3
        4   5    6     null
        */

    public static <T> void printPaths(Node<T> node, PrintStream out, List<Node<T>> path) {
        if (node == null)
            return;

        path.add(node);

        if (node.left == null && node.right == null) {
            List<T> collect = path.stream().map(tNode -> tNode.data).collect(Collectors.toList());
            out.println(collect);
            path.remove(path.size() - 1);
            return;
        }

        printPaths(node.left, out, path);
        printPaths(node.right, out, path);
        path.remove(path.size() - 1);

    }

    /**
     * Find the least common ancestor of two in a Binary Tree.
     * Note that the root node is the common ancestor of all the nodes.
     */

  /*
              1
          2		3
        4   5    6  null
 */
    public static <T> Optional<Node<T>> leastCommonAncestor(BinaryTree<T> bt, T first, T second) {
        return leastCommonAncestor(bt.root, first, second);
        //TODO check if a better way is possible https://www.youtube.com/watch?v=py3R23aAPCA
    }

    public static <T> Optional<Node<T>> leastCommonAncestor(Node<T> root, T first, T second) {

        if (root == null) {
            return Optional.empty(); //how it return correct type?
        }

        Optional<Node<T>> left = leastCommonAncestor(root.left, first, second);

        if (left.isPresent()) {
            return left;
        }

        Optional<Node<T>> right = leastCommonAncestor(root.right, first, second);

        if (right.isPresent()) {
            return right;
        }

        Node<T> leastCommonAncestor = null;

        if (find(root, first) && find(root, second)) {
            leastCommonAncestor = root;
        }


        return Optional.ofNullable(leastCommonAncestor);

    }

    // return the node that it found out of first and second
    public static <T> boolean find(Node<T> fromNode, T toFind) {
        if (fromNode == null) return false;
        if (fromNode.data.equals(toFind)) return true;

        return find(fromNode.left, toFind) || find(fromNode.right, toFind);
    }


    public static <T> void copy(BinaryTree<T> src, BinaryTree<T> dest) {

        if (src.root == null) return;
        dest.root = new Node<T>(src.root.data);


        Deque<Node<T>> srcStack = new ArrayDeque<>();
        srcStack.offerFirst(src.root);

        Deque<Node<T>> destStack = new ArrayDeque<>();
        destStack.offerFirst(dest.root);

        while (!srcStack.isEmpty()) {

            Node<T> srcNode = srcStack.pollFirst();
            Node<T> destNode = destStack.pollFirst();

            if (srcNode.left != null) {
                destNode.left = new Node<>(srcNode.left.data);
                srcStack.offerFirst(srcNode.left);
                destStack.offerFirst(destNode.left);
            }
            if (srcNode.right != null) {
                destNode.right = new Node<>(srcNode.right.data);
                srcStack.offerFirst(srcNode.right);
                destStack.offerFirst(destNode.right);
            }

        }
    }


    /**
     * Changes the tree by inserting a duplicate node
     * on each nodes's .left.
     * <p>
     * <p>
     * So the tree...
     * 2
     * / \
     * 1   3
     * Is changed to...
     * 2
     * / \
     * 2   3
     * /   /
     * 1   3
     * /
     * 1
     * Uses a recursive helper to recur over the tree
     * and insert the duplicates.
     */


    public static <T extends Comparable<T>> BinarySearchTree<T> doubleTree(BinarySearchTree<T> bst) {

        BinarySearchTree<T> doubleTree = new BinarySearchTree<>();
        copy(bst, doubleTree);
        doubleTree(doubleTree.root);
        return doubleTree;
    }


    public static <T> void doubleTree(Node<T> node) {
        if (node == null) return;

        doubleTree(node.left);
        doubleTree(node.right);

        Node<T> copy = new Node<>(node.data);
        if (node.left == null) {
            node.left = copy;
        } else {
            Node<T> tmp = node.left;
            node.left = copy;
            node.left.left = tmp;
        }

    }

    public static double countStructurallyUniqueTrees(int numberOfNodes) {
        if (numberOfNodes <= 1)
            return 1;
        // select each node as root node once
        double count = 0;
        for (int i = 1; i <= numberOfNodes; i++) {
            // i th node is root node

            // there will be one value at the root, with whatever remains
            // on the left and right each forming their own subtrees.
            // Iterate through all the values that could be the root...

            // for nodes that are on left side of i th node

            double leftCombinations = countStructurallyUniqueTrees(i - 1);


            // for nodes that are on right side of i th node
            double rightCombinations = countStructurallyUniqueTrees(numberOfNodes - i);

            count += leftCombinations * rightCombinations;
        }
        return count;
    }

    public static double countStructurallyUniqueTreesDP(int numberOfNodes) {
        double[] dp = new double[numberOfNodes + 1];
        dp[0] = 1;
        dp[1] = 1;

        // there will be one value at the root, with whatever remains
        // on the left and right each forming their own subtrees.
        // Iterate through all the values that could be the root...
        for (int i = 2; i <= numberOfNodes; i++) {
            double count = 0;
            for (int n = 1; n <= i; n++) {
                count += dp[n - 1] * dp[i - n];
            }
            dp[i] = count;
        }
        // 2 o  o dp[0] * dp[1] + dp[1] * dp[0]

        // 0 0 0 dp[0] * dp[2] + dp[1] * dp[1] + dp[2] * dp[0]
        return dp[numberOfNodes];
    }

    public static <T extends Comparable<T>> boolean isBST(BinaryTree<T> bt) {
        if (bt.root == null) return true;

        Deque<Node<T>> stack = new LinkedList<>();
        stack.offerFirst(bt.root);

        T prev = null;
        Node<T> curr = bt.root;
        while (true) {
            if (curr != null) { // we can process node
                stack.offerFirst(curr);// process later
                curr = curr.left; // process left first
            } else { // Nothing left to process, then check stack
                if (stack.isEmpty()) {
                    break;
                }
                curr = stack.pollFirst();

                // process current
                if (prev == null) {
                    prev = curr.data;
                } else {
                    if (prev.compareTo(curr.data) > 0)
                        return false;
                }

                // after processing current process right;
                curr = curr.right;

            }
        }
        return true;


    }

    public static <T extends Integer> boolean isBSTInteger(BinaryTree<T> bt) {

        return isBSTInteger(bt.root, Integer.MIN_VALUE, Integer.MAX_VALUE);

    }

    public static <T extends Integer> boolean isBSTInteger(Node<T> root, Integer min, Integer max) {
        if (root == null) return true;
        if (root.data.intValue() > max || root.data.intValue() < min)
            return false;
        return
                isBSTInteger(root.left, min, root.data)
                        && isBSTInteger(root.right, root.data, max);

    }


}







