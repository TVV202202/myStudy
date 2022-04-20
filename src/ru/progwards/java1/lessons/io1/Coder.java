package ru.progwards.java1.lessons.io1;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Coder {

    public static boolean log(String msg, String logName){
        try {
            FileWriter logWriter = new FileWriter(logName, true);
            try {
                logWriter.write(msg + "\n");
            }
            finally {
                logWriter.close();
            }
        }
        catch (IOException e){
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }


    public static void codeFile(String inFileName, String outFileName, char[] code, String logName){
        try {
            FileReader reader = new FileReader(inFileName);
            FileWriter writer = new FileWriter(outFileName);
            try{
                for(int ch;(ch= reader.read())>=0;){
                    writer.write(code[(int) ch]);
                }
            }
            catch (Exception e){
                log(e.getMessage(), logName);
            }
            reader.close();
            writer.close();
        } catch (Exception e) {
            log(e.getMessage(), logName);
        }
    }

    public static void main(String[] args) {
        char[] chars = {1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,
                0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,01,2,3,4,5,6,7,8,9,0,1,2,3,4,
                0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,01,2,3,4,5,6,7,8,9,0,1,2,3,4,
                0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,01,2,3,4,5,6,7,8,9,0,1,2,3,4,
                0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,01,2,3,4,5,6,7,8,9,0,1,2,3,4,
                0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,01,2,3,4,5,6,7,8,9,0,1,2,3,4,
                0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,01,2,3,4,5,6,7,8,9,0,1,2,3,4,
                0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,01,2,3,4,5,6,7,8,9,0,1,2,3,4,
                0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,01,2,3,4,5,6,7,8,9,0,1,2,3,4};
        for (int i=0; i<50; i++){
            codeFile("222.txt", "111.txt", chars, "logfile.txt");
        }
    }
}
