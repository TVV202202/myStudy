package ru.progwards.java1.lessons.bigints1;

import static ru.progwards.java1.lessons.bigints1.IntRegister.SIZEINT;

public class IntSummator extends Summator{
    public static void add(IntRegister value1, IntRegister value2) {
        boolean digit = false;
        for (int i = 0; i < SIZEINT+1; i++) {
            if (value1.valueArray[i].bit && value2.valueArray[i].bit) {
                if (digit) {
                    value1.valueArray[i] = new Bit(true);
                } else {
                    value1.valueArray[i] = new Bit();
                    digit = true;
                }
            }
            else if (value1.valueArray[i].bit || value2.valueArray[i].bit){
                if (digit){
                    value1.valueArray[i] = new Bit();
                }
                else{
                    value1.valueArray[i] = new Bit(true);
                }
            }
            else{
                if (digit){
                    value1.valueArray[i] = new Bit(true);
                    digit = false;
                }
                else {
                    value1.valueArray[i] = new Bit();
                }
            }
        }
    }


    public static void sub(IntRegister value1, IntRegister value2) {
        if (value2.toDecString().equals("0")) {
            add(value1, value2);
        } else {
            add(value1, value2.toTwosComplement(value2));
        }
    }
}
