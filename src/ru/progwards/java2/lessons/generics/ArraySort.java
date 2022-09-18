package ru.progwards.java2.lessons.generics;

public class ArraySort {
    public static <T> void sort(Comparable<T>[] a) {
        Comparable<T> tmp;
        for (int i = 0; i < a.length; i++) {
            for (int j = i + 1; j < a.length; j++) {
                if (a[i].compareTo((T) a[j])>0) {
                    tmp = a[i];
                    a[i] = a[j];
                    a[j] = tmp;
                }
            }
        }
    }

    public static void main(String[] args) {
        Integer[] kishmish = {23, 66, 234, 86, 35, 968, 43, 0, 54, 86};
//        String[] kishmish = {"tewgkjf", "drgrk", "aasseff", "bfrjb"};
        sort(kishmish);
        for (int i=0; i < kishmish.length; i++)
            System.out.print(kishmish[i] + " ");
        System.out.println();
    }
}
