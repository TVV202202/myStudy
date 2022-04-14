package ru.progwards.java1.lessons.bigints1;



import static ru.progwards.java1.lessons.bigints1.ByteCounter.dec;
import static ru.progwards.java1.lessons.bigints1.ByteCounter.inc;
import static ru.progwards.java1.lessons.bigints1.ByteShiftRegister.left;
import static ru.progwards.java1.lessons.bigints1.ByteShiftRegister.right;
import static ru.progwards.java1.lessons.bigints1.ByteSummator.add;

public class ByteRegister extends Register {
    static final int SIZEBYTE = 7;
    Bit[] bits = new Bit[SIZEBYTE + 1];

    public ByteRegister() {
        for (int i = 0; i < SIZEBYTE + 1; i++) {
            bits[i] = new Bit();
        }
    }

    // хранить в массиве решил в обратном порядке, так удобнее проводить все манипуляции. 0-й разряд, 1-й и т.д.
    public ByteRegister(byte value) {
        //bits[SIZEBYTE] = (value < 0) ? new Bit(true) : new Bit();
        for (int i = 0; i < SIZEBYTE+1; i++) {
            bits[i] = new Bit(value % 2 != 0);
            value = (byte) (value / 2);
        }
    }
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < SIZEBYTE + 1; i++) {
            result.insert(0, bits[i]);
        }
        return result.toString();
    }

    @Override
    public String toDecString() {
        int num = 0;
        for (int i = 0; i < SIZEBYTE+1; i++) {
            if (bits[i].bit)
                num += Math.pow(2, i);
        }
        return Integer.toString(num);
    }

    public static void main(String[] args) {
        byte num = (byte) 0b00000001;

        ByteRegister byteRegister = new ByteRegister(num);
        ByteRegister byteRegister1 = new ByteRegister((byte) 0);
        for (int i = 0; i < SIZEBYTE + 1; i++) {
            System.out.print(byteRegister.bits[SIZEBYTE - i]);
        }
        System.out.println();

        System.out.println(byteRegister.toDecString() + " это "
                + byteRegister.toString());
        System.out.println(byteRegister1.toDecString() + " это "
                + byteRegister1.toString());

        left(byteRegister);
        System.out.println("left это " + byteRegister.toDecString() + " это "
                + byteRegister);
//        right(byteRegister);
//        System.out.println("right это " + byteRegister.toDecString() + " это "
//                + byteRegister);
//
//        inc(byteRegister);
//        System.out.println("inc 1 это " + byteRegister.toDecString() + " это "
//                + byteRegister);
        dec(byteRegister);
        System.out.println("dec 1 это " + byteRegister.toDecString() + " это "
                + byteRegister);
        inc(byteRegister);
        System.out.println("inc 1 это " + byteRegister.toDecString() + " это "
                + byteRegister);

        System.out.println((byte) (num));

        if (add(byteRegister,byteRegister1)){
            System.out.println(byteRegister.toDecString());
            System.out.println(byteRegister);

        }
        else{
            System.out.println("переполнение");
        }
    }
}
