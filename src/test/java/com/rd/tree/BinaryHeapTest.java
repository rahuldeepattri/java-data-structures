package com.rd.tree;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BinaryHeapTest {
    @Test
    public void TestMin() throws BinaryHeap.HeapFullException, BinaryHeap.HeapEmptyException {
        MinHeap<Integer> minHeap = new MinHeap<>(30);

        minHeap.insert(9);
        minHeap.insert(4);
        minHeap.insert(17);
        minHeap.printHeapArray();
        minHeap.insert(6);
        minHeap.printHeapArray();

        minHeap.insert(100);
        minHeap.insert(20);
        minHeap.printHeapArray();
        minHeap.insert(2);
        minHeap.insert(1);
        minHeap.insert(5);
        minHeap.insert(3);
        minHeap.printHeapArray();

        minHeap.removeHighestPriority();
        minHeap.printHeapArray();
        minHeap.removeHighestPriority();
        minHeap.printHeapArray();
    }

    @Test
    public void TestMax() throws BinaryHeap.HeapFullException, BinaryHeap.HeapEmptyException {
        MaxHeap<Integer> maxHeap = new MaxHeap<>(40);

        maxHeap.insert(9);
        maxHeap.insert(4);
        maxHeap.insert(17);
        maxHeap.printHeapArray();
        maxHeap.insert(6);
        maxHeap.printHeapArray();

        maxHeap.insert(100);
        maxHeap.insert(20);
        maxHeap.insert(2);
        maxHeap.insert(1);
        maxHeap.printHeapArray();
        maxHeap.insert(5);
        maxHeap.insert(3);
        maxHeap.printHeapArray();

        maxHeap.removeHighestPriority();
        maxHeap.printHeapArray();
        maxHeap.removeHighestPriority();
        maxHeap.printHeapArray();
    }

    @Mock
    Random random;

    @Test
    public void test() {

        LinkedList<Integer> integers = new LinkedList<>(Arrays.asList(1, 2, 3, 4, 5));
        System.out.println(integers);
        Collections.shuffle(integers);
        System.out.println(integers);
        System.out.println();
        when(random.nextInt(anyInt())).thenReturn(0);
        integers = new LinkedList<>(Arrays.asList(1, 2, 3, 4, 5, 6));
        System.out.println(integers);
        Collections.shuffle(integers, random);
        System.out.println(integers);

        System.out.println();
        integers = new LinkedList<>(Arrays.asList(1, 2, 3));
        System.out.println(integers);
        Collections.shuffle(integers, random);
        System.out.println(integers);

        System.out.println();
        System.out.println(integers);
        Collections.shuffle(integers, random);
        System.out.println(integers);

    }
}
/*
* 1. Inputs and outputs of game/program are not defined.
   Does the program waits for inputs from user or just simulates? If it waits then what is the inputs spec?

   Both the user use same console window or they use seprate window.
   Even thought its not a web application  both players are on same machine?




2. Simulate two players or is there possibility for more than 2 players? Do we built it using changes will required
, or the scope of assignment wont changes. do we forsee that user might want to track who has won how many times?

3. scope of librariies/tools that can be used, lombok / spring etc

4. any requirements for coverage/documentation source code or testing the extra functionalty apart from what is required

5. Random should be psuedo random or true random

can u please clairfy what do you mean by test random()
can we use already tested implementation of colleactions.shuffle?
not necassirily means we have to test for randmness?



Assumptions:-

1. Once the programe starts, there no input given by user.
2. The program runs on one console.
3.

There are two parts to this: testing randomization and testing things that use randomization.

Testing randomization is relatively straightforward. You check that the period of the random number generator is as you expect it to be (for a few samples using a few kinda-random seeds, within some threshold) and that the distribution of the output over a large sample size is as you expect it to be (within some threshold).

Testing things that use randomization is best done with a deterministic psuedo-random number generator. Since the output of the randomization is known based on the seed (its inputs), then you can unit test as normal based on inputs vs expected outputs. If your RNG is not deterministic, then mock it with one that is deterministic (or simply not random). Test the randomization in isolation from the code that consumes it.

If you are writing the RNG, use statistical analysis to prove your distribution, if you're writing the card shuffler, make sure there are 52 non-repeated cards in each output (it's a better case for test by inspection that you're using the RNG).

input and output have same elements, order is diffrent, size of input, boundary value,
same random suffles the deck only once, next time with same result it will shuffle
List<String> students_1 = Arrays.asList("Foo", "Bar", "Baz", "Qux");
List<String> students_2 = Arrays.asList("Foo", "Bar", "Baz", "Qux");

int seedValue = 10;

Collections.shuffle(students_1, new Random(seedValue));
Collections.shuffle(students_2, new Random(seedValue));

assertThat(students_1).isEqualTo(students_2);

the "trick" is to realize you aren't testing randomness at this point,
instead you are testing your code's response to behaviour of getNextint()
Does the program logic work for all possible getNextint values?*/
