package ru.progwards.java1.lessons.sets;

import java.util.HashSet;
import java.util.Set;

public class SetOperations {
    public static Set<Integer> union(Set<Integer> set1, Set<Integer> set2) {
        Set<Integer> uniSet = new HashSet<>(set1);
        uniSet.addAll(set2);
        return uniSet;
    }

    public static Set<Integer> intersection(Set<Integer> set1, Set<Integer> set2) {
        Set<Integer> uniSet = new HashSet<>(set1);
        uniSet.retainAll(set2);
        return uniSet;
    }

    public static Set<Integer> difference(Set<Integer> set1, Set<Integer> set2) {
        Set<Integer> uniSet = new HashSet<>(set1);
        uniSet.removeAll(set2);
        return uniSet;
    }

    public static Set<Integer> symDifference(Set<Integer> set1, Set<Integer> set2) {
        Set<Integer> uniSet = new HashSet<>(set1);
        uniSet = difference(union(set1, set2), intersection(set1, set2));
        return uniSet;
    }

    public static void main(String[] args) {
        Set<Integer> intSet1 = Set.of(1, 2, 3, 4, 5);
        Set<Integer> intSet2 = Set.of(3, 4, 5, 6, 7, 8, 9);
        System.out.println(union(intSet1, intSet2));
    }
}
