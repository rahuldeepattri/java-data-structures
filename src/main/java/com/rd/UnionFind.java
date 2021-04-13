package com.rd;

import java.util.Arrays;

/**
 * https://www.youtube.com/watch?v=wU6udHRIkcc
 */
public class UnionFind {

    private final int[] parentList;

    // For efficiency, we aren't using makeset, but instead initialising
    // all the sets at the same time in the constructor.
    public UnionFind(int size) {
        parentList = new int[size];
        Arrays.setAll(parentList, i -> i);
    }

    // The find method, without any optimizations. It traces up the parent
    // links until it finds the root node for A, and returns that root.
    public int findParent(int A) {
        while (parentList[A] != A) {
            A = parentList[A];
        }
        return A;
    }

    // The union method, without any optimizations. It returns True if a
    // merge happened, False if otherwise.
    public boolean unionMerge(int a, int b) {
        int rootA = findParent(a);
        int rootB = findParent(b);

        if (rootA == rootB) {
            return false;
        }
        parentList[rootA] = rootB; //or reverse;
        return true;
    }

}
