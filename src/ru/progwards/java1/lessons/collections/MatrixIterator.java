package ru.progwards.java1.lessons.collections;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixIterator<T> implements Iterator<T> {
    private T[][] array;
    private int row;
    private int column;

    MatrixIterator(T[][] array) {
        this.array = array;
    }

    @Override
    public boolean hasNext() {
        return row < array.length && column < array[row].length;
    }

    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }

        if (row < array.length && column == array[row].length - 1) { //последний столбец
            T el = array[row++][column];
            column = 0;
            return el;
        } else {
            return array[row][column++];// предпоследний столбец
        }
    }

    public static void main(String[] args) {
        Integer[][] test = {{1, 2, 3, 4}, {5, 6}, {7, 8, 9}};
        for (MatrixIterator<Integer> iterator = new MatrixIterator<>(test); iterator.hasNext(); ) {
            int s = iterator.next();
            System.out.print(s);
        }
    }
}
