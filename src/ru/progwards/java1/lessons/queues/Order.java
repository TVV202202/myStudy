package ru.progwards.java1.lessons.queues;


public class Order implements Comparable<Order> {
    private double sum;
    private int num;
    static  int orderNo = 1;

    public Order(double sum) {
        this.sum = sum;
        num = orderNo;
        orderNo++;
    }

    public double getSum() {
        return sum;
    }

    public int getNum() {
        return num;
    }

    public static void main(String[] args) {
        Order order = new Order(100);
        Order ord1 = new Order(3);

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
