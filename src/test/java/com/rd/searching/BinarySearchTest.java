package com.rd.searching;

import org.junit.Test;
import java.util.stream.*;
import static org.junit.Assert.*;

public class BinarySearchTest {


    @Test
    public void test() {

        BinarySearch bs = new BinarySearch();

        Integer[] sorted =  IntStream
                .rangeClosed(1,10)
                .boxed()
                .toArray(Integer[]::new);

        assertEquals(1,bs.find(sorted, 2));
        assertEquals(-1,bs.find(sorted, 11));
    }

    @Test
    public void testB() {

        BinarySearch bs = new BinarySearch();

        Integer[] sorted =  IntStream
                .rangeClosed(1,1)
                .boxed()
                .toArray(Integer[]::new);

        assertEquals(0,bs.find(sorted, 1));
        assertEquals(-1,bs.find(sorted, 11));
    }

    @Test
    public void testA() {

        BinarySearch bs = new BinarySearch();

        Integer[] sorted =  new Integer[0];

        assertEquals(-1,bs.find(sorted, 2));
        assertEquals(-1,bs.find(sorted, 11));
    }
}
