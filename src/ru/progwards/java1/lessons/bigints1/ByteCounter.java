package ru.progwards.java1.lessons.bigints1;

import static ru.progwards.java1.lessons.bigints1.ByteRegister.SIZEBYTE;

public class ByteCounter extends Counter{
// когда писал возникли вопросы: как делать лучше (правильнее):
//    1. присваивать значение в массив напрямую (зная, что там хранятся булевые значения) используя false/true
//    2. присваивать через new Bit(el)
//    первый способ, я думаю, быстрее, но второй не заставляет помнить в каком именно первоначальном виде хранятся элементы массива


    public static void inc(ByteRegister value) {
        for (int i = 0; i < SIZEBYTE + 1; i++) {
            if (value.bits[i].bit) {
                value.bits[i].bit = false;
            } else {
                value.bits[i].bit = true;
                break;
            }
        }
    }

    public static void dec(ByteRegister value) {
        boolean tmp = value.bits[SIZEBYTE].bit;
        for (int i = 0; i < SIZEBYTE + 1; i++) {
            if (value.bits[i].bit) {
                value.bits[i].bit = false;
                if (!tmp) {
                    break;
                }
            } else {
                value.bits[i].bit = true;
                if (tmp) {
                    break;
                }
            }
        }
    }

}
