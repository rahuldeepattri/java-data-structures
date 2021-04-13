package com.rd.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Backtracking {

    public List<List<Character>> generate(String string) {
        Character[] choices = new Character[string.length()];
        for (int i = 0; i < string.length(); i++) {
            choices[i] = string.charAt(i);
        }
        return generate(choices);
    }

    public <T> List<List<T>> generate(T[] choices) {

        return this.combinations(choices.length).stream()
                .map(combination -> IntStream.range(0, choices.length)
                        .filter(i -> combination[i])
                        .mapToObj(i -> choices[i])
                        .collect(Collectors.toList())
                )
                .collect(Collectors.toList());
    }





    /*
        0000
        0001
        0010
        0011

    //exhaustive search
    public List<T> exploreSearchSpace(T[] decisions) {    //List<T> exploreSearchSpace(T[] decisions, current state, choices) {


        //base case
        //if there are no more decisions to make stop
        // ie |decisions| == our goal

        // else there are decisions to make
        // then lets handle one decision my ourselves and
        // and allow recursion to handle the rest ie make recursive call
        // for each available choice C for current decision
        // -> Choose C , if it satisfies a constraint
        // -> explore the remaining decisions that could follow C
        // ->  explore( decisions + 1, choices - 1)

        return null;
    }


    //backtrack
    public List<T> exploreBacktrack(T[] decisions) {    // exploreBacktrack(decisions, current state, choices) {


        //base case
        //if there are no more decisions to make stop
        // ie |decisions| == our goal

        // else there are decisions to make
        // then lets handle one decision my ourselves and
        // and allow recursion to handle the rest ie make recursive call
        // for each available choice C for current decision
        // -> Choose C , if it satisfies a constraint
        // -> explore the remaining decisions that could follow C
        // -> explore( decisions + 1, choices - 1)
        //* -> Un-Choose C (backtrack!)

        return null;
    }

    /*
     *  a b c d
     *
     *  []
     *
     *
     * */

    public List<boolean[]> combinations(int things) {
        return combinations(things, new boolean[things], things);
    }

    private List<boolean[]> combinations(int things, boolean[] madeDecisions, int size) {
        List<boolean[]> result;
        if (things == 0) { // no more decisions need to be made
            result = new ArrayList<>(1);
            result.add(madeDecisions);

        } else { // make a decision

            // we will make decision for last position
            int pos = things - 1;

            boolean[] doNotSelect = new boolean[size];
            copyPrevDecisions(doNotSelect, madeDecisions, pos, size - things + 1);
            doNotSelect[pos] = false; // do not select the last element
            // explore
            List<boolean[]> choiceA = combinations(things - 1, doNotSelect, size); // let recursion handle the other decisions

            boolean[] select = new boolean[size];
            copyPrevDecisions(select, madeDecisions, pos, size - things + 1);
            select[pos] = true;// do  select the last element
            // explore
            List<boolean[]> choiceB = combinations(things - 1, select, size);

            // there is no need to un-choose here
            result = choiceA;
            result.addAll(choiceB);
        }
        return result;
    }

    public List<boolean[]> combinationsBuildFromFront(int things) {


        return combinationsBuildFromFront(0, new ArrayList<>(), things);
    }

    private List<boolean[]> combinationsBuildFromFront(int decisionsMade, List<Boolean> chosen, int totalDecisions) {
        if (decisionsMade == totalDecisions) {
            boolean[] end = new boolean[chosen.size()];
            int ptr = 0;
            for (boolean choice : chosen) {
                end[ptr++] = choice;
            }
            ArrayList<boolean[]> list = new ArrayList<>();
            list.add(end);
            return list;
        } else {
            //for each choice either select or not select

            // choose C
            // explore

            // C=do not select current, choose explore un-choose
            chosen.add(Boolean.FALSE);
            List<boolean[]> doNotAdd = combinationsBuildFromFront(decisionsMade + 1, chosen, totalDecisions);
            chosen.remove(chosen.size() - 1);

            // C=do select current, choose explore un-choose
            chosen.add(Boolean.TRUE);
            List<boolean[]> doAdd = combinationsBuildFromFront(decisionsMade + 1, chosen, totalDecisions);
            chosen.remove(chosen.size() - 1);


            doNotAdd.addAll(doAdd);
            return doNotAdd;

        }
    }

    private Object copyPrevDecisions(Object newDecision, Object madeDecisions, int from, int size) {
        System.arraycopy(madeDecisions, from, newDecision, from, size);
//        while (from < newDecision.length) {
//            newDecision[from] = madeDecisions[from];
//            from++;
//        }
        return newDecision;
    }

    public List<List<Character>> permute(String string) {
        Character[] choices = new Character[string.length()];
        for (int i = 0; i < string.length(); i++) {
            choices[i] = string.charAt(i);
        }
        return permute(choices);
    }

    public <T> List<List<T>> permute(T[] choices) {

        return this.permutations(choices.length).stream()
                .map(permutation -> {
                    List<T> list = new ArrayList<>();
                    for (int idx :
                            permutation) {
                        list.add(choices[idx]);
                    }
                    return list;
                })
                .collect(Collectors.toList());
    }

    public List<int[]> permutations(int things) {
        List<Integer> choices = IntStream.range(0, things).boxed().collect(Collectors.toList());
        boolean[] availableChoices = new boolean[things];
        Arrays.fill(availableChoices, true);

        return permutationsBuildFromFront(things, new ArrayList<>(), choices, availableChoices);
    }

    /*   public List<int[]> permutationsBuildFromBack(int things, int[] prevDecisions, Set<Integer> choices, int size) {

           if (things == 0) {
               return Collections.singletonList(prevDecisions);
           } else {
               List<int[]> result = new ArrayList<>();
               int choiceFor = things - 1;

               Set<Integer> currentChoices = new HashSet<>(choices);
               for (int choice : currentChoices) {

                   currentChoices.remove(choice); // choose a choice

                   int[] newDecision = (int[]) copyPrevDecisions(new int[size], prevDecisions, choiceFor, size - things + 1);
                   newDecision[choiceFor] = choice;
                   List<int[]> permutations = permutationsBuildFromBack(things - 1, newDecision, currentChoices, size);
                   result.addAll(permutations);

                   currentChoices.add(choice); // Un-choose the choice
               }
               return result;
           }

       }
   */
    public List<int[]> permutationsBuildFromFront(int things, List<Integer> choicesMade, List<Integer> choices, boolean[] availableChoices) {

        if (things == choicesMade.size()) {
            int[] ints = choicesMade.stream().mapToInt(Integer::intValue).toArray(); // copy
            return Collections.singletonList(ints);
        } else {
            List<int[]> result = new ArrayList<>();

            for (int i = 0, choicesSize = choices.size(); i < choicesSize; i++) {
                if (!availableChoices[i]) { // if current choice is not available, then dont consider it
                    continue;
                }
                int choice = choices.get(i);
                //"choose"
                choicesMade.add(choice);
                availableChoices[i] = false; // make it unavailable for others.
                // or choices.remove(i);

                //explore
                List<int[]> permutations = permutationsBuildFromFront(things, choicesMade, choices, availableChoices);
                result.addAll(permutations);

                //un-choose
                choicesMade.remove(choicesMade.size() - 1);
                availableChoices[i] = true; // make it available for others.
                //or choices.add(i, choice)
            }

            return result;
        }

    }


//    1 2 3
    //    3 1 2 3
    //  12
    // 1
}
//explore(state):
//  if goal reached process result
//  for each valid choice, valid check here can be optimized
//  choose
//  explore(state - choosen)
//  un-choose (backtrack or try state with other choice)