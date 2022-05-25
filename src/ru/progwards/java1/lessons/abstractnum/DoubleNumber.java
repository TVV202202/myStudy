package ru.progwards.java1.lessons.abstractnum;

public class DoubleNumber extends Number {
    double num;

    public DoubleNumber(double num) {
        this.num = num;
    }

    @Override
    public Number mul(Number num) {
        return new DoubleNumber(this.num * Double.parseDouble(num.toString()));
    }

    @Override
    public Number div(Number num) {
        return new DoubleNumber(this.num / Double.parseDouble(num.toString()));
    }

    @Override
    public Number newNumber(String strNum) {
        return new DoubleNumber(Double.parseDouble(strNum));
    }

    @Override
    public String toString() {
        return Double.toString(num);
    }

    public static void main(String[] args) {
        Number test1 = new DoubleNumber(2.5);
        Number test2 = new DoubleNumber(2.0);
        System.out.println(test1.mul(test2));
        System.out.println(test1.div(test2));
        System.out.println(test1.newNumber("4.865488576867"));
    }
}
