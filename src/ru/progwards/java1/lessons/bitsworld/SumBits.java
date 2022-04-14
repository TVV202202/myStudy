package ru.progwards.java1.lessons.bitsworld;

public class SumBits {
    public static int sumBits(byte value) {
        int result = 0;
        boolean flag=false;
/*        if (value < 0) {
            flag =true;
            value = (byte) ~value;
        }
        while (value > 0) {
            int tmp = value & 1;
            value >>= 1;
            result += tmp;
        }
        if (flag) {
            result = 8 - result;
        }*/
        for (int i=0; i<8; i++){
            int tmp = value & 1;
            value >>= 1;
            result += tmp;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(sumBits((byte) 0b10000001));
    }
}
