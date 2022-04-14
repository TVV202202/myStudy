package ru.progwards.java1.lessons.bigints1;

public class Bit {
    boolean bit;

    public Bit() {

    }

    public Bit(boolean value) {
        bit = value;
    }

    public String toString() {
        if (bit)
            return "1";
        else
            return "0";
    }

    public static void main(String[] args) {
        Bit www = new Bit(true);
        System.out.println(www);
    }
}
