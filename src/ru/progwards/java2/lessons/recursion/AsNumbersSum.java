package ru.progwards.java2.lessons.recursion;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static java.lang.Math.min;

public class AsNumbersSum {

//    public static String asNumbersSum(int number) {
//        String result="";
//        List<String> list1 = new ArrayList<>();
//        numStr(5, list1, "");
//        result += list1.get(list1.size()-1);
//        for (int i= list1.size()-2; i>=0; i--){
//            String s=String.join("+", list1.get(i).split(""));
//            result = result + " = " + s;
//        }
//        return result;
//    }
//
//    static void numStr(int number, List<String> list, String s){
//        if (number == 0) {
//            list.add(s);
//        } else {
//            for (int i = 1; i <= number; i++) {
//                if (s.length()==0 || Integer.parseInt(s.substring(s.length()-1))>=i) {
//                    numStr(number - i,list, s + i);
//                }
//            }
//        }
//    }


    public static String asNumbersSum(int number)
    //, который раскладывает параметр number, как всевозможные уникальные комбинации сумм натуральных чисел,
    // например: 5 = 4+1 = 3+2 = 3+1+1 = 2+2+1 = 2+1+1+1 = 1+1+1+1+1
//  Строка должна содержать результат, отформатированный точно, как указано в примере.
//  Повторные комбинации не допускаются, например, если а строке уже есть 3+2, то 2+3 там быть не должно.
//  Задача должна быть решена методом рекурсии, циклы использовать запрещено.
    {
        return number + onlyHalf(number - 1, 1, "");
    }

    public static String onlyHalf(int number, int half, String res)
    {
        if (number <= 0)
            return "";

//чтобы без повторов, половина возможных переборов
        if (half > number)
            return onlyHalf(number, half - number, res + number + "+") +
                    onlyHalf(number - 1, half + 1, res);
        else {
            return " = " + res + number + "+" + half +
                    onlyHalf(half - 1, 1, res + number + "+") +
                    onlyHalf(number - 1, half + 1, res);

        }
    }


    public static void main(String[] args) {


        System.out.println(asNumbersSum(7));
    }

}
