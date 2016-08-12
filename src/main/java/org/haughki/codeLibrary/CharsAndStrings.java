package org.haughki.codeLibrary;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;

public class CharsAndStrings {
    public static void main(String[] args) throws UnsupportedEncodingException {
        // the int value of a char is the Unicode code point:
        char c = '不';
        System.out.println((int) (c));
        System.out.println((char) (19981));
        
        // string to bytes to string
        String convertMe = "不 some text";
        byte[] convertMeBytes = convertMe.getBytes();
        String reconverted = new String (convertMeBytes, "UTF-8");
        System.out.println(reconverted);
        ByteBuffer byteBuffer = ByteBuffer.wrap(convertMeBytes);
        CharBuffer asCharBuffer = Charset.forName("UTF-8").decode(byteBuffer);
        System.out.println(asCharBuffer);
    }
}
