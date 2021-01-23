package com.rd;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

enum Suit {
    S1,
    S2;
}

enum Rank {
    ONE, TWO;
}

public class LRUCache<K, V> extends LinkedHashMap<K, V> {
    private final int maxSize;

    public LRUCache(int maxSize) {
        super(maxSize, 0.75f, true);
        this.maxSize = maxSize;
    }

    public static void main2(String[] args) {
        LRUCache<Object, Object> cache = new LRUCache<>(3);//, 0.75f, true);

        cache.put(1, 1);
        System.out.println(cache);

        cache.put(2, 2);
        System.out.println(cache);

        cache.put(3, 3);
        System.out.println(cache);

        cache.put(4, 4);
        System.out.println(cache);

        cache.put(1, 1);
        System.out.println(cache);

        cache.get(3);
        System.out.println(cache);

        cache.put(5, 5);

        System.out.println(cache);


    }

    public static void main(String[] args) {
//        TreeSet
    }

    // Stream-based Cartesian product computation
    private static List<String> newDeck() {
        return Stream.of(Suit.values())
                .peek(System.out::println)
                .flatMap(suit -> Stream.of(Rank.values())
                        .peek(System.out::println)
                        .map(rank -> new Card(suit, rank)))
                .peek(System.out::println)
                .map(card -> card.toString())
                .peek(System.out::println)

                .collect(toList());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        LRUCache<?, ?> lruCache = (LRUCache<?, ?>) o;

        return maxSize == lruCache.maxSize;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + maxSize;
        return result;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return this.size() > maxSize;
    }
}

class Card {
    private final Suit suit;
    private final Rank rank;

    public Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    @Override
    public String toString() {
        return "Card{" +
                "suit=" + suit +
                ", rank=" + rank +
                '}';
    }
}
