package org.haughki.codeLibrary.aacommon;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {
    public static List<File> getFileNames(Path dir) throws IOException {
        List<File> fileNames = new ArrayList<>();
        try(DirectoryStream<Path> stream = Files.newDirectoryStream(dir)) {
            for (Path path : stream) {
                if(path.toFile().isDirectory()) {
                    getFileNames(path);
                } else {
                    fileNames.add(path.toFile());
                }
            }
        }
        return fileNames;
    }

}
