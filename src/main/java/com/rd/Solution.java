package com.rd;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

//	public static void main(String[] args) {
//		TreeNode root = new TreeNode(0);
//
//		root.left = new TreeNode(3);
//		root.left.right = new TreeNode(7);
//
//		root.right = new TreeNode(1);
//		root.right.left = new TreeNode(4);
//		root.right.right = new TreeNode(2);
//
//		root.right.left.left = new TreeNode(6);
//		root.right.left.left.left = new TreeNode(9);
//
//		root.right.right.left = new TreeNode(8);
//		root.right.right.right = new TreeNode(5);
//
//
//		System.out.println(new Solution().subtreeWithAllDeepest(root));
//
//	}
/*class Solution2 {
    public static void main(String[] args) {
        System.out.println(new Solution().maxCoins(new int[]{8, 2, 6, 8, 9, 8, 1, 4, 1, 5, 3, 0, 7, 7, 0, 4, 2, 2, 5, 5}));
    }

    public int maxCoins(int[] nums) {
        return bestSum(nums, new HashMap<>());
    }

    public int bestSum(int[] nums, HashMap<String, Integer> memo) {

        if (nums.length == 0)
            return 0;


        String key = Arrays.toString(nums);
        if (memo.containsKey(key)) {
            //	System.out.println("\t" + key + "found, returning " + memo.get(key).toString());
            return memo.get(key);
        }

        int bestSum = 0;
        int tempSum;
        for (int i = 0; i < nums.length; i++) {
            tempSum = getCoinsByBursting(i, nums) + bestSum(removeElement(i, nums), memo);
            bestSum = Math.max(bestSum, tempSum);
        }

        memo.put(key, bestSum);
        //System.out.println("Calculate result: " + bestSum);
        return bestSum;


    }

    public int getCoinsByBursting(int idx, int[] nums) {
        int leftValue, rightValue;
        if (idx == 0)
            leftValue = 1;
        else
            leftValue = nums[idx - 1];

        if (idx == nums.length - 1)
            rightValue = 1;
        else
            rightValue = nums[idx + 1];
        return leftValue * nums[idx] * rightValue;
    }

    public int[] removeElement(int idx, int[] nums) {
        int[] res = new int[nums.length - 1];
        int j = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i == idx)
                continue;
            res[j] = nums[i];
            j++;
        }
        return res;
    }

    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        HashMap<Integer, Integer> freqA = new HashMap<>(500);
        HashMap<Integer, Integer> freqB = new HashMap<>(500);
        HashMap<Integer, Integer> freqC = new HashMap<>(500);
        HashMap<Integer, Integer> freqD = new HashMap<>(500);

        fillFrequency(freqA, A);
        fillFrequency(freqB, B);
        fillFrequency(freqC, C);
        fillFrequency(freqD, D);
        int count = 0;
        int rsa = 0;
        for (Map.Entry<Integer, Integer> entryA : freqA.entrySet()) {
            rsa = 0;
            rsa -= entryA.getKey();
            int rsb;
            for (Map.Entry<Integer, Integer> entryB : freqB.entrySet()) {
                rsb = rsa;
                rsb -= entryB.getKey();
                int rsc;
                for (Map.Entry<Integer, Integer> entryC : freqC.entrySet()) {
                    rsc = rsb;
                    rsc -= entryC.getKey();
                    int rsd;
                    for (Map.Entry<Integer, Integer> entryD : freqD.entrySet()) {
                        rsd = rsc;
                        rsd -= entryD.getKey();
                        if (rsd == 0) // 1 2 3 4
                            count += entryA.getValue() * entryB.getValue() * entryC.getValue() * entryD.getValue();
                    }
                }
            }
        }
        return count;
    }

    void fillFrequency(HashMap<Integer, Integer> freq, int[] a) {
        for (int j : a) {
            if (freq.containsKey(j))
                freq.put(j, freq.get(j) + 1);
            else
                freq.put(j, 1);
        }
    }
}*/

public class Solution {
    public static void main(String[] args) {
        int i = new Solution().countArrangement(4);
        System.out.println(i);
    }

    public int countArrangement(int n) {
//        LinkedHashSet<Integer> choices = new LinkedHashSet<>();
        int[] choices = new int[n];
        for (int i = 1; i <= n; i++) {
//            choices.add(i);
            choices[i - 1] = i;
        }

        return permute(new int[n], choices, 1);

    }


    private int permute(int[] perm, int[] choices, int pos) {

        if (choices.length == 0) {
            System.out.println(Arrays.toString(perm));
            return 1;
        }
        int res = 0;
        //For given position/choice number, make a choice and then reduce the choice set.
        for (int i = 0; i < choices.length; i++) {
            int choice = choices[i];
            if (isChoiceGood(choice, pos)) {        //Dont make need less choices
                perm[pos - 1] = choice;
                int[] newChoices = removeCurrentChoice(choices, i);
                res += permute(perm, newChoices, pos + 1);
            }
        }
        return res;
    }

    private int[] removeCurrentChoice(int[] choices, int idx) {

        int[] newChoices = new int[choices.length - 1];

        System.arraycopy(choices, 0, newChoices, 0, idx);
        System.arraycopy(choices, idx + 1, newChoices, idx, choices.length - 1 - idx);

        return newChoices;

    }

    private boolean isChoiceGood(int choice, int pos) {
        return choice % pos == 0 || pos % choice == 0;
    }
}