package ru.progwards.java1.lessons.queues;

import java.util.*;

public class CollectionsSort {
    public static void mySort(Collection<Integer> data) {
        List<Integer> tmpList = new ArrayList<>(data);
        int tmp;
        for (int i = 0; i < tmpList.size(); i++) {
            for (int j = i + 1; j < tmpList.size(); j++) {
                if (tmpList.get(i) > tmpList.get(j)) {
                    tmp = tmpList.get(i);
                    tmpList.set(i, tmpList.get(j));
                    tmpList.set(j, tmp);
                }
            }
        }
        data.clear();
        data.addAll(tmpList);
    }

    public static void minSort(Collection<Integer> data) {
        int tmp;
        Collection<Integer> tmpCol = new ArrayList<>();
        while (data.size() != 0) {
            tmp = Collections.min(data);
            tmpCol.add(tmp);
            data.remove(tmp);
        }
        data.addAll(tmpCol);
    }

    static void collSort(Collection<Integer> data) {
        List<Integer> tmpList = new ArrayList<>(data);
        Collections.sort(tmpList);
        data.clear();
        data.addAll(tmpList);
    }

    public static Collection<String> compareSort() {
        final int ELEMENTS_COUNT = 50_000;
        long startTime;
        List<Integer> arrayList = new ArrayList();
        for (int i = 0; i < ELEMENTS_COUNT; i++) {
            arrayList.add(i);
        }
        Collections.shuffle(arrayList);

        Collection<Integer> test1 = arrayList;
        Collection<Integer> test2 = arrayList;
        Collection<Integer> test3 = arrayList;

        long t1;
        long t2;
        long t3;
        startTime = new Date().getTime();
        mySort(test1);
        t1 = (new Date().getTime() - startTime);

        startTime = new Date().getTime();
        minSort(test2);
        t2 = (new Date().getTime() - startTime);

        startTime = new Date().getTime();
        collSort(test3);
        t3 = (new Date().getTime() - startTime);
        // жаль не проходили словари или была бы функция sort для многомерного массива...
        // приходится придумывать костыль,
        // больше ничего в голову не пришло (или куча if)
        Comparator<Pair> pcomp = new PairTimeComparator().thenComparing(new PairNameComparator());
        TreeSet<Pair> tmpSet = new TreeSet<>(pcomp);

        tmpSet.add(new Pair(t2, "minSort"));
        tmpSet.add(new Pair(t3, "collSort"));
        tmpSet.add(new Pair(t1, "mySort"));
        String[] tmpArr = new String[3];
        int i = 0;
        for (Pair el : tmpSet) {
            tmpArr[i] = el.name;
            i++;
        }
        Collection<String> result = List.of(tmpArr);
        return result;
    }

    public static class Pair {
        long time;
        String name;

        public Pair(long time, String name) {
            this.time = time;
            this.name = name;
        }
    }

    static class PairNameComparator implements Comparator<Pair> {
        public int compare(Pair a, Pair b) {
            return a.name.compareTo(b.name);
        }
    }

    static class PairTimeComparator implements Comparator<Pair> {
        public int compare(Pair a, Pair b) {
            return Long.compare(a.time, b.time);
        }
    }

    public static void main(String[] args) {
        Collection<Integer> test = new ArrayList<>(List.of(2, 6, 1, 8, 3, 0, 3));
        Collection<Integer> test1 = new ArrayList<>();
//        minSort(test1);
//        System.out.println(test1);
        System.out.println(compareSort());


    }

}
