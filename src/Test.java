import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

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

    private static int lineCount(String filename) throws IOException{
        try {
            int res=0;
            FileReader fileReader = new FileReader(filename);
            Scanner scanner = new Scanner(fileReader);
            while (scanner.hasNextLine()){
                String s = scanner.nextLine();
                res++;
            }
            fileReader.close();
            return res;
        }
        catch (IOException e){
            throw new IOException("файл не найден");
        }
    }

//    private static int lineCount(String filename) throws IOException{
//        int res = 0;
//        FileReader fileReader = new FileReader(filename);
//        Scanner scanner = new Scanner(fileReader);
//        while (scanner.hasNextLine()) {
//            String s = scanner.nextLine();
//            res++;
//        }
//        fileReader.close();
//        return res;
//
//    }



    public static void main(String[] args) throws IOException{
        //System.out.println(sqr(5));
        System.out.println(lineCount("11.txt"));
    }
}


