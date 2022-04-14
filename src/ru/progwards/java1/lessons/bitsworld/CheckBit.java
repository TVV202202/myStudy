package ru.progwards.java1.lessons.bitsworld;

public class CheckBit {
    public static int checkBit(byte value, int bitNumber){
        /*for (int i=0; i<bitNumber; i++){
            value >>>= 1;
        }
        return value & 1;*/
        return ((1 << bitNumber) & value) == 0 ? 0 : 1;
    }

    public static void main(String[] args) {
        System.out.println(checkBit((byte) 0b0100101, 2));
    }
}
