package ru.progwards.java1.lessons.io1;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class LineCount {

    public static int calcEmpty(String fileName) {
        try {
            int res=0;
            FileReader fileReader = new FileReader(fileName);
            Scanner scanner = new Scanner(fileReader);
            while (scanner.hasNextLine()){
                if (scanner.nextLine().equals("")){
                    res++;
                }
            }
            scanner.close();
            fileReader.close();
            return res;
        }
        catch (IOException e){
            return -1;
        }
    }

    public static void main(String[] args){
        //System.out.println(sqr(5));
        System.out.println(calcEmpty("222.txt"));
    }
}
