package ru.progwards.java1.lessons.egts;

public class EgtsDirectionAndSpeed {
    static final int TOSHORT = 0b00000000_11111111;
    static final int TOINT = 0b00000000_00000000_01111111_11111111;
    static final int TOBIG = 0b00000000_00000000_10000000_00000000;

    public static int getSpeed(short speedAndDir){
        return speedAndDir & TOINT;
    }

    public static int getDirection(byte dirLow, short speedAndDir){
        int naprJrBit = dirLow & TOSHORT;
        int grossBit = (speedAndDir & TOBIG) >> 7;
        return naprJrBit + grossBit;
    }
    public static void main(String[] args) {
        byte dirLow = (byte) 0b10000111;
        short speedAndDir = (short) 0b11000000_10000111;


        System.out.println(getDirection(dirLow, speedAndDir));
        System.out.println(getSpeed(speedAndDir));

    }

}
