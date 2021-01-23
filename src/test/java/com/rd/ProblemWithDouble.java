package com.rd;

import org.junit.Test;

public class ProblemWithDouble {

    @Test
    public void doubleTrouble( ) {

        System.out.println(Integer.MAX_VALUE);
        System.out.println(Double.MAX_VALUE);
        System.out.println(Double.POSITIVE_INFINITY);
        System.out.println(Double.NEGATIVE_INFINITY);
        System.out.println(Double.NaN);
        System.out.println(1/3);

        System.out.println(10f/3);
        System.out.println(10d/3);

        System.out.println(Double.compare(10f/3, 10d/3));

        System.out.println(1f/3);
        System.out.println(1d/3);
        System.out.println(Double.compare(1f/3, 1d/3));
        //https://randomascii.wordpress.com/2012/02/25/comparing-floating-point-numbers-2012-edition/
    }
}
