package ru.progwards.java1.lessons.bigints1;

import static ru.progwards.java1.lessons.bigints1.IntRegister.SIZEINT;

public class IntCounter extends Counter {

    public static void inc(IntRegister value) {
        for (int i = 0; i < SIZEINT + 1; i++) {
            if (value.valueArray[i].bit) {
                value.valueArray[i].bit = false;
            } else {
                value.valueArray[i].bit = true;
                break;
            }
        }
    }

    public static void dec(IntRegister value) {
        //boolean tmp = value.value[SIZEINT].bit;
        for (int i = 0; i < SIZEINT+1; i++) {
            if (value.valueArray[i].bit) {
                value.valueArray[i].bit = false;
                break;
            } else {
                value.valueArray[i].bit = true;
            }
        }
    }
}
