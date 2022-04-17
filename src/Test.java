import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Test {

    public static Integer sqr(Integer n){
        try{
            return n*n;
        }
        catch (NullPointerException e){
            return -1;
        }
    }

    public static String test(String filename){
        try {
            if (filename==null)
                throw new IOException();
            return "File processing";
        } catch (IOException e){
            System.out.print(e);
            return " File not found";
        }
    }

    public static void main(String[] args) {
        //System.out.println(sqr(5));
        System.out.println(test(null));
    }
}


