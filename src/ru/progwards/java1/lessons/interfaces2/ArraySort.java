package ru.progwards.java1.lessons.interfaces2;

public class ArraySort {
    public static void sort(Comparable<Number>[] a) {
        Number tmp;
        for (int i = 0; i < a.length; i++) {
            for (int j = i + 1; j < a.length; j++) {
                if (a[i].compareTo(a[j]) == 1) {
                    tmp = (Number) a[i];
                    a[i] = a[j];
                    a[j] = tmp;
                }
            }
        }
    }

    public static void main(String[] args) {
        IntNumber[] kishmish = {new IntNumber(23), new IntNumber(66), new IntNumber(234), new IntNumber(86),
                new IntNumber(35), new IntNumber(968), new IntNumber(-43), new IntNumber(0), new IntNumber(-54), new IntNumber(86)};
        sort(kishmish);
        for (Object i : kishmish)
            System.out.print(i + " ");
        System.out.println();

        DoubleNumber[] kishmish2 = {new DoubleNumber(23.8), new DoubleNumber(66.0), new DoubleNumber(234.3), new DoubleNumber(86.9),
                new DoubleNumber(35.1), new DoubleNumber(968.8), new DoubleNumber(-43.3), new DoubleNumber(0.0), new DoubleNumber(-54.6), new DoubleNumber(86.9)};
        sort(kishmish2);
        for (Object i : kishmish2)
            System.out.print(i + " ");

    }


}
