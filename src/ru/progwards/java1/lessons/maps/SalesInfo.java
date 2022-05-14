package ru.progwards.java1.lessons.maps;

import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.*;

public class SalesInfo {
    ArrayList<Object> arrayList = new ArrayList<>();

    public void LoadFiles(String fileName) {
        try {
            FileReader reader = new FileReader(fileName);
            Scanner scanner = new Scanner(reader);
            while (scanner.hasNextLine()) {
                ArrayList<Object> tmpString = new ArrayList<>(List.of(scanner.nextLine().split("\\,|\\;")));
                if (tmpString.size() == 4) {
                    try {
                        tmpString.set(2, Integer.valueOf(((String) tmpString.get(2)).strip()));
                        tmpString.set(3, Double.valueOf(((String) tmpString.get(3)).strip()));
                        arrayList.add(tmpString);
                    } catch (NumberFormatException ignored) {
                    }
                }
            }
            scanner.close();
            reader.close();
        } catch (IOException e) {
            System.out.println("file not found");
        }
    }

    public int loadOrders(String fileName) {
        LoadFiles(fileName);
        return arrayList.size();
    }

    public Map<String, Double> getGoods() {
        Map<String, Double> map = new TreeMap<>();
        for (Object el : arrayList) {
            ArrayList tmpArrayList = (ArrayList) el;
            String tmpEl = (String) tmpArrayList.get(1);
            Double tmpSum = (Double) tmpArrayList.get(3);
            map.putIfAbsent(tmpEl, (double) 0);
            map.put(tmpEl, map.get(tmpEl) + tmpSum);
        }
        return map;
    }

    public Map<String, AbstractMap.SimpleEntry<Double, Integer>> getCustomers() {
        Map<String, AbstractMap.SimpleEntry<Double, Integer>> map = new TreeMap<>();
        for (Object el : arrayList) {
            ArrayList tmpArrayList = (ArrayList) el;
            String fio = (String) tmpArrayList.get(0);
            double sumBye = 0.0;
            int colBue = 0;
            for (Object entry : arrayList) {
                ArrayList tmp = (ArrayList) entry;
                if (((String) tmp.get(0)).equals(fio)) {
                    sumBye += (Double) tmp.get(3);
                    colBue += (Integer) tmp.get(2);
                }
            }
            AbstractMap.SimpleEntry<Double, Integer> byeEntry = new AbstractMap.SimpleEntry<>(sumBye, colBue);
            map.putIfAbsent(fio, byeEntry);
        }
        return map;
    }

    public static void main(String[] args) {
        SalesInfo salesInfo = new SalesInfo();
        int num = salesInfo.loadOrders("123.txt");
        System.out.println(num);
        System.out.println(salesInfo.getGoods());
        System.out.println(salesInfo.getCustomers());
    }
}
