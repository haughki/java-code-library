package org.haughki.codeLibrary;

public class FluentAndLambdas {
    public static void main(String[] args) {


        // probably a terrible way to do this...
/*
        String s = "Some words 中 go herЁ.";
        s.chars().forEach(i -> {  // chars() returns an intStream, where the ints are UTF-16 *code-point* values
            System.out.println(String.format("0x%6s", Integer.toHexString(i)));
            System.out.println((char) i);  // watch out for character encoding. these are UTF-16 code-points.
            if (i == 'e')
                System.out.println("found an e.");
        });
*/
    }
}
