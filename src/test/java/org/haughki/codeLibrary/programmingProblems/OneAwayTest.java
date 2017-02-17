package org.haughki.codeLibrary.programmingProblems;

import org.haughki.codeLibrary.programmingProblems.OneAway;
import org.junit.Assert;
import org.junit.Test;

public class OneAwayTest {
    @Test
    public void isOneAway() throws Exception {
        OneAway oa = new OneAway("abcde", "abcde");
        Assert.assertTrue(oa.isOneAway());

        oa = new OneAway("abcde", "abcdefg");
        Assert.assertFalse(oa.isOneAway());

        oa = new OneAway("abcdefg", "abcde");
        Assert.assertFalse(oa.isOneAway());

        oa = new OneAway("abcd", "abcde");
        Assert.assertTrue(oa.isOneAway());

        oa = new OneAway("abcdz", "abcde");
        Assert.assertTrue(oa.isOneAway());

        oa = new OneAway("abcde", "abcd");
        Assert.assertTrue(oa.isOneAway());

        oa = new OneAway("abcde", "bcde");
        Assert.assertTrue(oa.isOneAway());

        oa = new OneAway("bcde", "abcde");
        Assert.assertTrue(oa.isOneAway());
        
        oa = new OneAway("abcde", "abzde");
        Assert.assertTrue(oa.isOneAway());
        
        oa = new OneAway("abcde", "abde");
        Assert.assertTrue(oa.isOneAway());

        oa = new OneAway("aycze", "abcde");
        Assert.assertFalse(oa.isOneAway());
        
        oa = new OneAway("abcde", "aycze");
        Assert.assertFalse(oa.isOneAway());
        
        oa = new OneAway("aycde", "abcze");
        Assert.assertFalse(oa.isOneAway());
        
        oa = new OneAway("bcde", "abcze");
        Assert.assertFalse(oa.isOneAway());
        
        oa = new OneAway("abcze", "bcde");
        Assert.assertFalse(oa.isOneAway());
        
        oa = new OneAway("azcde", "abcd");
        Assert.assertFalse(oa.isOneAway());
        
    }

    @Test
    public void temp() throws Exception {

    }

}