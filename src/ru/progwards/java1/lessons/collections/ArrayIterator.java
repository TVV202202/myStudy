package ru.progwards.java1.lessons.collections;

import java.util.*;

public class ArrayIterator<T> implements Iterator<T> {
    private T[] array;
    private int pos;

    ArrayIterator(T[] array) {
        this.array = array;
    }

    @Override
    public boolean hasNext() {
        return pos < array.length;
    }

    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return array[pos++];
    }

    public static void main(String[] args) {
        Integer[] str = {9, 27, 4, 16, 64, 5, 25, 125, 6, 36, 216, 7, 49, 343, 8, 64, 512, 9, 81, 729, 10, 100, 1000};
        for (ArrayIterator<Integer> iterator = new ArrayIterator<>(str); iterator.hasNext(); ) {
            System.out.println(iterator.next());
        }

    }
}