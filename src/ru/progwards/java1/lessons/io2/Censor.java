package ru.progwards.java1.lessons.io2;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

public class Censor {
    static class CensorException extends Exception{
        private String fileName;
        private String exeptName;
        //public String getFileName(){return fileName;}
        public CensorException(String exeptName, String fileName){
            this.exeptName = exeptName;
            this.fileName = fileName;
        }
        @Override
        public String toString(){
            return fileName + ": " + exeptName;
        }
    }
    private static String replaceStar(String word){
        StringBuilder result = new StringBuilder();
        for (char ch: word.toCharArray()){
            result.append("*");
        }
        return result.toString();
    }
    public static void censorFile(String inoutFileName, String[] obscene) {
        try {
            FileReader reader = new FileReader(inoutFileName);
            Scanner scanner = new Scanner(reader);
            StringBuilder stringBuilder = new StringBuilder();
            while (scanner.hasNext()){
                String tmpStr = scanner.next();
                boolean flag = true;
                for (String el: obscene){
                    if (tmpStr.contains(el)){
                        stringBuilder.append(tmpStr.replaceAll(el, replaceStar(el))).append(" ");
                        flag =false;
                        break;
                    }
                }
                if (flag){
                    stringBuilder.append(tmpStr).append(" ");
                }
            }
            stringBuilder.deleteCharAt(stringBuilder.length()-1);
            reader.close();
            scanner.close();
            FileWriter writer = new FileWriter(inoutFileName);
            writer.write(String.valueOf(stringBuilder));
            writer.close();

        } catch (Exception e) {
            try {
                throw new CensorException(e.getMessage(), inoutFileName);
            } catch (CensorException ex) {
                System.out.println(ex);
            }
        }

    }

    public static void main(String[] args) {
        String[] obscene = {"Java", "Oracle", "Sun", "Microsystems"};
        censorFile("3.txt", obscene);
    }

}
