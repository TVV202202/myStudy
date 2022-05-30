package ru.progwards.java1.lessons.files;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public class FilesSelect {

    public static List<Path> createFilesList(String inFolder) throws IOException {
        List<Path> resultList = new ArrayList<>();
        Path dir = Paths.get(inFolder);
        PathMatcher pathMatcher = FileSystems.getDefault().getPathMatcher("glob:**/*.txt");
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

    public static void selectFiles(String inFolder, String outFolder, List<String> keys) throws IOException {
        Path path = Paths.get(outFolder);
        if (!Files.exists(path)) Files.createDirectory(path);
        List<Path> pathList = createFilesList(inFolder);
        for (String key : keys) { // по ключам
            for (Path el : pathList) { // по списку файлов
                if (Files.readString(el).contains(key)) {
                    Path dstPath = path.resolve(key);
                    if (!Files.exists(dstPath)) Files.createDirectory(dstPath);
                    Files.copy(el, dstPath.resolve(el.getFileName()), StandardCopyOption.REPLACE_EXISTING);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        selectFiles("D:\\Test", "D:\\Test1", List.of("qwert", "aaa", "123", "ytytry"));
    }
}
