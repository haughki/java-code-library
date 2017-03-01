package org.haughki.codeLibrary.bitsAndBytes;

import org.junit.Assert;
import org.junit.Test;


public class BitVectorTest {
    
    @Test(expected = IllegalArgumentException.class)
    public void badKeyThrows_set() throws Exception {
        BitVector bv = new BitVector(32);
        bv.set(32, true);
    }

    @Test(expected = IllegalArgumentException.class)
    public void badKeyThrows_get() throws Exception {
        BitVector bv = new BitVector(32);
        bv.get(32);
    }

    @Test
    public void zeroKey() throws Exception {
        BitVector bv = new BitVector(1);
        bv.set(0, true);
        Assert.assertTrue(bv.get(0));
        Assert.assertFalse(bv.get(1));
    }

    @Test
    public void thirtyOneKey() throws Exception {
        BitVector bv = new BitVector(1);
        bv.set(31, true);
        Assert.assertTrue(bv.get(31));
    }

    @Test
    public void toggle() throws Exception {
        BitVector bv = new BitVector(1);
        bv.set(4, true);
        Assert.assertTrue(bv.get(4));
        bv.set(4, false);
        Assert.assertFalse(bv.get(4));
    }

    @Test
    public void setMultiple() throws Exception {
        BitVector bv = new BitVector(64);
        bv.set(4, true);
        Assert.assertTrue(bv.get(4));
        Assert.assertFalse(bv.get(10));
        bv.set(25, true);
        Assert.assertTrue(bv.get(4));
        Assert.assertTrue(bv.get(25));
        Assert.assertFalse(bv.get(10));
        bv.set(48, true);
        Assert.assertTrue(bv.get(4));
        Assert.assertTrue(bv.get(25));
        Assert.assertTrue(bv.get(48));
        Assert.assertFalse(bv.get(10));
        bv.set(4, false);
        Assert.assertFalse(bv.get(4));
        Assert.assertTrue(bv.get(25));
        Assert.assertTrue(bv.get(48));
        Assert.assertFalse(bv.get(10));
    }
    
}