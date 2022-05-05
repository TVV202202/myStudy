package ru.progwards.java1.lessons.sets;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class LettersInFile {
    public static String process(String fileName) throws IOException {
        TreeSet<Character> res = new TreeSet<>();
        FileReader reader = new FileReader(fileName);
        Scanner scanner = new Scanner(reader);
        for (int ch; (ch = reader.read()) >= 0; ) {
            if (Character.isAlphabetic((char) ch)) {
                res.add((char) ch);
            }
        }
        StringBuilder result = new StringBuilder();
        for (char el : res) {
            result.append(el);
        }
        return String.valueOf(result);
    }

    public static void main(String[] args) throws IOException {
        System.out.println(process("222.txt"));
    }
}
