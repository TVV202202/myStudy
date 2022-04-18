package ru.progwards.java1.lessons.bigints1;

import static ru.progwards.java1.lessons.bigints1.IntCounter.dec;
//import static ru.progwards.java1.lessons.bigints1.IntCounter.inc;
import static ru.progwards.java1.lessons.bigints1.IntShiftRegister.left;
import static ru.progwards.java1.lessons.bigints1.IntShiftRegister.right;
import static ru.progwards.java1.lessons.bigints1.IntSummator.add;
import static ru.progwards.java1.lessons.bigints1.IntSummator.sub;


public class IntRegister extends Register {
    static final int SIZEINT = 31;
    Bit[] valueArray = new Bit[SIZEINT+1];

    public IntRegister(){
        for (int i = 0; i < SIZEINT + 1; i++) {
            valueArray[i] = new Bit();
        }
    }

    public IntRegister(int value){
        for (int i = 0; i < SIZEINT+1; i++){
            this.valueArray[i] = (((value & 1) == 0) ? new Bit() : new Bit(true));
            value =  value >> 1;
        }
    }

    @Override
    public String toString(){
        String res = "";
        for (int i=0; i<SIZEINT+1;i++){
            res = ((valueArray[i].bit) ? "1" : "0") + res;
        }
        return res;
    }
    @Override
    public String toDecString(){
        boolean flag = false;
        IntRegister tmp = new IntRegister();
        for (int i = 0; i < SIZEINT+1; i++){
            tmp.valueArray[i].bit = this.valueArray[i].bit;
        }
//        if (valueArray[SIZEINT].bit){
//            flag = true;
//            for (int i=0; i<SIZEINT+1; i++) { // инвертируем
//                tmp.valueArray[i].bit = !this.valueArray[i].bit;
//            }
//            for (int i = 0; i < SIZEINT; i++) { // прибавляем 1
//                if (tmp.valueArray[i].bit) {
//                    tmp.valueArray[i].bit = false;
//                } else {
//                    tmp.valueArray[i].bit = true;
//                    break;
//                }
//            }
//        }
        // новый вариант с использованием функции

        if (valueArray[SIZEINT].bit){
            flag = true;
            tmp = toTwosComplement(this);
        }

        // преобразовываем в десятичную систему счисления
        int num = 0;
        for (int i = 0; i < SIZEINT; i++) {
            if (tmp.valueArray[i].bit)
                num += Math.pow(2, i);
        }
        StringBuilder result = new StringBuilder(Integer.toString(num));
        if (flag) {
            result.insert(0, "-");
        }
        return result.toString();
    }

    public IntRegister toTwosComplement(IntRegister value){
        IntRegister newVal = new IntRegister();

        if (value.toDecString().equals("0")){
            for (int i = 0; i < SIZEINT+1; i++) {
                newVal.valueArray[i].bit = value.valueArray[i].bit;
                return newVal;
            }
        }

        for (int i=0; i<SIZEINT+1; i++) { // инвертируем
            newVal.valueArray[i].bit = !value.valueArray[i].bit;
        }
        for (int i = 0; i < SIZEINT; i++) { // прибавляем 1
            if (newVal.valueArray[i].bit) {
                newVal.valueArray[i].bit = false;
            } else {
                newVal.valueArray[i].bit = true;
                break;
            }
        }
        return newVal;
    }



    public static void main(String[] args) {
        IntRegister intRegister0 = new IntRegister(253);
        System.out.println(intRegister0);
        System.out.println(intRegister0.toDecString());

        IntRegister intRegister2 = new IntRegister(0);
        System.out.println(intRegister2);
        System.out.println(intRegister2.toDecString());
//
//        IntRegister tmp = intRegister2.toTwosComplement(intRegister2);
//        System.out.println(tmp);

//        add(intRegister0,intRegister2);
//        System.out.println(intRegister0);
//        System.out.println(intRegister0.toDecString());

        sub(intRegister0,intRegister2);
        System.out.println(intRegister0);
        System.out.println(intRegister0.toDecString());

//        System.out.println(2147483647<<1);
//        left(intRegister0);
//        System.out.println(intRegister0);
//        System.out.println(intRegister0.toDecString());
//
//        IntRegister intRegister1 = new IntRegister(-100);
//        System.out.println(intRegister1);
//        System.out.println(intRegister1.toDecString());

//        left(intRegister0);
//        System.out.println(intRegister0);
//        System.out.println(intRegister0.toDecString());

//        right(intRegister1);
//        System.out.println(intRegister1);
//        System.out.println(intRegister1.toDecString());

//        System.out.println(-2147483647>>1);
//        System.out.println(new IntRegister(-1073741824));

//        inc(intRegister0);
//        System.out.println(intRegister0);

//        dec(intRegister0);
//        System.out.println(intRegister0);
//        System.out.println(intRegister0.toDecString());

    }


}
