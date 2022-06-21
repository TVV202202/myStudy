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
    public List<Goods> sortByName(){ // список, отсортированный по наименованию
        return goodsList.stream().sorted(Comparator.comparing(x -> x.name)).collect(Collectors.toList());
    }
    public List<Goods> sortByNumber(){ // список, отсортированный по артикулу, без учета регистра
        return goodsList.stream().sorted(Comparator.comparing(x -> x.number.toLowerCase()))
                .collect(Collectors.toList());
    }
    public List<Goods> sortByPartNumber(){ //  список, отсортированный по первым 3-м символам артикула, без учета регистра
        return goodsList.stream().sorted(Comparator.comparing(x -> x.number.toLowerCase().substring(0,3)))
                .collect(Collectors.toList());
    }
    public List<Goods> sortByAvailabilityAndNumber(){//список, отсортированный по количеству, а для одинакового количества, по артикулу, без учета регистра
        //Comparator<Goods> tst = Comparator.comparing(x -> x.available);
        //Comparator<Goods> test = tst.thenComparing(x -> x.number.toLowerCase());
        return goodsList.stream()
                .sorted(Comparator.comparing(x -> x.number.toLowerCase()))
                .sorted(Comparator.comparing(x -> x.available))
                .collect(Collectors.toList());
    }

    public List<Goods> expiredAfter(Instant date){ // список, с товаром, который будет просрочен после указанной даты, отсортированный по дате годности
        //Predicate<Goods> pr = x -> x.expired.isAfter(date);
        return goodsList.stream()
                .sorted(Comparator.comparing(x -> x.expired))
                .filter(x -> x.expired.isAfter(date))
                .collect(Collectors.toList());
    }
    public List<Goods> countLess(int count){ //список, с товаром, количество на складе которого меньше указанного, отсортированный по количеству
        //Predicate<Goods> pr = x -> x.available < count;
        return goodsList.stream()
                .sorted(Comparator.comparing(x -> x.available))
                .filter(x -> x.available < count)
                .collect(Collectors.toList());
    }
    public List<Goods> countBetween(int count1, int count2){ //список, с товаром, количество на складе которого больше count1 и меньше count2, отсортированный по количеству
//        Predicate<Goods> pr1 = x -> x.available > count1;
//        Predicate<Goods> pr2 = x -> x.available < count2;
//        Predicate<Goods> pr = pr1.and(pr2);
        return goodsList.stream()
                .sorted(Comparator.comparing(x -> x.available))
                .filter(x -> x.available > count1)
                .filter(x -> x.available < count2)
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        Goods g1 = new Goods("gd2","arteA",7, 6.1, Instant.now().plusMillis(7387489));
        Goods g2 = new Goods("gd1","nyer",5, 1.0, Instant.now().minusMillis(8738933));
        Goods g3 = new Goods("gd5","Artea",10, 18.1, Instant.now().minusMillis(9080912));
        List<Goods> test = new ArrayList<>();
        test.add(g1);
        test.add(g2);
        test.add(g3);
        GoodsWithLambda gwl = new GoodsWithLambda();
        gwl.setGoods(test);
        //System.out.println(gwl.sortByAvailabilityAndNumber());
        System.out.println(gwl.countBetween(5,7));
    }

}
