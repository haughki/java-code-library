package org.haughki.codeLibrary.programmingProblems;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class ReverseStringTest {
    @Test
    public void reverseWordsSymmetric() throws Exception {
        String s = "four or more";
        String reversed = "more or four";
        ReverseString r = new ReverseString();
        Assert.assertEquals(reversed, r.reverseWords(s));
    }
    
    @Test
    public void reverseWordsSymmetricSpace() throws Exception {
        String s = "four is so neat";
        String reversed = "neat so is four";
        ReverseString r = new ReverseString();
        Assert.assertEquals(reversed, r.reverseWords(s));
    }
    
    @Test
    public void reverseWordsAsymmetric() throws Exception {
        String s = "eight nifty ear";
        String reversed = "ear nifty eight";
        ReverseString r = new ReverseString();
        Assert.assertEquals(reversed, r.reverseWords(s));
    }
    
    @Test
    public void reverseWordsAsymmetricSpace() throws Exception {
        String s = "eight a big ear";
        String reversed = "ear big a eight";
        ReverseString r = new ReverseString();
        Assert.assertEquals(reversed, r.reverseWords(s));
    }

    @Test
    public void reverseOdd() throws Exception {
        String s = "fiver";
        String reversed = "revif";
        ReverseString r = new ReverseString();
        char[] c = s.toCharArray();
        r.reverse(0, s.length(), c);

        Assert.assertEquals(reversed, String.valueOf(c));
    }

    @Test
    public void reverseEven() throws Exception {
        String s = "four";
        String reversed = "ruof";
        ReverseString r = new ReverseString();
        char[] c = s.toCharArray();
        r.reverse(0, s.length(), c);

        Assert.assertEquals(reversed, String.valueOf(c));
    }

    @Test
    public void reverseWithSpaces() throws Exception {
        String s = "four or more";
        String reversed = "erom ro ruof";
        ReverseString r = new ReverseString();
        char[] c = s.toCharArray();
        r.reverse(0, s.length(), c);

        Assert.assertEquals(reversed, String.valueOf(c));
    }
    
    
}