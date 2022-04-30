package ru.progwards.java1.lessons.collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Finder {
    public static Collection<Integer> findMinSumPair(Collection<Integer> numbers) {
        int sum = Integer.MAX_VALUE;
        Collection<Integer> result = new ArrayList<>();
        int first = 0;
        int second = 0;
        List<Integer> list = new ArrayList<>(numbers);
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i - 1) + list.get(i) < sum) {
                first = i - 1;
                second = i;
                sum = list.get(i - 1) + list.get(i);
            }
        }
        result.add(first);
        result.add(second);
        return result;

    }

    public static Collection<Integer> findLocalMax(Collection<Integer> numbers) {
        List<Integer> result = new ArrayList<>();
        List<Integer> list = new ArrayList<>(numbers);
        for (int i = 1; i < list.size() - 1; i++) {
            if (list.get(i - 1) < list.get(i) && list.get(i) > list.get(i + 1)) {
                result.add(list.get(i));
            }
        }
        return result;
    }

    public static boolean findSequence(Collection<Integer> numbers) {
        List<Integer> list = new ArrayList<>();
        List<Integer> tmpNumbers = new ArrayList<>(numbers);
        for (int i = 0; i < numbers.size(); i++) {
            list.add(i);
        }
        tmpNumbers.removeAll(list);
        return tmpNumbers.size() == 0;
    }

    public static String findSimilar(Collection<String> names) {
        List<String> tmpNames = new ArrayList<>(names);
        String name = "";
        int num = 1;
        String nameTmp = "";
        int numTmp = 1;
        for (int i = 0; i < tmpNames.size() - 1; i++) {
            nameTmp = tmpNames.get(i);
            if (nameTmp.equals(tmpNames.get(i + 1))) {
                numTmp++;
            } else {
                if (num < numTmp) {
                    num = numTmp;
                    name = nameTmp;
                }
                numTmp = 1;
            }
        }
        return name + ":" + num;
    }

    public static void main(String[] args) {
        Integer[] str = {9, 27, 4, 16, 64, 5, 25, 125, 6, 36, 216, 7, 49, 343, 8, 64, 512, 9, 81, 729, 10, 100, 1000};
        Collection<Integer> test1 = new ArrayList(List.of(str));
        Collection<Integer> test2 = findMinSumPair(test1);
        Collection<Integer> test3 = findLocalMax(test1);
//        for (int el: test3){
//            System.out.print(el + " ");
//        }
//        System.out.println(findSequence(test1));
//        for (int el: test1){
//            System.out.print(el + " ");
//        }
        String[] str1 = {"eee", "eee", "eee", "qqq", "qqq", "qqq", "r", "qqq", "ee", "eee", "vv", "jty"};
        Collection<String> test0 = new ArrayList(List.of(str1));
        System.out.println();
        System.out.println(findSimilar(test0));
    }

}
