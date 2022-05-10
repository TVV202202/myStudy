package ru.progwards.java1.lessons.queues;


public class Order implements Comparable<Order> {
    private double sum;
    private int num;

    public Order(double sum, int num) {
        this.sum = sum;
        this.num = num;
    }

    public double getSum() {
        return sum;
    }

    public int getNum() {
        return num;
    }

    public static void main(String[] args) {
        Order order = new Order(100, 1);
        Order ord1 = new Order(3, 3);

        System.out.println();
    }

    @Override
    public String toString() {
        return "Order{" +
                "sum=" + sum +
                ", num=" + num +
                '}';
    }

    @Override
    public int compareTo(Order o) {
        return Double.compare(num, o.num);
    }
}
