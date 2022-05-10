package ru.progwards.java1.lessons.queues;

import java.util.List;

public class Calculate {
    public static double calculation1(){
        // 2.2*(3+12.1)
        StackCalc newStack = new StackCalc();
        newStack.push(2.2);
        newStack.push(3);
        newStack.push(12.1);
        newStack.add();
        newStack.mul();
        return newStack.pop();
    }
    public static double calculation2(){
        // (737.22+24)/(55.6-12.1)+(19-3.33)*(87+2*(13.001-9.2))
        StackCalc stack = new StackCalc();
        stack.push(737.22);
        stack.push(24);
        stack.add();
        stack.push(55.6);
        stack.push(12.1);
        stack.sub();
        stack.div();
        stack.push(19);
        stack.push(3.33);
        stack.sub();
        stack.push(13.001);
        stack.push(9.2);
        stack.sub();
        stack.push(2);
        stack.mul();
        stack.push(87);
        stack.add();
        stack.mul();
        stack.add();
        return stack.pop();
    }
    public static void main(String[] args) {
        System.out.println(calculation1());
        System.out.println(2.2*(3+12.1));
        System.out.println(calculation2());
        System.out.println((737.22+24)/(55.6-12.1)+(19-3.33)*(87+2*(13.001-9.2)));
    }
}
