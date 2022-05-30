package ru.progwards.java1.lessons.files;

public class OrderItem {
    public String googsName; // - наименование товара
    public int count; // - количество
    public double price; // - цена за единицу

    public void setGoogsName(String googsName) {
        this.googsName = googsName;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return  "googsName='" + googsName + '\'' +
                ", count=" + count +
                ", price=" + price +
                '\n';
    }
}
