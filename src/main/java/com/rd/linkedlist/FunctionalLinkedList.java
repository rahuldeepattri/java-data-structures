// package com.rd.linkedlist;

// import lombok.Data;

// import java.util.concurrent.atomic.AtomicInteger;
// import java.util.function.Supplier;
// import java.util.stream.Stream;

// public class FunctionalLinkedList<T extends Comparable<T>> implements Cloneable {

//     @Data
//     class Node<T extends Comparable<T>> {
//         T data;
//         Node<T> next; // by default it is null
//     }

//     Node<T> head; // by default it is null


//     public T peekHead() {
//         return head.getData();
//     }

// //    public int count(){
// //        Strea
// //    }

//     @Override
//     public FunctionalLinkedList<T> clone() throws CloneNotSupportedException {
//         return (FunctionalLinkedList<T>) super.clone();
//     }

//     public static void main(String[] args) {
//         Stream.generate(new Supplier<Integer>() {

//             private AtomicInteger value = new AtomicInteger();

//             @Override
//             public Integer get() {
//                 return value.getAndIncrement();
//             }}).parallel()
//                 .limit(4)
//                 .forEach(System.out::println);
//     }
// }


