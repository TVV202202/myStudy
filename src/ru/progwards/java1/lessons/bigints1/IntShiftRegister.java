package ru.progwards.java1.lessons.bigints1;

import static ru.progwards.java1.lessons.bigints1.IntRegister.SIZEINT;

public class IntShiftRegister extends ShiftRegister{
    public static void left(IntRegister value){
        System.arraycopy(value.valueArray, 0, value.valueArray, 1, SIZEINT);
        value.valueArray[0] = new Bit();
    }

    public static void right(IntRegister value){
        Bit tmp = value.valueArray[SIZEINT];
        System.arraycopy(value.valueArray, 1, value.valueArray, 0, SIZEINT);
        value.valueArray[SIZEINT] = tmp;
    }
}
