package ru.progwards.java2.lessons.generics;

import java.util.ArrayList;

public class FruitBox<E extends Fruit> extends ArrayList<E> {
    private final Class<E> typeParam;

    public Class<E> getTypeParam() {
        return typeParam;
    }
    public FruitBox(Class<E> typeParam) {
        this.typeParam = typeParam;
    }

    // победить пустую корзину так и не смог
    @Override
    public boolean add(E fruit) {
        return super.add(fruit);
    }

    public float getWeight() {
        if (size() == 0) {
            return size() * 0f;
        } else {
            return size() * this.get(0).getWeight();
            }
    }

    public void moveTo(FruitBox newBox) throws UnsupportedOperationException  {
        if (getTypeParam().equals(newBox.getTypeParam())) {
            newBox.addAll(this);
            this.clear();
        } else {
            throw new UnsupportedOperationException("Коробки разных типов");
        }
    }

    public Integer compareTo(FruitBox<E> anotherBox){
        return Float.compare(this.getWeight(), anotherBox.getWeight());
    }

    @Override
    public String toString() {
        return "FruitBox{" +
                "modCount=" + modCount +
                '}';
    }

    public static void main(String[] args) throws Exception {
        FruitBox<Apple> boxA = new FruitBox<>(Apple.class);
//        System.out.println(boxA.getWeight());
        Apple a1 = new Apple();
        Apple a2 = new Apple();
        Orange o1 = new Orange();
        boxA.add(a1);
        boxA.add(a2);
        FruitBox<Apple> boxB = new FruitBox<>(Apple.class);
        //boxB.add(a2);
        FruitBox<Orange> boxC = new FruitBox<>(Orange.class);
 //       boxC.add(o1);
        boxA.moveTo(boxC);
        System.out.println(boxA.getWeight());
        System.out.println(boxB.getWeight());
        System.out.println(boxC.getWeight());
 //       System.out.println(boxB.compareTo(boxC));
    }

}

abstract class Fruit {
    float getWeight(){
        return 0.0f;
    }
}
class Apple extends Fruit{
    @Override
    float getWeight(){
        return 1.0f;
    }
}
class Orange extends Fruit{
    @Override
    float getWeight(){
        return 1.5f;
    }
}