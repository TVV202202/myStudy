package ru.progwards.java1.lessons.queues;

import java.util.LinkedList;

public class StackCalc {
    LinkedList<Double> stack = new LinkedList<>();

    public void push(double value) {
        stack.push(value);
    }

    public double pop() {
        return stack.poll();
    }

    public void add() {
        stack.push(stack.pop() + stack.pop());
    }

    public void sub() {
        double tmp = stack.pop();
        stack.push(stack.pop() - tmp);
    }

    public void mul() {
        stack.push(stack.pop() * stack.pop());
    }

    public void div() {
        double tmp = stack.pop();
        if (tmp != 0) {
            stack.push(stack.pop() / tmp);
        } else {
            stack.push(1.0);
        }
    }

    public static void main(String[] args) {
        StackCalc test = new StackCalc();
        test.push(1.0);
        System.out.println();
    }
}
