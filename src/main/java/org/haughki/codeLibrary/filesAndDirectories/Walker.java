package org.haughki.codeLibrary.filesAndDirectories;

import org.haughki.codeLibrary.aacommon.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class Walker {
    public static void main(String[] args) throws IOException {
        //List<File> files = new ArrayList<>();
        //listf("D:\\Projects", files);
        List<File> files = FileUtils.getFileNames(Paths.get("D:\\Projects"));
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

}
