package ru.progwards.java1.lessons.queues;

import java.util.*;

public class OrderQueue {
    Queue<Order> orders = new LinkedList<>();
    PriorityQueue<Order> orders1 = new PriorityQueue<>();
    PriorityQueue<Order> orders2 = new PriorityQueue<>();
    PriorityQueue<Order> orders3 = new PriorityQueue<>();

    public void add(Order order) {
        if (order.getSum() > 20000) {
            orders1.add(order);
        } else if (order.getSum() > 10000) {
            orders2.add(order);
        } else {
            orders3.add(order);
        }
        orders.clear();
        orders.addAll(orders1);
        orders.addAll(orders2);
        orders.addAll(orders3);
    }

    public Order get() {
        return orders.poll();
    }

    public static void main(String[] args) {
        Order o1 = new Order(300);
        Order o2 = new Order(500);
        Order o3 = new Order(21000);
        Order o4 = new Order(20000);
        Order o5 = new Order(10000);
        Order o6 = new Order(12000);
        Order o7 = new Order(60000);
        Order o8 = new Order(15000);

        OrderQueue orderQueue = new OrderQueue();
        orderQueue.add(o1);
        orderQueue.add(o2);
        orderQueue.add(o3);
        orderQueue.add(o4);
        orderQueue.add(o5);
        orderQueue.add(o6);
        orderQueue.add(o7);
        orderQueue.add(o8);

        while (orderQueue.orders.peek() != null)
            System.out.println(orderQueue.get());
    }

}
