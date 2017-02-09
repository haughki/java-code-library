package org.haughki.codeLibrary.programmingProblems;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class ReverseStringTest {

    @Test
    public void reverseNoWord() throws Exception {
        ReverseString rev = new ReverseString();
        Assert.assertEquals("", rev.reverseWords(""));
    }

    @Test
    public void reverseSpace() throws Exception {
        ReverseString rev = new ReverseString();
        Assert.assertEquals(" ", rev.reverseWords(" "));
    }

    @Test
    public void reverseNoSpaces() throws Exception {
        ReverseString rev = new ReverseString();
        Assert.assertEquals("bug", rev.reverseWords("bug"));
    }

    @Test
    public void reverseWordsOdd() throws Exception {
        ReverseString rev = new ReverseString();
        String s = "gf ed xyz abc";
        String r = "abc xyz ed gf";
        Assert.assertEquals(r, rev.reverseWords(s));

        s = "edc ba efg hi";
        r = "hi efg ba edc";
        Assert.assertEquals(r, rev.reverseWords(s));

        s = "hgf eyx ab cd";
        r = "cd ab eyx hgf";
        Assert.assertEquals(r, rev.reverseWords(s));

        s = "hgfe xyz abcd";
        r = "abcd xyz hgfe";
        Assert.assertEquals(r, rev.reverseWords(s));
    }

    @Test
    public void reverseWordsEven() throws Exception {
        ReverseString rev = new ReverseString();
        String s = "ed cba fg hi";
        String r = "hi fg cba ed";
        Assert.assertEquals(r, rev.reverseWords(s));

        s = "ji hg bcd ef";
        r = "ef bcd hg ji";
        Assert.assertEquals(r, rev.reverseWords(s));

        s = "jih gab cdef";
        r = "cdef gab jih";
        Assert.assertEquals(r, rev.reverseWords(s));

        s = "jihg abc def";
        r = "def abc jihg";
        Assert.assertEquals(r, rev.reverseWords(s));
    }

    
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