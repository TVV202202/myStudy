package ru.progwards.java1.lessons.maps;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.*;

public class UsageFrequency {
    String fileString = "";

    public void processFile(String fileName) {
        try {
            StringBuilder tmpStr = new StringBuilder();
            FileReader reader = new FileReader(fileName);
            Scanner scanner = new Scanner(reader);
            while (scanner.hasNextLine())
                tmpStr.append(scanner.nextLine());
            fileString = tmpStr.toString();
        } catch (IOException e) {
            System.out.println("file not found");
        }
    }

    public Map<Character, Integer> getLetters() {
        Map<Character, Integer> map = new HashMap<>();
        for (char ch : fileString.toCharArray()) {
            if (Character.isAlphabetic(ch) || Character.isDigit(ch)) {
                map.putIfAbsent(ch, 0);
                map.put(ch, map.get(ch) + 1);
            }
        }
        return map;
    }

    public Map<String, Integer> getWords() {
        Map<String, Integer> map = new HashMap<>();
        String[] tmp;
        tmp = fileString.split("\\W+");
        //  \ |\“|\.|\,|\!|\@|\?|"
        for (String el : tmp) {
            map.putIfAbsent(el, 0);
            map.put(el, map.get(el) + 1);
        }
        map.remove("");
        return map;
    }

    public static void main(String[] args) {
        UsageFrequency usageFrequency = new UsageFrequency();
        usageFrequency.processFile("wiki.test.tokens");
        System.out.println(usageFrequency.getLetters());
        System.out.println(usageFrequency.getWords());
    }
}
