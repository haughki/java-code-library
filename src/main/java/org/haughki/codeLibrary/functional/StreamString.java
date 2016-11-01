package org.haughki.codeLibrary.functional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StreamString {
    public static void main(String[] args) {

        streamStringChars();
        
    }

    private static void streamStringChars() {
        // There is no "CharStream":  http://stackoverflow.com/questions/22435833/why-is-string-chars-a-stream-of-ints-in-java-8
        // This was a design decision to reduce API bloat.
        String s = "Some words 中 go herЁ.";
        s.chars().forEach(n -> {  // chars() returns an intStream, where the ints are UTF-16 *code-point* values
            System.out.println(String.format("0x%6s", Integer.toHexString(n)));
            System.out.println((char) n);  // watch out for character encoding. these are UTF-16 code-points.
            if (n == 'e')
                System.out.println("found an e.");
        });

        System.out.println();

        // similar
        s.chars().mapToObj(i -> (char) i).forEach(System.out::println);
    }


}
