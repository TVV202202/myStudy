package ru.progwards.java1.lessons.bigints1;

import static ru.progwards.java1.lessons.bigints1.ByteRegister.SIZEBYTE;

public class ByteShiftRegister extends ShiftRegister{
    public static void left(ByteRegister value) {
        System.arraycopy(value.bits, 0, value.bits, 1, SIZEBYTE);
        value.bits[0] = new Bit();
    }

    public static void right(ByteRegister value) {
        System.arraycopy(value.bits, 1, value.bits, 0, SIZEBYTE);
        value.bits[SIZEBYTE] = new Bit();
    }
}
