package ru.progwards.java1.lessons.maps;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class FiboMapCache {
    private static Map<Integer, BigDecimal> fiboCache = new HashMap<>();
    boolean cacheOn;
    public FiboMapCache(boolean cacheOn){
        this.cacheOn = cacheOn;
    }
    public BigDecimal fiboNumber(int n){
        if (cacheOn){
            //if (!fiboCache.containsKey(n))
            fiboCache.put(n,fibNew(n));
            return fiboCache.get(n);
        }
        else{
            BigDecimal res=fib(n);
            fiboCache.put(n, res);
            return res;
        }
    }

    static BigDecimal fib(int n) {
        if (n <= 2) return BigDecimal.ONE;
        BigDecimal prev = BigDecimal.ONE, prevOfPrev = BigDecimal.ONE;
        BigDecimal res = null;
        for (int i = 2; i < n; i++) {
            res = prev.add(prevOfPrev);
            prevOfPrev = prev;
            prev = res;
        }
        return res;
    }

    static BigDecimal fibNew(int n) {
        if (n <= 2) return BigDecimal.ONE;
        if (fiboCache.containsKey(n)){
            return fiboCache.get(n);
        }
        else
            return fibNew(n-1).add(fibNew(n-2));
    }

    public void clearCahe(){
        fiboCache.clear();
        fiboCache.put(null,null);
    }
    public static void test(){
        FiboMapCache fiboMapCache1 = new FiboMapCache(true);
        long startTime = new Date().getTime();
        for (int i=1; i<=1000; i++){
            BigDecimal fibo1 = fiboMapCache1.fiboNumber(i);
        }
        long t1 = new Date().getTime() - startTime;
        System.out.println("fiboNumber cacheOn="+fiboMapCache1.cacheOn+" время выполнения "+t1);

        FiboMapCache fiboMapCache2 = new FiboMapCache(false);
        startTime = new Date().getTime();
        for (int i=1; i<=1000; i++){
            BigDecimal fibo2 = fiboMapCache2.fiboNumber(i);
        }
        long t2 = new Date().getTime() - startTime;
        System.out.println("fiboNumber cacheOn="+fiboMapCache2.cacheOn+" время выполнения "+t2);

    }

    public static void main(String[] args) {
        Map<Integer,BigDecimal> fiboCache;
        fiboCache = Map.of(1,BigDecimal.ONE,2,BigDecimal.ONE,23, new BigDecimal(28657), 24, new BigDecimal(46368));
        test();
    }
}
