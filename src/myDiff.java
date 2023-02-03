import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class myDiff {
    // константы для цвета
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    // разбиение файла на строки
    public static ArrayList<String> partitionOnLines(String fileName) {
        ArrayList<String> setOfLines = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader(fileName);
            Scanner scanner = new Scanner(fileReader);
            while (scanner.hasNextLine()) {
                setOfLines.add(scanner.nextLine());
            }

            scanner.close();
            fileReader.close();
            return setOfLines;
        } catch (IOException e) {
            return setOfLines;
        }
    }
    // разбиение файла на слова
    public static ArrayList<String> partitionFileOnWords(String fileName) {
        ArrayList<String> setOWords = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader(fileName);
            Scanner scanner = new Scanner(fileReader);
            String tmp;
            while (scanner.hasNext()) {
                StringTokenizer tokenizer = new StringTokenizer(scanner.next(), " \t\n\r,.", true);
                while (tokenizer.hasMoreElements()) {
                    setOWords.add(tokenizer.nextToken());
                }
            }
            scanner.close();
            fileReader.close();
            return setOWords;
        } catch (IOException e) {
            return setOWords;
        }
    }
    // разбиение строки на слова
    public static ArrayList<String> partitionOnWords(String line) {
        ArrayList<String> setOfWords = new ArrayList<>();
        if (line.length() != 0) {
//            StringTokenizer tokenizer = new StringTokenizer(line, " ", true);
//            while (tokenizer.hasMoreElements()) {
//                setOfWords.add(tokenizer.nextToken());
//            }
            String[] words = line.split("\\s");
            System.out.println(Arrays.toString(words));

        }
        return setOfWords;
    }

    // разбиение текста на символы
    public static ArrayList<Character> partitionOnLetters(String word) {
        ArrayList<Character> setOfLetters = new ArrayList<>();
        if (word.length() != 0) {
            for (int i = 0; i < word.length(); i++) {
                setOfLetters.add(word.charAt(i));
            }
        }
        return setOfLetters;
    }

    // матрица размера i+1*j+1 с 0 значениями
    public static int[][] myMatrix(int row, int column) {
        int[][] matrix = new int[row+1][column+1];
        for (int i = 0; i <= row; i++) {
            for (int j = 0; j <= column; j++) {
                matrix[i][j] = 0;
            }
        }
        return matrix;
    }
    // печать матрицы
    public static String printMatrix(int[][] matrix) {
        StringBuilder tmp = new StringBuilder();
        for (int[] ints : matrix) {
            for (int anInt : ints) {
                tmp.append(anInt).append(";");
            }
            tmp.append(System.lineSeparator());
        }
        return tmp.toString();
    }
    // заполнение матрицы наибольшей общей последовательности
    public static <T> int[][] NOPMatrix(ArrayList<T> a, ArrayList<T> b) {
        int l1 = a.size();
        int l2 = b.size();
        int[][] matr = myMatrix(l1,l2);
        for (int i = l1-1; i >=0; i--) {
            for (int j = l2-1; j >=0; j--) {
                if (a.get(i).equals(b.get(j))) {
                    matr[i][j] = matr[i + 1][j + 1] + 1;
                } else {
                    matr[i][j] = Math.max(matr[i+1][j], matr[i][j+1]);
                }
            }
        }
        return matr;
    }
    // наибольшая общая последовательность
    // общий текст и разница в 1-м и 2-м
    public static <T> ArrayList<List<T>> arrayWithText(ArrayList<T> a, ArrayList<T> b){
        int[][] matrix = NOPMatrix(a, b);
        ArrayList<T> overallText = new ArrayList<>();
        ArrayList<T> diffText1 = new ArrayList<>();
        ArrayList<T> diffText2 = new ArrayList<>();
        int i=0;
        int j=0;
        int k=0; // счетчик
        int l1 = a.size();
        int l2 = b.size();

        while (matrix[i][j]!=0 && i<l1 && j<l2){
            overallText.add((T) "");
            diffText1.add((T) "");
            diffText2.add((T) "");
            if (a.get(i).equals(b.get(j))){
                overallText.set(k, a.get(i));
                i++;
                j++;
            } else if (matrix[i][j] == matrix[i+1][j]){
                diffText1.set(k,a.get(i));
                i++;
            }
            else{
                diffText2.set(k,b.get(j));
                j++;
            }
            k++;
        }
        for (int m=i; m<l1; m++){
            overallText.add((T) "");
            diffText1.add(a.get(m));
            diffText2.add((T) "");
            k++;
        }
        for (int m=j; m<l2; m++){
            overallText.add((T) "");
            diffText1.add((T) "");
            diffText2.add(b.get(m));
            k++;
        }

        ArrayList<List<T>> qqq = new ArrayList<>();
        qqq.add(overallText);
        qqq.add(diffText1);
        qqq.add(diffText2);
        return qqq;
    }

// вывод в файл
    public static void outFile(List<String> outText){
        try (FileWriter fileWriter = new FileWriter("test.txt", false)) {
            for (String elem : outText) { // Перебираем элементы массива
                fileWriter.append(elem) // Записываем элементы в файл
                        .append(System.lineSeparator()); // Добавляем перевод строки между элементами
                fileWriter.flush(); // Очищаем буфер потока
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void main(String[] args) {

        ArrayList<String> t1 = new ArrayList<>(partitionOnLines("222.txt"));
        ArrayList<String> t2 = new ArrayList<>(partitionOnLines("223.txt"));
        ArrayList<String> test2 = new ArrayList<>(partitionOnWords(t1.get(1)));
        ArrayList<String> test21 = new ArrayList<>(partitionOnWords(t1.get(3)));
        ArrayList<Character> test3 = new ArrayList<>(partitionOnLetters(test2.get(0)));
        ArrayList<String> test4 = new ArrayList<>(partitionOnWords(t1.get(2)));
        ArrayList<String> test5 = new ArrayList<>(partitionOnWords(t1.get(4)));
        ArrayList<Character> test6 = new ArrayList<>(partitionOnLetters(test4.get(0)));
        ArrayList<Character> test7 = new ArrayList<>(partitionOnLetters(test5.get(0)));
        ArrayList<String> t3 = new ArrayList<>(partitionFileOnWords("222.txt"));
        ArrayList<String> t4 = new ArrayList<>(partitionFileOnWords("223.txt"));

//       System.out.println(printMatrix(NOPMatrixNew(test2, test21)));
        System.out.println(printMatrix(NOPMatrix(t1, t2)));
//       System.out.println(NOP(test2, test21));

//        ArrayList<List<String>> allTextArray = arrayWithTextNew(test2, test21);
        ArrayList<List<String>> allTextArray = arrayWithText(t1, t2);
        for (List<String> el : allTextArray) {
            System.out.println(el.toString());
        }
//        System.out.println();
//        System.out.println(t3);

        ArrayList<String> txtOut = new ArrayList<>(); // текст для вывода
        int k = 0;
        for (int i=0; i<allTextArray.get(0).size();i++) {
            //txtOut.add("");
            if (allTextArray.get(0).get(i).equals("")){ // есть изменения в этом месте
                if (allTextArray.get(1).get(i).equals("")){ // пустое место в diff1 (что-то добавлено в текст2)
                    // todo
                    // добавить в основной текст
                    txtOut.add(allTextArray.get(2).get(i));
                    System.out.println(ANSI_GREEN+allTextArray.get(2).get(i)+ANSI_RESET);
                }
                else { // пустое в diff2 (что-то убрано из текста1)
                    // todo
                    // удалить из основного текста
                    txtOut.add(allTextArray.get(1).get(i));
                    System.out.println(ANSI_RED+allTextArray.get(1).get(i)+ANSI_RESET);
                }
            }
            else { // нет изменений
                // todo
                txtOut.add(allTextArray.get(0).get(i));
                System.out.println(allTextArray.get(0).get(i));
            }
            k++;
        }

//        for (String el : txtOut) {
//            System.out.println(el.toString());
//        }
        outFile(txtOut);
    }

}
