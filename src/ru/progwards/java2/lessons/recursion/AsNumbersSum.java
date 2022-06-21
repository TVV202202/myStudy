package ru.progwards.java2.lessons.recursion;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static java.lang.Math.min;

public class AsNumbersSum {

    public static String asNumbersSum(int number) {
        String result="";
        List<String> list1 = new ArrayList<>();
        numStr(5, list1, "");
        result += list1.get(list1.size()-1);
        for (int i= list1.size()-2; i>=0; i--){
            String s=String.join("+", list1.get(i).split(""));
            result = result + " = " + s;
        }
        return result;
    }

    static void numStr(int number, List<String> list, String s){
        if (number == 0) {
            list.add(s);
        } else {
            for (int i = 1; i <= number; i++) {
                if (s.length()==0 || Integer.parseInt(s.substring(s.length()-1))>=i) {
                    numStr(number - i,list, s + i);
                }
            }
        }
    }
    public static void main(String[] args) {

        //System.out.println(asNumbersSum(5));
        List<String> list1 = new ArrayList<>();
        //numberSum(5, 5, "");
        //numStr(5, list1, "");
        //System.out.println(list1);
        System.out.println(asNumbersSum(5));
    }
}
