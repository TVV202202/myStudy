package ru.progwards.java1.lessons.bigints1;

import static ru.progwards.java1.lessons.bigints1.ByteRegister.SIZEBYTE;

public class ByteSummator extends Summator{
    public static boolean add(ByteRegister value1, ByteRegister value2) {
        boolean digit = false;
        for (int i = 0; i < SIZEBYTE+1; i++) {
            if (value1.bits[i].bit && value2.bits[i].bit) {
                if (digit) {
                    value1.bits[i] = new Bit(true);
                } else {
                    value1.bits[i] = new Bit();
                    digit = true;
                }
            }
            else if (value1.bits[i].bit || value2.bits[i].bit){
                if (digit){
                    value1.bits[i] = new Bit();
                }
                else{
                    value1.bits[i] = new Bit(true);
                }
            }
            else{
                if (digit){
                    value1.bits[i] = new Bit(true);
                    digit = false;
                }
                else {
                    value1.bits[i] = new Bit();
                }
            }

        }
        return !digit;
    }
}
