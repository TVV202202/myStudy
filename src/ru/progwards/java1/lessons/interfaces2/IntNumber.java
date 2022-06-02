package ru.progwards.java1.lessons.interfaces2;

public class IntNumber extends Number {
    int num;

    public IntNumber(int num) {
        this.num = num;
    }

    @Override
    public Number mul(Number num) {
        return new IntNumber(Integer.parseInt(num.toString()) * this.num);
    }

    @Override
    public Number div(Number num) {
        return new IntNumber(this.num / Integer.parseInt(num.toString()));
    }

    @Override
    public Number newNumber(String strNum) {
        return new IntNumber(Integer.parseInt(strNum));
    }

    @Override
    public String toString() {
        return Integer.toString(num);
    }

    @Override
    public int compareTo(Object o) {
        return Integer.compare(this.num, Integer.parseInt(o.toString()));
    }



    public static void main(String[] args) {
        IntNumber test1 = new IntNumber(3);
        IntNumber test2 = new IntNumber(4);
//        System.out.println(test1.mul(test2));
//        System.out.println(test1.div(test2));
//        System.out.println(test1.newNumber("-8"));
        System.out.println(test1.compareTo(test2));

    }
}
