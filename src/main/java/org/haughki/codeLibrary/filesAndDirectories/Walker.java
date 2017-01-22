package org.haughki.codeLibrary.filesAndDirectories;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Walker {
    public static void main(String[] args) {
        List<File> files = new ArrayList<>();
        //listf("D:\\Projects", files);
        getFileNames(files, Paths.get("D:\\Projects"));
        files.forEach(System.out::println);
    }
    private static void listf(String directoryName, List<File> files) {
        File directory = new File(directoryName);

        // get all the files from a directory
        File[] fList = directory.listFiles();
        for (File file : fList) {
            if (file.isFile()) {
                files.add(file);
            } else if (file.isDirectory()) {
                listf(file.getAbsolutePath(), files);
            }
        }
    }

    private static void getFileNames(List<File> fileNames, Path dir) {
        try(DirectoryStream<Path> stream = Files.newDirectoryStream(dir)) {
            for (Path path : stream) {
                if(path.toFile().isDirectory()) {
                    getFileNames(fileNames, path);
                } else {
                    fileNames.add(path.toFile());
                }
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
