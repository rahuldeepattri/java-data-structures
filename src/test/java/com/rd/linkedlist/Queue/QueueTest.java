package com.rd.linkedlist.Queue;

import com.rd.Queue.Queue;
import org.junit.Test;

public class QueueTest {

    @Test
    public void test() {
        Queue<Integer> q = new Queue<>(Integer.class, 3);

        System.out.println(q);
        q.offer(1);
        System.out.println(q);
        q.offer(2);
        System.out.println(q);
        q.offer(3);
        System.out.println(q);
        q.offer(4);
        System.out.println(q);

        System.out.println(q.poll());
        System.out.println(q);

        System.out.println(q.offer(4));
        System.out.println(q);

        System.out.println(q.offer(5));
        System.out.println(q);

        System.out.println(q.poll());
        System.out.println(q);
        System.out.println(q.poll());
        System.out.println(q);
        System.out.println(q.poll());
        System.out.println(q);
        System.out.println(q.poll());
        System.out.println(q);
        System.out.println(q.poll());
        System.out.println(q);
        System.out.println(q.poll());
        System.out.println(q);

        System.out.println(q.isEmpty());


        System.out.println(q);
        q.offer(1);
        System.out.println(q);
        q.offer(2);
        System.out.println(q);
        q.offer(3);
        System.out.println(q);
        q.offer(4);
        System.out.println(q);
        q.offer(5);
        System.out.println(q);

        System.out.println(q.poll());
        System.out.println(q);

        System.out.println(q.offer(6));
        System.out.println(q);

        System.out.println(q.offer(7));
        System.out.println(q);

        System.out.println(q.poll());
        System.out.println(q);
        System.out.println(q.poll());
        System.out.println(q);
        System.out.println(q.poll());
        System.out.println(q);
        System.out.println(q.poll());
        System.out.println(q);
        System.out.println(q.poll());
        System.out.println(q);
        System.out.println(q.poll());
        System.out.println(q);

        System.out.println(q.isEmpty());

    }
}
