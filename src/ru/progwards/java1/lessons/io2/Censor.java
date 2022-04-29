package ru.progwards.java1.lessons.io2;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class Censor {
    static class CensorException extends Exception {
        private String fileName;
        private String exeptName;

        public CensorException(String fileName, String exeptName) {
            this.exeptName = exeptName;
            this.fileName = fileName;
        }

        @Override
        public String toString() {
            return fileName + ":" + exeptName;
        }
    }

    private static String replaceStar(String word) {
        StringBuilder result = new StringBuilder();
        for (char ch : word.toCharArray()) {
            result.append("*");
        }
        return result.toString();
    }

    public static void censorFile(String inoutFileName, String[] obscene) throws CensorException {
        try {
//            RandomAccessFile raf = new RandomAccessFile(inoutFileName, "rw");
//            StringBuilder tmp=new StringBuilder();
//            StringBuilder stringBuilder=new StringBuilder();
//            for (int i=0; i<raf.length(); i++){
//                char ch = raf.readChar();
//                if (Character.isAlphabetic(ch)){
//                    tmp.append(ch);
//                }
//            }


            FileReader reader = new FileReader(inoutFileName);
            Scanner scanner = new Scanner(reader);
            StringBuilder stringBuilder = new StringBuilder();
            while (scanner.hasNext()) {
                String tmpStr = scanner.next();
                for (String el : obscene) {
                    if (tmpStr.contains(el)) {
                        tmpStr=tmpStr.replaceAll(el, replaceStar(el));
                    }
                }
                stringBuilder.append(tmpStr).append(" ");
            }
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
            reader.close();
            scanner.close();

            FileWriter writer = new FileWriter("2.txt");
            writer.write(String.valueOf(stringBuilder));
            writer.close();

        } catch (Exception e) {
            throw new CensorException(inoutFileName, e.getMessage());
        }

    }

    public static void main(String[] args) throws CensorException {
        String[] obscene = {"two", "count", "write", "day", "storey"};
        censorFile("1.txt", obscene);
    }

}
