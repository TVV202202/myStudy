package ru.progwards.java1.lessons.collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

public class Creator {
    public static Collection<Integer> fillEven(int n) {
        ArrayList<Integer> collection = new ArrayList<>();
        for (int i = 2; i <= n; i++) {
            if (i % 2 == 0) collection.add(i);
        }
        return collection;
    }

    public static Collection<Integer> fillOdd(int n) {
        LinkedList<Integer> collection = new LinkedList<>();
        int i = 1;
        while (collection.size() < n) {
            if (i % 2 != 0) collection.push(i);
            i++;
        }
        return collection;
    }

    public static Collection<Integer> fill3(int n) {
        ArrayList<Integer> collection = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            collection.add(i);
            collection.add(i * i);
            collection.add(i * i * i);
        }
        return collection;
    }

    public static void main(String[] args) {
        int n = 30;
        Collection<Integer> test1 = fillEven(n);
        for (int el : test1) {
            System.out.print(el + " ");
        }
        System.out.println();
        Collection<Integer> test2 = fillOdd(10);
        for (int el : test2) {
            System.out.print(el + " ");
        }
        System.out.println();
        Collection<Integer> test3 = fill3(10);
        for (int el : test3) {
            System.out.print(el + " ");
        }
    }
}
