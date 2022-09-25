package ru.progwards.java2.lessons.generics;

import java.util.ArrayList;

public class FruitBox<Fruit> extends ArrayList<Fruit> {

    @Override
    public boolean add(Fruit fruit) {
        if (this.size() == 0) {
            return super.add(fruit);
        }
        else if (this.get(0).getClass().getSimpleName().equals(fruit.getClass().getSimpleName())){
            return super.add(fruit);
        }
        return false;
//                return super.add(fruit);
    }


    void addAll(ArrayList <? extends Fruit> items){
        for (Fruit item : items) {
            this.add(item);
        }
    }

//    public boolean remove(Object fruit){
//        return super.remove(fruit);
//    }

    public float getWeight() {
        if (this.size() == 0) {
            return modCount * 0f;
        } else if (this.get(0).getClass().getSimpleName().equals("Apple")) {
            // уродливая конструкция на мой взгляд, но больше ничего не смог придумать
            return modCount * 1.0f;
            }
            if (this.get(0).getClass().getSimpleName().equals("Orange")) {
                return modCount * 1.5f;
            }
        return modCount * 0f;
    }

    public void moveTo(FruitBox newBox) {
//        if (this.get(0).getClass().getSimpleName().equals(newBox.get(0).getClass().getSimpleName())){
//            for (int i=0; i<this.size(); i++){
//                if (!newBox.add(this.get(i))){
//                    throw new UnsupportedOperationException();
//                }
//            }
//        this.clear();
//        }
//        else{
//            throw new UnsupportedOperationException();
//        }
//        return this;

        // никак не могу обойти пересыпание яблок в пустую корзину для апельсинов.
        for (int i=0; i<this.size(); i++){
             if (!newBox.add(this.get(i))){
                 throw new UnsupportedOperationException();
             }
        }
        this.clear();
    }

    public Integer compareTo(FruitBox anotherBox){
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
        System.out.println(boxA.getWeight());
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
        System.out.println(boxB.getWeight());
        System.out.println(boxC.getWeight());
        System.out.println(boxB.compareTo(boxC));
    }

}

abstract class Fruit {
}
class Apple extends Fruit{
}
class Orange extends Fruit{
}