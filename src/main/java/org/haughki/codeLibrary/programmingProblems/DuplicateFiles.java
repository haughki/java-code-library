package org.haughki.codeLibrary.programmingProblems;

import org.apache.commons.codec.digest.DigestUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.util.*;


/*
Given a directory root, find any duplicate files (byte-by-byte duplicates) within the directory or any of its 
sub-directories.  Return a list of lists, where each element in the outer list is a list of the paths of any 
files which are duplicates of one another.

I don't know for sure, but I think one point of this is that the real bottleneck here will be the file read
and hash, especially if there are any big files.  So that, even though we're looping and sorting multiple times,
the actual time complexity will likely depend on the file read+hash, not the various O(n) loops.
 */
public class DuplicateFiles {

    private List<File> allFiles = new ArrayList<>();
    private Map<ByteBuffer, List<String>> hashed = new HashMap<>();
    private MessageDigest digest = DigestUtils.getSha256Digest();

    public static void main(String[] args) throws IOException {
        DuplicateFiles d = new DuplicateFiles();
        for(List<String> l : d.findDuplicates("D:\\temp"))
            System.out.println(l);
    }
    
    private List<List<String>> findDuplicates(final String root) throws IOException {
        // first get all of the files:  O(n) where n is number of files/directories
        walkDirectoryTree(Paths.get(root));
        
        // sort files by file size: puts all potential duplicates next to each other
        // likely nlog(n) for quicksort, where n is number of files in array 
        allFiles.sort((f1, f2) -> {
            if (f1.length() == f2.length())
                return 0;
            else if (f1.length() > f2.length())
                return 1;
            else
                return -1;
        });

        // compare each file to next file.  If you find one of same size, get both, hash them, and 
        // then keep lookin for more.  O(n) for number of files.
        for (int i = 0; i < allFiles.size() - 1; i++) {
            File curr = allFiles.get(i);
            File next = allFiles.get(i + 1);
            //System.out.println(curr.getName() + ": " + curr.length());
            if (curr.length() == next.length()) {
                //System.out.println(next.getName() + ": " + next.length());
                // hash curr and next and store them in a map
                hashAndAddDuplicate(curr);
                hashAndAddDuplicate(next);
                // get the next file after "next", and so on, if there are any
                int whileIndex = i + 2;
                File more = allFiles.get(whileIndex);
                while (more.length() == curr.length()) {
                    hashAndAddDuplicate(more);
                }
            }
        }
        // extract just paths
        List<List<String>> duplicates = new ArrayList<>();
        duplicates.addAll(hashed.values());
        return duplicates;
    }
    
    private void hashAndAddDuplicate(File file) throws IOException {
        byte[] hash = hashFile(file);
//        for(byte b : hash)
//            System.out.print(b);
//        System.out.println();
        List<String> files = hashed.get(ByteBuffer.wrap(hash));
        if (files != null && files.size() > 0)
            files.add(file.getAbsolutePath());
        else
            hashed.put(ByteBuffer.wrap(hash), new ArrayList<>(Arrays.asList(file.getAbsolutePath())));
    }

    private byte[] hashFile(File file) throws IOException {
        FileInputStream fis = new FileInputStream(file.getAbsolutePath());

        byte[] dataBytes = new byte[1024];

        int numberBytesRead;
        while ((numberBytesRead = fis.read(dataBytes)) != -1) {
            digest.update(dataBytes, 0, numberBytesRead);
        }
        return digest.digest();
    }

    private void walkDirectoryTree(final Path root) {
        try(DirectoryStream<Path> stream = Files.newDirectoryStream(root)) {
            for (Path path : stream) {
                File found = path.toFile();
                if(found.isDirectory()) {
                    walkDirectoryTree(path);
                } else {
                    allFiles.add(found);
                }
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
    } 
}
