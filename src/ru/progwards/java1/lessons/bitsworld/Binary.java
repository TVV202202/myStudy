package ru.progwards.java1.lessons.bitsworld;

public class Binary {
    byte num;
    public Binary(byte num){
        this.num = num;
    }

    public String toString() {
        String result = "00000000";
        byte value=num;
        if (num == -128) {
            return "10000000";
        } else if (num < 0){
            value = (byte) (num-128);
        }
            while (value > 0) {
                int tmp = value & 1;
                result = (result + tmp).substring(1, 9);
                value >>= 1;
            }
        if (num < 0){
            result = 1 + result.substring(1,8);
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
        Binary binary3 = new Binary((byte) -108);
        System.out.println(binary3);
        System.out.println(Integer.toBinaryString(-108));
        Binary binary4 = new Binary((byte) -1);
        System.out.println(binary4);
        System.out.println(Integer.toBinaryString(-1));

    }
}
