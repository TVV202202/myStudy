package ru.progwards.java2.lessons.generics;

import java.util.Arrays;

public class DynamicArray<T> {
    private T[] dinArray;

    public DynamicArray() {
        dinArray = (T[]) new Object[1];
    }
    public void add(T item){
        if (dinArray[dinArray.length-1]==null) {
            dinArray[dinArray.length-1]= item;
        }
        else {
            T[] tmp = (T[]) new Object[dinArray.length * 2];
            System.arraycopy(dinArray,0, tmp, 0, dinArray.length);
            tmp[tmp.length-1]=item;
            dinArray = tmp;
        }
    }
    public void insert(int pos, T item){
        dinArray[pos]=item;
    }
    public void remove(int pos){
        T[] tmp = (T[]) new Object[dinArray.length -1];
        System.arraycopy(dinArray,0, tmp, 0, pos);
        System.arraycopy(dinArray,pos+1, tmp, pos, dinArray.length-1-pos);
        dinArray = tmp;
    }
    public T get(int pos){
        return dinArray[pos];
    }
    public int size(){
        return dinArray.length;
    }
    @Override
    public String toString() {
        return "dinArray=" + Arrays.toString(dinArray);
    }

    public static void main(String[] args) {
        DynamicArray<String> tt = new DynamicArray<>();
        tt.add("2");
        tt.add("ff");
        tt.add("ouyp");
        tt.add("96");
        System.out.println(tt);
        tt.insert(2, "5");
        System.out.println(tt);
        tt.remove(4);
        System.out.println(tt);
        System.out.println(tt.get(3));
        System.out.println(tt.size());
    }
}
