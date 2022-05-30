package ru.progwards.java1.lessons.files;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class FindDuplicates {

    public static ArrayDeque<Path> createFilesList(String startPath) throws IOException {
        ArrayDeque<Path> resultList = new ArrayDeque<>();
        Path dir = Paths.get(startPath);
        PathMatcher pathMatcher = FileSystems.getDefault().getPathMatcher("glob:**");
        Files.walkFileTree(dir, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) {
                if (pathMatcher.matches(path))
                    resultList.add(path);
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFileFailed(Path file, IOException e) {
                return FileVisitResult.CONTINUE;
            }
        });
        return resultList;
    }


    public static boolean compare(Path name1, Path name2) { // реализовать сравнение файлов по имени/расш, дате-времени посл изм, размеру, содержимому
        try {
            boolean atr1 = name1.getFileName().equals(name2.getFileName());
            boolean atr2 = Files.getAttribute(name1, "lastModifiedTime").equals(Files.getAttribute(name2, "lastModifiedTime"));
            boolean atr3 = Files.getAttribute(name1, "size").equals(Files.getAttribute(name2, "size"));
            boolean atr4 = eqInside(name1, name2);
            return atr1 && atr2 && atr3 && atr4;
        }catch (IOException e){
            return false;
        }

    }

    public static boolean eqInside(Path name1, Path name2)  {
        try {
            String in1 = Arrays.toString(Files.readAllBytes(name1));
            String in2 = Arrays.toString(Files.readAllBytes(name2));
            return in1.equals(in2);
        } catch (IOException e){
            return false;
        }

    }

    public static List<List<String>> findDuplicates(String startPath)  {
        List<List<String>> lists = new ArrayList<>();
        try {
            Deque<Path> filesList = createFilesList(startPath);
            Path file;
            while ((file = filesList.poll()) != null) {
                List<String> listFiles = new ArrayList<>();
                listFiles.add(String.valueOf(file));
                for (Path el : filesList) {
                    if (compare(file, el)) {
                        listFiles.add(String.valueOf(el));
                        filesList.poll();
                    }
                }
                if (listFiles.size() > 1) lists.add(listFiles);
            }
        } catch (IOException e){

        }
        return lists;
    }

    public static void main(String[] args) {
        //System.out.println(createFilesList("D:\\Test"));
        List<List<String>> test = findDuplicates("D:\\Test");

        for (List<String> el : test) {
            System.out.println(el);
        }
    }
}
