package ru.progwards.java1.lessons.datetime;

import java.util.*;

public class Profiler {
    static List<StackItem> stackItems = new ArrayList();

    static Map<String, StatisticInfo> statisticInfoMap = new TreeMap<>();
    static int duration = 0;

    public static void enterSection(String name) {
        StackItem stackitem = new StackItem(name);
        stackitem.startTime = new Date().getTime();

        stackItems.add(stackitem);
    }

    public static void exitSection(String name) {
        for (int i=stackItems.size()-1; i>=0; i--) {
            if (stackItems.get(i).name.equals(name)) {
                stackItems.get(i).durationLast=(int) (new Date().getTime() - stackItems.get(i).startTime);
                StatisticInfo statisticInfo = new StatisticInfo(name);
                statisticInfo.fullTime = stackItems.get(i).durationLast;

                //statisticInfo.selfTime = statisticInfo.fullTime - (i-1>=0 ? stackItems.get(i-1).durationLast : 0);
                //duration = tempDeque.pollLast().durationLast;
                //statisticInfo.selfTime = statisticInfo.fullTime - (int) Objects.requireNonNull(tempDeque.pollLast()).durationLast;

                if (statisticInfoMap.containsKey(statisticInfo.sectionName)) {
                    statisticInfoMap.get(statisticInfo.sectionName).fullTime += statisticInfo.fullTime;
                    statisticInfoMap.get(statisticInfo.sectionName).selfTime += statisticInfo.selfTime;
                    statisticInfoMap.get(statisticInfo.sectionName).count += 1;
                } else {
                    statisticInfoMap.put(statisticInfo.sectionName, statisticInfo);
                }
                stackItems.remove(i);
                break;

            }
        }
        if (stackItems.size() == 0) duration = 0;
    }

    public static List<StatisticInfo> getStatisticInfo() {
        List<StatisticInfo> statisticInfoList = new ArrayList<>();
        for (String el : statisticInfoMap.keySet()) {
            statisticInfoList.add(statisticInfoMap.get(el));
        }
        return statisticInfoList;
    }

    static class StackItem {
        String name;
        public long startTime;
        int durationLast;

        public StackItem(String name) {
            this.name = name;
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
