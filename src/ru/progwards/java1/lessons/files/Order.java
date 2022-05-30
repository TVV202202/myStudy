package ru.progwards.java1.lessons.files;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Order {
    public String shopId; // - идентификатор магазина
    public String orderId; // - идентификатор заказа
    public String customerId; // - идентификатор покупателя
    public LocalDateTime datetime; // - дата-время заказа (из атрибутов файла - дата последнего изменения)
    public List<OrderItem> items = new ArrayList<>(); // - список позиций в заказе, отсортированный по наименованию товара
    public double sum; // - сумма стоимости всех позиций в заказе

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public void setDatetime(LocalDateTime datetime) {
        this.datetime = datetime;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    @Override
    public String toString() {
        return  "shopId='" + shopId + '\'' +
                ", orderId='" + orderId + '\'' +
                ", customerId='" + customerId + '\'' +
                ", datetime=" + datetime +
                ", items=" + "\n" + items;
    }
}
