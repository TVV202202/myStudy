package ru.progwards.java1.lessons.datetime;

public class StatisticInfo {
    public String sectionName; //- имя секции
    public int fullTime; // - полное время выполнения секции в миллисекундах.
    public int selfTime; // - чистое время выполнения секции в миллисекундах.
                        // Для вложенных секций, из времени выполнения внешней секции нужно вычесть времена выполнения вложенных секций.
    public int count;   // - количество вызовов. В случае, если вызовов более одного, fullTime и selfTime
                        // содержат суммарное время выполнения всех вызовов.

    static StatisticInfo update(String name, Profiler.StackItem item)
    {
        StatisticInfo res = new StatisticInfo();
        res.sectionName = name;
        res.fullTime = item.fullTime;
        res.selfTime = item.selfTime;
        res.count = 1;
        return res;
    }

}
