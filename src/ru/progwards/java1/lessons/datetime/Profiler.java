package ru.progwards.java1.lessons.datetime;

import java.util.*;

public class Profiler {
    static List<StackItem> stackItems = new ArrayList();

    static Map<String, StatisticInfo> statisticInfoMap = new TreeMap<>();
    static int duration = 0;

    public static void enterSection(String name) {
        StackItem stackitem = new StackItem();
        stackItems.add(stackitem);
    }

    public static void exitSection(String name) {
        StackItem si = stackItems.get(stackItems.size() - 1);
        si.fullTime = (int) (new Date().getTime() - si.startTime);
        si.selfTime = si.selfTime + si.fullTime;
        stackItems.remove(si);
        //вычесть из всех  остальных в стеке
        for (int i = stackItems.size() - 1; i >= 0; i--)
        {
            StackItem cur = stackItems.get(i);
            cur.selfTime = cur.selfTime - si.selfTime;
        }

        if (statisticInfoMap.containsKey(name)) {
            statisticInfoMap.get(name).fullTime += si.fullTime;
            statisticInfoMap.get(name).selfTime += si.selfTime;
            statisticInfoMap.get(name).count++;
        } else {
            statisticInfoMap.put(name, StatisticInfo.update(name, si));
        }

    }


    public static List<StatisticInfo> getStatisticInfo() {
        List<StatisticInfo> statisticInfoList = new ArrayList<>();
        for (String el : statisticInfoMap.keySet()) {
            statisticInfoList.add(statisticInfoMap.get(el));
        }
        return statisticInfoList;
    }

    static class StackItem {
        public long startTime;
        public int fullTime;// полное время выполнения секции в миллисекундах.
        public int selfTime;// чистое время выполнения секции в миллисекундах.

        public StackItem() {
            startTime = new Date().getTime();
            selfTime = 0;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        enterSection("1");
        Thread.sleep(10);

        for (int i = 0; i < 5; i++) {

            enterSection("3");
            Thread.sleep(30);

            enterSection("5");
            Thread.sleep(50);
            exitSection("5");

            exitSection("3");

        }

        enterSection("4");
        Thread.sleep(40);
        exitSection("4");

        exitSection("1");
        for (StatisticInfo el : getStatisticInfo())
            System.out.println(el.sectionName + " " + el.fullTime + " " + el.selfTime + " " + el.count);
    }

}
