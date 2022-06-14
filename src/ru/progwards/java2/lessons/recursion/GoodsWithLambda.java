package ru.progwards.java2.lessons.recursion;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class GoodsWithLambda {
    List<Goods> goodsList = new ArrayList<>();

    public void setGoods(List<Goods> list){
        goodsList = list;
    }
    public List<Goods> sortByName(){
        return goodsList.stream().sorted(Comparator.comparing(x -> x.name)).collect(Collectors.toList());
    }
    public List<Goods> sortByNumber(){
        return goodsList.stream().sorted(Comparator.comparing(x -> x.number.toLowerCase()))
                .collect(Collectors.toList());
    }
    public List<Goods> sortByPartNumber(){
        return goodsList.stream().sorted(Comparator.comparing(x -> x.number.toLowerCase().substring(0,3)))
                .collect(Collectors.toList());
    }
    public List<Goods> sortByAvailabilityAndNumber(){
        Comparator<Goods> tst = Comparator.comparing(x -> x.available);
        Comparator<Goods> test = tst.thenComparing(x -> x.number.toLowerCase());
        return goodsList.stream().sorted(test).collect(Collectors.toList());
    }

    public List<Goods> expiredAfter(Instant date){
        Predicate<Goods> pr = x -> x.expired.isAfter(date);
        return goodsList.stream().filter(pr).sorted(Comparator.comparing(x -> x.expired)).collect(Collectors.toList());
    }
    public List<Goods> countLess(int count){
        Predicate<Goods> pr = x -> x.available < count;
        return goodsList.stream().filter(pr).sorted(Comparator.comparing(x -> x.available)).collect(Collectors.toList());
    }
    public List<Goods> countBetween(int count1, int count2){
        Predicate<Goods> pr1 = x -> x.available > count1;
        Predicate<Goods> pr2 = x -> x.available < count2;
        Predicate<Goods> pr = pr1.and(pr2);
        return goodsList.stream().filter(pr).sorted(Comparator.comparing(x -> x.available)).collect(Collectors.toList());
    }

    public static void main(String[] args) {
        Goods g1 = new Goods("gd2","arteA",7, 6.1, Instant.now().plusMillis(7387489));
        Goods g2 = new Goods("gd1","nyer",5, 1.0, Instant.now().minusMillis(873893));
        Goods g3 = new Goods("gd5","Artea",10, 18.1, Instant.now().minusMillis(90809));
        List<Goods> test = new ArrayList<>();
        test.add(g1);
        test.add(g2);
        test.add(g3);
        GoodsWithLambda gwl = new GoodsWithLambda();
        gwl.setGoods(test);
        System.out.println(gwl.countBetween(5,10));
    }

}
