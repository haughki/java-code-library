package org.haughki.codeLibrary;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;

public class CharsAndStrings {
    public static void main(String[] args) throws UnsupportedEncodingException {
        // supplementary
        String basicPlane = "Basic plane: ᚡځ人Җאあ⇈";
        basicPlane.codePoints().forEach(c -> System.out.print((char)c));
        System.out.print("\r\n");
        basicPlane.codePoints().forEach(cp ->
                System.out.print(new String(Character.toChars(cp))));
        System.out.print("\r\n");
        String supplementary = "Some Supplementary: 𠜎𠜱𠝹𠱓";
        supplementary.codePoints().forEach(cp -> 
                System.out.print(new String(Character.toChars(cp))));
        System.out.print("\r\n");
        
        System.out.println();
        // the int value of a char is the Unicode code point:
        char c = '不';
        System.out.println("Codepoint for bu: " + (int)c);
        System.out.println("From codepoint: " + (char)19981);
        System.out.println();
        
        // STRING TO BYTES TO STRING:  character encodings
        String convertMe = "不 some text";
        System.out.println("Default Encoding: " + Charset.defaultCharset());
        
        byte[] defaultBytes = convertMe.getBytes();
        System.out.println("No Encoding Specified: " + new String(defaultBytes));
        System.out.println("Reconverted: " + new String (defaultBytes, "UTF-8"));
        System.out.println("UTF-8 as UTF-16: " + new String (defaultBytes, "UTF-16"));

        System.out.println();
        byte[] utf16_Bytes = convertMe.getBytes("UTF-16");
        String noEncodingBad =  new String(utf16_Bytes);
        System.out.println("UTF-16 as UTF-8: ");
        System.out.println(noEncodingBad);
        System.out.println("Reconverted 16: " + new String(utf16_Bytes, "UTF-16"));

        System.out.println();
        ByteBuffer byteBuffer = ByteBuffer.wrap(defaultBytes);
        CharBuffer asCharBuffer = Charset.forName("UTF-8").decode(byteBuffer);
        System.out.println(asCharBuffer);

        // Strings are CharSequences
        System.out.println();
        CharSequence sequence = new String("Some chars are here");
        sequence.chars().forEach(n -> System.out.print((char)n));
        System.out.print("\r\n");
        sequence = "Some other chars are HERE.";
        sequence.chars().forEach(n -> System.out.print((char)n));
        System.out.print("\r\n");

        // Codepoints
        System.out.println();
        String aString = "不 some text";
        System.out.println("Codepoint at 0: " + aString.codePointAt(0));
        System.out.println("Don't count whitespace: " + aString.codePointCount(0, 2));
        System.out.println(aString.charAt(aString.offsetByCodePoints(0, 3)));
        
        // String, StringBuilder
        System.out.println();
        String myString = "my nice little string";
        String myString2 = "my nice little string";
        StringBuilder stringBuilder = new StringBuilder(myString);
        System.out.println(myString.equals(stringBuilder));
        System.out.println(myString.contentEquals(stringBuilder));
        System.out.println(myString.contentEquals(myString2));

    }
}
