package com.rd;

import java.util.ArrayList;
import java.util.Comparator;

public class Main {
    static int b = 10;
    int t = 100;

    public static void main(String[] args) {
        // write your code here
        ArrayList<Object> arrayList = new ArrayList<>();

        arrayList.add("adsad");
        arrayList.add("bdsad");
        arrayList.add("00");
        arrayList.add(12.0);
        arrayList.add(12.0);
        arrayList.add(12.0);
        arrayList.add(11);
        arrayList.add(4);
        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(1);
//        arrayList.add(IntStream.range(1,5).boxed().collect(Collectors.toList()));
        arrayList.sort(new Comparator<Object>() {
            @Override
            public int compare(Object o1, Object o2) {
                return o1.hashCode() - o2.hashCode();
            }
        });
        System.out.println(arrayList);

    }

    public class temp {
        int a = b;
        int c;

        public void main(String[] args) {
            Main main = new Main();
            main.t = 1;
        }
    }
}
