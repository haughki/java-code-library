package org.haughki.codeLibrary.filesAndDirectories;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ReadWriteFile {
    public static void main(String[] args) {
        
        ReadWriteFile rwf = new ReadWriteFile();
        rwf.readWithHelper();
        System.out.println();
        rwf.readCompleteSyntax();
        System.out.println();
        rwf.writeWithHelper();
        
    }
    
    
    
    private void readWithHelper() {
        Path testPath = getResourcePath();
        try (BufferedReader reader = Files.newBufferedReader(testPath, StandardCharsets.UTF_8)) {
            String line;
            while ((line = reader.readLine()) != null)  // .readline() only available on BufferedReader, not Reader.
                System.out.println(line);
        } catch (IOException x) {
            System.err.format("IOException: %s%n", x);
        }
    }

    // The following is what readWithHelper is doing under the hood.
    private void readCompleteSyntax() {
        try (
                InputStream fileStream = new FileInputStream(getResourceFile());
                Reader streamReader = new InputStreamReader(fileStream, StandardCharsets.UTF_8);
                BufferedReader reader = new BufferedReader(streamReader)) {
            String line;
            while ((line = reader.readLine()) != null)  // .readline() only available on BufferedReader, not Reader.
                System.out.println(line);
        } catch (IOException e) {
            throw new IllegalStateException("Failed to read file.", e);
        }
    }
    private Path getResourcePath() {
        URL resourceUrl = getResourceUrl();
        return Paths.get(resourceUrl.getPath());
    }
    
    private File getResourceFile() {
        return getResourcePath().toFile();
    }

    private URL getResourceUrl() {
        ClassLoader classLoader = ReadWriteFile.class.getClassLoader();
        URL resourceUrl = classLoader.getResource("utf8TestData.data");
        if (resourceUrl != null)
            return resourceUrl;
        else
            throw new IllegalStateException("resourceUrl is null.");
    }

    private void writeWithHelper() {
        String tempOut = System.getProperty("user.dir") + "\\temp";
        System.out.println(tempOut);
        Path path = Paths.get(tempOut);
        if (!Files.exists(path)) {
            try {
                Files.createDirectories(path);
            } catch (IOException e) {
                throw new IllegalStateException("Could not create path: " + path, e);
            }
        }

        Path filePath = path.resolve("tempFile.dat");

        String s = "English (Braille): ⠊⠀⠉⠁⠝⠀⠑⠁⠞⠀⠛⠇⠁⠎⠎⠀⠁⠝⠙⠀⠊⠞⠀⠙⠕⠑⠎⠝⠞⠀⠓⠥⠗⠞⠀⠍⠑\n" +
                "Jamaican: Mi kian niam glas han i neba hot mi.\n" +
                "Lalland Scots / Doric: Ah can eat gless, it disnae hurt us.";
        try (Writer writer = Files.newBufferedWriter(filePath, StandardCharsets.UTF_8)) {
            writer.write(s, 0, s.length());
        } catch (IOException e) {
            throw new IllegalStateException("Could not write to file path: " + filePath, e);
        }
    }

}
