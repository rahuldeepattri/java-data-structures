package com.rd.backtracking;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class BacktrackingTest {

    Backtracking backtracking = new Backtracking();

    @Test
    public void generate() {

        List<List<Character>> comb = backtracking.generate("rahul");
        System.out.println(comb);

        List<List<Character>> perm = backtracking.permute("rahul");
        System.out.println(perm);


    }

    @Test
    public void makeAChoice() {
    }

    @Test
    public void combinations() {
        List<boolean[]> combinations = this.backtracking.combinationsBuildFromFront(5);
        for (boolean[] combination : combinations) {
            System.out.println(Arrays.toString(combination));
        }
        System.out.println(combinations.size());

    }

    @Test
    public void permutations() {
        List<int[]> permutations = this.backtracking.permutations(5);
        for (int[] permutation : permutations) {
            System.out.println(Arrays.toString(permutation));
        }
        System.out.println(permutations.size());

    }
}