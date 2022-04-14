package ru.progwards.java1.lessons.bigints1;

import static ru.progwards.java1.lessons.bigints1.IntRegister.SIZEINT;

public class Register {
    private int size;
    public Bit[] valueArray;

    public String toString(){
        return null;
    }

    public String toDecString(){
        return null;
    }

    private Register toTwosComplement(Register value){
        Register newVal = new Register();
        for (int i=0; i<value.valueArray.length+1; i++) { // инвертируем
            newVal.valueArray[i].bit = !newVal.valueArray[i].bit;
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

}
