package org.haughki.codeLibrary.functional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FluentAndLambdas {
    public static void main(String[] args) {

        streamStringChars();

        System.out.println();

        final List<String> listOfStrings = new ArrayList<>(Arrays.asList("stork", "something", "adverse"));

        // map
        listOfStrings.stream().map(s -> s.concat(" is great!")).forEach(System.out::println);

        System.out.println();

        // filter
        listOfStrings.stream().filter(s -> s.contains("o")).forEach(System.out::println);
    }

    private static void streamStringChars() {
        // There is no "CharStream":  http://stackoverflow.com/questions/22435833/why-is-string-chars-a-stream-of-ints-in-java-8
        // This was a design decision to reduce API bloat.
        String s = "Some words 中 go herЁ.";
        s.chars().forEach(i -> {  // chars() returns an intStream, where the ints are UTF-16 *code-point* values
            System.out.println(String.format("0x%6s", Integer.toHexString(i)));
            System.out.println((char) i);  // watch out for character encoding. these are UTF-16 code-points.
            if (i == 'e')
                System.out.println("found an e.");
        });

        System.out.println();

        // similar
        s.chars().mapToObj(i -> (char) i).forEach(System.out::println);
    }


}
