package org.haughki.codeLibrary.bitsAndBytes;


import java.util.Arrays;

/*
My play impl of a bit vector, just to understand it better.

This bit vector is an array of ints. Each index of the array (an int) is a 'bucket'. Each bucket is an int, an you can use all
the bits of the int (32) to store information; each bit can be 0 or 1. So, the structure can hold true/false information for
32 * arrayLength things.
 */
public class BitVector {
    private final int length;
    private final int[] vector;
    private final static int BUCKET_SIZE = 32;  // number of bits in each bucket
    
    public BitVector(int length) {
        this.length = length;
        int numBuckets = this.length / BUCKET_SIZE;
        if (this.length % BUCKET_SIZE > 0)
            numBuckets++;
        vector = new int[numBuckets];
    }
    
    public void set(int key, boolean value) {
        int bucket = key / BUCKET_SIZE;
        if (bucket >= vector.length)
            throw new IllegalArgumentException("Bad key: " + key);
        int index = key % BUCKET_SIZE;
        int mask = 1 << index;  // shift the binary number '1' left by the number of the index
        if (value)
            vector[bucket] |= mask;
        else
            vector[bucket] &= ~mask;
    }
    
    public boolean get(int key) {
        int bucket = key / BUCKET_SIZE;  // rounds down. e.g., Key 33 would go into bucket 1.
        if (bucket >= vector.length)
            throw new IllegalArgumentException("Bad key: " + key);
        int index = key % BUCKET_SIZE;
        int mask = 1 << index;
        return (vector[bucket] & mask) == mask; 
    }
    
    // ** TESTING ONLY
    public int[] getVector() {
        return Arrays.copyOf(vector, vector.length);
    }
}
