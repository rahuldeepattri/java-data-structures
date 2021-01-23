package com.rd.linkedlist.Queue;

import com.rd.Queue.QueueBuildWith2Stacks;
import org.junit.Test;

public class QueueBuildWith2StacksTest {

    @Test
    public void test() {
        QueueBuildWith2Stacks<Integer> q = new QueueBuildWith2Stacks<>();

        System.out.println(q.toString());
        q.offer(1);
        System.out.println(q.toString());
        q.offer(2);
        System.out.println(q.toString());
        q.offer(3);
        System.out.println(q.toString());
        q.offer(4);
        System.out.println(q.toString());


        q.poll();
        System.out.println(q.toString());
        q.poll();
        System.out.println(q.toString());

        q.offer(5);
        System.out.println(q.toString());
        q.offer(6);
        System.out.println(q.toString());
        q.offer(7);
        System.out.println(q.toString());
        q.offer(8);
        System.out.println(q.toString());

        q.poll();
        System.out.println(q.toString());
        q.poll();
        System.out.println(q.toString());
        q.poll();
        System.out.println(q.toString());
        q.poll();
        System.out.println(q.toString());
        q.poll();
        System.out.println(q.toString());
        q.poll();
        System.out.println(q.toString());
        q.poll();
        System.out.println(q.toString());


    }
}
