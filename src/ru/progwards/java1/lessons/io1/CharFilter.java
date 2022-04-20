package ru.progwards.java1.lessons.io1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class CharFilter {
    public static void filterFile(String inFileName, String outFileName, String filter) throws IOException {
        FileReader reader = new FileReader(inFileName);
        FileWriter writer = new FileWriter(outFileName);
        Scanner scanner = new Scanner(reader);
        while (scanner.hasNextLine()){
            String tempString = scanner.nextLine();
            StringBuilder stringBuilder= new StringBuilder();
            for (int i=0; i < tempString.length(); i++){
                if (filter.indexOf(tempString.charAt(i))==-1){
                    stringBuilder.append(tempString.charAt(i));
                }
            }
            writer.write(String.valueOf(stringBuilder));
        }
        reader.close();
        writer.close();
        scanner.close();
    }

    public static void main(String[] args) {
        try{
            filterFile("1.txt", "2.txt", " = \" —,-.()\"");
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
}
