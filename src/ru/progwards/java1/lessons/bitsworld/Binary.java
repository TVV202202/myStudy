package ru.progwards.java1.lessons.bitsworld;

public class Binary {
    byte num;
    public Binary(byte num){
        this.num = num;
    }

    public String toString() {
        //StringBuilder result = new StringBuilder();
/*        byte value=num;
        if (num < 0){
            value = (byte) (num-128);
        }
        while (value > 0) {
            int tmp = value & 1;
            result.insert(0, tmp);
            value >>= 1;
        }
        while (result.length() != 7) {
            result.insert(0, 0);
        }
        if (num < 0){
            result.insert(0, 1);
        }
        else {
            result.insert(0, 0);
        }

        return result.toString();*/

        String result="";
        for (int i = 0; i < 8; i++)
        {
//логическое  & c 1
            result = (((num & 1) == 0) ? "0" : "1") + result;
//Сдвинуть число на 1 бит
            num = (byte) (num >> 1);
        }
        return result;
    }

    public static void main(String[] args) {
        //Binary binary0 = new Binary((byte) 0);
       // System.out.println(binary0);
       // Binary binary1 = new Binary((byte) 1);
       // System.out.println(binary1);
        // Binary binary2 = new Binary((byte) 127);
        //System.out.println(binary2);
        Binary binary3 = new Binary((byte) -128);
        System.out.println(binary3);
        System.out.println(Integer.toBinaryString(-128));
        Binary binary4 = new Binary((byte) -1);
        System.out.println(binary4);
        System.out.println(Integer.toBinaryString(-1));

    }
}
