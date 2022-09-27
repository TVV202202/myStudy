package ru.progwards.java2.lessons.generics;

import java.util.ArrayList;

public class FruitBox<E extends Fruit> extends ArrayList<E> {
// победить пустую корзину так и не смог
    @Override
    public boolean add(E fruit) {
        if (this.size() == 0) {
            return super.add(fruit);
        }
        else if (this.get(0).getClass().getSimpleName().equals(fruit.getClass().getSimpleName())){
            return super.add(fruit);
        }
        return false;
//        return super.add(fruit);
    }


    public float getWeight() {
        if (this.size() == 0) {
            return modCount * 0f;
        } else {
            return modCount * this.get(0).weightF();
            }
    }

    public void moveTo(FruitBox<E> newBox) {
        for (int i=0; i<this.size();i++) {
            if (!newBox.add(this.get(i))) {
                throw new UnsupportedOperationException();
            }
        }
        this.clear();
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

    public static void main(String[] args) {
        FruitBox<Apple> boxA = new FruitBox<>();
//        System.out.println(boxA.getWeight());
        Apple a1 = new Apple();
        Apple a2 = new Apple();
        Orange o1 = new Orange();
        boxA.add(a1);
        boxA.add(a2);
        FruitBox<Apple> boxB = new FruitBox<>();
        boxB.add(a2);
        FruitBox<Orange> boxC = new FruitBox<>();
        boxC.add(o1);
        boxA.moveTo(boxB);
        System.out.println(boxA.getWeight());
        System.out.println(boxB.getWeight());
        System.out.println(boxC.getWeight());
 //       System.out.println(boxB.compareTo(boxC));
    }

}

abstract class Fruit {
    float m;
    float weightF(){
        return m;
    }
}
class Apple extends Fruit{
    float m;
    @Override
    float weightF(){
        return m=1.0f;
    }
}
class Orange extends Fruit{
    float m;
    @Override
    float weightF(){
        return m=1.5f;
    }
}